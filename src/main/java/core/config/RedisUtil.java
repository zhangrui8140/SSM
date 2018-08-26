package core.config;

import business.bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class RedisUtil<T>{

    @Autowired
    private RedisTemplate<String, String> template;



















    //region Pub/Sub
    public void SendMsgToTopic(String topic,Object msg){
        template.convertAndSend(topic,msg);
    }
    //endregion

}
