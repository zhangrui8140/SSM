package core.config.redis.listener;

import business.bean.HelloWorldBean;

public interface MessageContentsDelegate {
    void handleMessage(String text);

    void handleMessage(byte[] bytes);

    void handleMessage(HelloWorldBean obj);

    void handleMessage(String text, String channel);

    void handleMessage(byte[] bytes, String pattern);
}
