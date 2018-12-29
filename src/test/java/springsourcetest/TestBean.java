package springsourcetest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class TestBean {
}

class UserModel{
    private String USERNAME;
    private Integer USERAGE;

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public Integer getUSERAGE() {
        return USERAGE;
    }

    public void setUSERAGE(Integer USERAGE) {
        this.USERAGE = USERAGE;
    }
}

class UserRowMapper implements RowMapper<UserModel>{
    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel model = new UserModel();
        model.setUSERNAME(rs.getString("USERNAME"));
        model.setUSERAGE(rs.getInt("USERAGE"));
        return model;
    }
}

class UserModelSqlQuery extends SqlQuery<UserModel> {
    public UserModelSqlQuery(JdbcTemplate jdbcTemplate) {
        //super.setDataSource(jdbcTemplate.getDataSource());
        super.setJdbcTemplate(jdbcTemplate);
        super.setSql("select * from test where name=?");
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }
    @Override
    protected RowMapper<UserModel> newRowMapper(Object[] parameters, Map context) {
        return new UserRowMapper();
    }
}

class UserModelMappingSqlQuery extends MappingSqlQuery<UserModel> {
    public UserModelMappingSqlQuery(JdbcTemplate jdbcTemplate) {
        super.setDataSource(jdbcTemplate.getDataSource());
        super.setSql("select * from test where name=:name");
        super.declareParameter(new SqlParameter("name", Types.VARCHAR));
        compile();
    }
    @Override
    protected UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel model = new UserModel();
        model.setUSERNAME(rs.getString("USERNAME"));
        model.setUSERAGE(rs.getInt("USERAGE"));
        return model;
    }
}

class InsertUserModel extends SqlUpdate {
    public InsertUserModel(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
        super.setSql("insert into test(name) values(?)");
        super.declareParameter(new SqlParameter(Types.VARCHAR));
        compile();
    }
}

