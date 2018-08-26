package core.config.redis.datastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisValueUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valueOps;

    //region set
    public void set(Map<String,RedisValueBean> valueMap){
        for(String key:valueMap.keySet()) {
            RedisValueBean valueBean=valueMap.get(key);
            if(valueBean==null) break;
            //有效时间设置
            if(valueBean.getExpireTime()!=null){
                valueOps.set(key,valueBean.getValue(),valueBean.getExpireTime(),valueBean.getTimeUnit());
            }
            if(valueBean.getOffset()!=null)
            {
                valueOps.set(key,valueBean.getValue(),valueBean.getOffset());
            }
            if(valueBean.getExistForSuccess()!=null){
                valueOps.setIfAbsent(key,valueBean.getValue());
            }
        }
    }

    public Map<String,Object> get(List<String> keys){
        Map<String,Object> valueMap=new HashMap<>();
        for(String key:keys){
            valueMap.put(key,valueOps.get(key));
        }
        return valueMap;
    }

    //set
}
