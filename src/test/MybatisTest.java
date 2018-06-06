import business.dao.HelloWorldMapper;
import business.table.HelloWorldTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MybatisTest {
    @Autowired
    private HelloWorldMapper helloWorldMapper;
    @Test
    public void main() throws JsonProcessingException {
        HelloWorldTable auth=helloWorldMapper.selectByPrimaryKey("1");
        String data=new ObjectMapper().writeValueAsString(auth);
        System.out.println(data);
    }
}
