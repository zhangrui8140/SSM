package core.excetionHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.*;

@Configuration("classpath:applicationContext.xml")
public class ExceptionHandlerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimpleMappingExceptionResolver() throws Exception{
        try{
            int i=1/0;
        }
        catch (Exception e){
            throw e;
        }
        System.out.print("SimpleMappingExceptionResolver");
    }
}