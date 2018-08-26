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

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ibatisTest {
    @Autowired
    private HelloWorldMapper DaoImp;
    @Test
    public void main() {
        HelloWorldTable test=new HelloWorldTable();
        test.setLgrpcd("9999");
        test.setIschnl("123456789");
        try {
            DaoImp.insert(test);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
