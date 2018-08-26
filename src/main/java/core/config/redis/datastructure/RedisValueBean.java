package core.config.redis.datastructure;

import java.util.concurrent.TimeUnit;

public class RedisValueBean {
    public RedisValueBean(Object value, Long expireTime, Boolean isExistForSuccess, TimeUnit timeUnit,Integer offset) {
        this.value = value;
        this.expireTime = expireTime;
        this.isExistForSuccess = isExistForSuccess;
        this.offset = offset;
        this.timeUnit = timeUnit;
    }

    private Object value;

    private Long expireTime;

    private Boolean isExistForSuccess;

    private Integer offset;

    private TimeUnit timeUnit;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getExistForSuccess() {
        return isExistForSuccess;
    }

    public void setExistForSuccess(Boolean existForSuccess) {
        isExistForSuccess = existForSuccess;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
