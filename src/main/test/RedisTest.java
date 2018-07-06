import business.dao.HelloWorldMapper;
import business.table.HelloWorldTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    private int index=60;

    @Test
    public void main() throws InterruptedException {

        ExecutorService cachedThreadPool= Executors.newCachedThreadPool();
        System.out.println(redisUtil.get("num"));
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
        TimeUnit.SECONDS.sleep(20L);
    }

    @Test
    public void redisSlaveTest(){
        System.out.println(redisUtil.get("name"));
    }
}
