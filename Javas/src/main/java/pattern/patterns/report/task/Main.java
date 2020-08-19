package pattern.patterns.report.task;

/**
 * I like it
 *
 * @author zhaolei22
 * @date 2020/08/19
 */
public class Main {
    public static void main(String[] args) {
        RedisTask task0 = RedisTask.of("001",RedisTaskType.LIMIT_APPLY_CALLBACK)
                .after(10000);
        RedisTask task1 = RedisTask.of("002",RedisTaskType.LIMIT_APPLY)
                .when(System.currentTimeMillis());
    }
}
