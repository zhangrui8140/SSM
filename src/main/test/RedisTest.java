import business.bean.HelloWorldBean;
import core.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RedisTest {

   /* @Autowired
    private RedisUtil<HelloWorldBean> redisUtil;

    @Autowired
    private RedisMessageListenerContainer listenerContainer;

    @Autowired
    private MessageListenerAdapter listenerAdapter;

    private int index=60;

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    public void test(){}



    @Test
    public void testRedisCache(){
        Cache time=redisCacheManager.getCache("time");
        Cache passwd=redisCacheManager.getCache("password");
        if(time!=null){
            time.put("loginTime","20180714");
            time.put("searchTime","20180720");
            passwd.put("zhangrui","123");
            passwd.put("xin","456");
        }
        System.out.println(redisCacheManager.getCache("time").get("loginTime"));
    }

    @Test
    public void main() throws InterruptedException {

       *//* ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        //synchronized (this) {
            for (int i = 0; i < 100; i++) {
                //TimeUnit.SECONDS.sleep(10L);
                System.out.println("Random:"+Math.random());
                if (index < 70) {
                    cachedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("Name:"+Thread.currentThread().getName());
                                index++;
                                //System.out.println(redisUtil.incr("num",1));
                                TimeUnit.SECONDS.sleep(1L);
                                System.out.println("EndName:"+Thread.currentThread().getName());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });
                }
            }
       // }
        TimeUnit.SECONDS.sleep(20L);*//*
    }

    @Test
    public void redisValueOpsTest(){
        try{

          *//*  HelloWorldBean user =new HelloWorldBean(1,"zhangrui");
            redisUtil.setValueOps("test2",user);
            HelloWorldBean userGet=redisUtil.getValueOps("test2");
            System.out.println(userGet.getName());*//*
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("end");
    }

    @Test
    public void redisPubSub() throws InterruptedException {
        try {
          *//*  listenerContainer.addMessageListener(listenerAdapter,new ChannelTopic("Theme.Math"));
            for(int i=0;i<10;i++){
                redisUtil.SendMsgToTopic("Theme.Math",new byte[]{1,2,3});
                Thread.sleep(1000);
            }*//*
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

*/
}
