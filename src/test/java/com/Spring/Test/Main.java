package com.Spring.Test;
import com.Spring.Test.bean.Collection.Customer;
import com.Spring.Test.bean.OutPutGenerator.OutPutHelper;
import com.Spring.Test.bean.OutPutGenerator.FileNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"SpringBeans.xml"});

        Customer obj = (Customer) context.getBean("customerBean");
        System.out.println(obj.getLists().toString());
        System.out.println(obj.getMaps().toString());
        System.out.println(obj.getSets().toString());
        System.out.println(obj.getProps().toString());
    }
}
