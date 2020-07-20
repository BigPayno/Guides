package recipes;

import com.google.common.collect.Iterables;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 领导人
 *
 * @author zhaolei22
 * @date 2020/07/20
 */
public class Leaders {

    private static final String ZK_CONNECTS = "zk1.idc.cedu.cn:2181,zk2.idc.cedu.cn:2181,zk3.idc.cedu.cn:2181";

    private List<CuratorFramework> clients;

    @Before
    public void init(){
        clients = new ArrayList<>(10);
        IntStream.range(0,10).forEach(num->{
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(ZK_CONNECTS)
                    .connectionTimeoutMs(15000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            client.start();
            clients.add(client);
        });
    }

    @Test
    public void inspect() throws Exception {
        Iterables.getLast(clients)
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/test/inspect","chad".getBytes());

        Object val = Iterables.getFirst(clients,null)
                .getData()
                .forPath("/test/inspect");
        if(val instanceof byte[]){
            System.out.println(new String((byte[]) val));
        }
    }

    @Test
    public void leaderLatch() throws InterruptedException {
        List<LeaderLatch> leaderLatches = new ArrayList<>(10);
        clients.forEach(client->{
            LeaderLatch leaderLatch =
                    new LeaderLatch(client, "/test/leader_latch/leader", "client-"+client.toString());
            leaderLatch.addListener(new LeaderLatchListener() {
                @Override
                public void isLeader() {
                    System.out.println(leaderLatch.getId() + " is a leader!");
                }

                @Override
                public void notLeader() {
                    System.out.println(leaderLatch.getId() + " not a leader!");
                }
            });
            leaderLatches.add(leaderLatch);
        });

        leaderLatches.parallelStream().forEach(leaderLatch -> {
            try{
                leaderLatch.start();
                leaderLatch.await();
                System.out.println(leaderLatch.getId()+" elect end");
                System.out.println(
                        leaderLatch.getId() + " leader:" + leaderLatch.getLeader() + " isLeader:" + leaderLatch.hasLeadership());
                if(leaderLatch.hasLeadership()){
                    leaderLatch.close();
                }
            }catch (Exception e){ }
        });

        Thread.sleep(100000);
        clients.forEach(client->{
            System.out.println(client.getData());
            client.close();
        });
    }

    @Test
    public void leaderSelector() throws InterruptedException {
        List<LeaderSelector> leaderSelectors = new ArrayList<>(10);
        Map<LeaderSelector,CuratorFramework> mapper = new HashMap<>();
        clients.forEach(client->{
            LeaderSelector leaderSelector = new LeaderSelector(client, "/test/leader_selector/leader", new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    System.out.println(Thread.currentThread().getName()+ " is a leader!");
                    Thread.sleep(Integer.MAX_VALUE);
                }

                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                    // do nothing!
                }
            });
            leaderSelectors.add(leaderSelector);
            mapper.put(leaderSelector, client);
        });

        leaderSelectors.parallelStream().forEach(leaderSelector -> {
            leaderSelector.autoRequeue();
            leaderSelector.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mapper.get(leaderSelector).close();
        });
    }
}
