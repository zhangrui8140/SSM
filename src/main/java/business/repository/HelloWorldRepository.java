package business.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;

import business.bean.HelloWorldBean;
import business.dao.HelloWorldMapper;
import business.table.HelloWorldTable;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;


//注册资源 不要用new 否则内部注册对象也为null
@Repository
public class HelloWorldRepository {

    @Resource
    private HelloWorldMapper helloWorldDao;

    public  HelloWorldBean getHelloWorld(){

        HelloWorldBean helloWorldBean=new HelloWorldBean(1,"zhangrui");
        try {
            HelloWorldTable auth=helloWorldDao.selectByPrimaryKey("1");
            return new HelloWorldBean(1,new ObjectMapper().writeValueAsString(auth));
        }
        catch(Exception e){
            helloWorldBean.setName("Error");
        }
        return helloWorldBean;
    }
}
