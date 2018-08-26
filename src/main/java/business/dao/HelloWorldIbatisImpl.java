package business.dao;

import business.table.HelloWorldTable;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibm.db2.jcc.am.SqlDataException;
import com.ibm.db2.jcc.am.SqlException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.sql.SQLException;

public class HelloWorldIbatisImpl extends SqlMapClientDaoSupport implements HelloWorldMapper{
    @Override
    public void insert(HelloWorldTable data) throws SQLException {
        final HelloWorldTable datatest=data;
        getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor sqlMapExecutor) throws SQLException {
                return sqlMapExecutor.insert("insert",datatest);
            }
        });
    }
}
