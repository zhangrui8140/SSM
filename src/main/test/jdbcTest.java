import business.dao.HelloWorldMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.Properties;


public class jdbcTest {
    @Test
    public void db2Test() {
        Connection conn=null;
        //String url="jdbc:db2://10.0.114.84:50000/pbpdb";
        //String url="jdbc:db2://10.0.114.182:50000/CBSDB";
        String url="jdbc:db2://10.0.114.99:50003/CBSDB";
        String user="db2inst1";//db2usr1 db2inst1
        String pwd="db2inst1";
        Statement stmt=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            System.out.println("编码:"+Charset.defaultCharset());
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            Properties prop=new Properties();
            prop.setProperty("user",user);
            prop.setProperty("password",pwd);
            //prop.setProperty("useUnicode","true");
            //prop.setProperty("characterEncoding","GBK");
            //prop.setProperty("DB2e_ENCODING","GB18030");
            //prop.setProperty("DB2CODEPAGE","5488");
            conn=DriverManager.getConnection(url,prop);
            StringBuilder sql=new StringBuilder();
            //sql.append("insert into DB2USR1.TEST (ID,NAME) values (?,?)");
            sql.append("insert into CBSDB.TEST (ID,NAME) values (?,?)");
            /*stmt=conn.createStatement();
            boolean result=stmt.execute(sql.toString());*/
            pstmt=conn.prepareStatement(sql.toString());
            pstmt.setString(1,"12");
            pstmt.setString(2,"1234");
            //pstmt.setString(3,"����");
            pstmt.execute();
            ResultSet test=pstmt.getResultSet();
            System.out.println(pstmt);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if(rs!=null){
                try {
                    rs.close();
                }
                catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }

            if(stmt!=null){
                try {
                    stmt.close();
                }
                catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }

            if(conn!=null){
                try {
                    conn.close();
                }
                catch (SQLException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }



    }
}
