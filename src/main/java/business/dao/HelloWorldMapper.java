package business.dao;

import business.table.HelloWorldTable;
import com.ibm.db2.jcc.am.SqlDataException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("HelloWorldMapper")
public interface HelloWorldMapper {
    void insert(HelloWorldTable data) throws SQLException;
}
