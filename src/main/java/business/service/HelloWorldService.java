package business.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements IHelloWorldService{

    public void testAspect(){
        System.out.print("");
    }
}
