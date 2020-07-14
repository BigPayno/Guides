package com.payno.guides.servers.mqs.amqp.guide;

import org.junit.Test;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * 重试amqp模板
 *      AMQP的重试机制的实现与使用
 * @author zhaolei22
 * @date 2020/07/14
 */
public class RetryAmqpTemplate extends FastStart{

    private void init(RetryTemplate retryTemplate){
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000L);
        backOffPolicy.setMultiplier(3);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(
                3, Collections.singletonMap(Exception.class, true));
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);
    }

    @Test
    public void retry(){
        RetryTemplate retryTemplate = new RetryTemplate();
        init(retryTemplate);
        rabbitTemplate.setRetryTemplate(retryTemplate);
        ConnectionFactory errCF = new CachingConnectionFactory("localhost");
        rabbitTemplate.setConnectionFactory(errCF);
        try{
            inspect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void retry2(){
        //  In this case, you would not inject a RetryTemplate into the RabbitTemplate.

        // of course,we can use it as a retry component!
        RetryTemplate retryTemplate = new RetryTemplate();
        init(retryTemplate);
        //  retryCallback 重试逻辑 recoverCallback 补偿逻辑 我们可以把这个Template当作一个重试的组件，就像Hystrix一样
        try{
            Integer result = retryTemplate.execute(retryContext -> {
                System.err.println(retryContext.getRetryCount());
                if(retryContext.getRetryCount()<4){
                    //  取4会触发recoverCallback，取2则会在重试中成功
                    throw new RuntimeException("mock exception!");
                }
                return retryContext.getRetryCount();
            },retryContext -> {
                Object message = retryContext.getAttribute("message");
                Throwable throwable = retryContext.getLastThrowable();
                System.err.println("after last throwable : "+throwable.getMessage());
                System.err.println("we finally get message : "+message+" ,we return 88 as result!");
                return Integer.valueOf(88);
            });
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
