package core.config.redis.listener;

import business.bean.HelloWorldBean;

public class MsgListener implements MessageContentsDelegate {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public MsgListener(int id){
        this.id=id;
    }

    @Override
    public void handleMessage(String text) {
        System.out.println("text");
    }

    @Override
    public void handleMessage(byte[] bytes) {
        System.out.println(bytes);
    }

    @Override
    public void handleMessage(HelloWorldBean obj) {
        System.out.println(obj);
    }

    @Override
    public void handleMessage(String text, String channel) {
        System.out.println(text+channel);
    }

    @Override
    public void handleMessage(byte[] bytes, String pattern) {
        System.out.println(bytes);
    }
}
