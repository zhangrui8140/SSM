package springsourcetest;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTest extends SpringSourceTest {

    private static final String T_USER="T_USER";

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testBatchUpdate5() {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("test");
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("name", "name5");
        insert.executeBatch(new Map[] {valueMap, valueMap});
        Assert.assertEquals(2, jdbcTemplate.update("select count(*) from test"));
    }

    @Test
    public void testBatchUpdate3() {
        String insertSql = "insert into test(name) values(:myName)";
        UserModel model = new UserModel();
        model.setUSERNAME("name5");
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(new Object[] {model, model});
        namedParameterJdbcTemplate.batchUpdate(insertSql, params);
        Assert.assertEquals(2, jdbcTemplate.update("select count(*) from test"));
    }

    @Test
    public void testBatchUpdate2() {
        String insertSql = "insert into test(name) values(?)";
        final String[] batchValues = new String[] {"name5", "name6"};
        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, batchValues[i]);
            }
            @Override
            public int getBatchSize() {
                return batchValues.length;
            }
        });
        Assert.assertEquals(2, jdbcTemplate.update("select count(*) from test"));
    }

    @Test
    public void testBatchUpdate1() {
        String insertSql = "insert into test(name) values('name5')";
        String[] batchSql = new String[] {insertSql, insertSql};
        jdbcTemplate.batchUpdate(batchSql);
        Assert.assertEquals(2, jdbcTemplate.update("select count(*) from test"));
    }


    @Test
    public void testFetchKey2() {
        final String insertSql = "insert into test(name) values('name5')";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        SqlUpdate update = new SqlUpdate();
        update.setJdbcTemplate(jdbcTemplate);
        update.setReturnGeneratedKeys(true);
        //update.setGeneratedKeysColumnNames(new String[]{"ID"});
        update.setSql(insertSql);
        update.update(null, generatedKeyHolder);
        Assert.assertEquals(0, generatedKeyHolder.getKey());
    }

    @Test
    public void testFetchKey1() throws SQLException {
        final String insertSql = "insert into test(name) values('name5')";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                return conn.prepareStatement(insertSql, new String[]{"ID"});
            }}, generatedKeyHolder);
        Assert.assertEquals(0, generatedKeyHolder.getKey());
    }

    @Test
    public void testSimpleJdbcCall3() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
        call.withProcedureName("PROCEDURE_TEST");
        call.declareParameters(new SqlInOutParameter("inOutName", Types.VARCHAR));
        call.declareParameters(new SqlOutParameter("outId", Types.INTEGER));
        SqlParameterSource params =
                new MapSqlParameterSource().addValue("inOutName", "test");
        Map<String, Object> outVlaues = call.execute(params);
        Assert.assertEquals("Hello,test", outVlaues.get("inOutName"));
        Assert.assertEquals(0, outVlaues.get("outId"));
    }

    @Test
    public void testSimpleJdbcCall2() {
        //调用hsqldb自定义函数得使用如下方式
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
        call.withProcedureName("FUNCTION_TEST");
        call.declareParameters(new SqlReturnResultSet("result",
                new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs)
                            throws SQLException, DataAccessException {
                        while(rs.next()) {
                            return rs.getInt(1);
                        }
                        return 0;
                    }}));
        call.declareParameters(new SqlParameter("str", Types.VARCHAR));
        Map<String, Object> outVlaues = call.execute("test");
        Assert.assertEquals(4, outVlaues.get("result"));
    }

    @Test
    public void testSimpleJdbcCall1() {
        //此处用mysql,因为hsqldb调用自定义函数和存储过程一样
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
        call.withFunctionName("FUNCTION_TEST");
        call.declareParameters(new SqlOutParameter("result", Types.INTEGER));
        call.declareParameters(new SqlParameter("str", Types.VARCHAR));
        Map<String, Object> outVlaues = call.execute("test");
        Assert.assertEquals(4, outVlaues.get("result"));
    }

    @Test
    public void testSimpleJdbcInsert() {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("test");
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("name", "name5");
        insert.compile();
        //1.普通插入
        insert.execute(args);
        Assert.assertEquals(1, jdbcTemplate.update("select count(*) from test"));
        //2.插入时获取主键值
        insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("test");
        insert.setGeneratedKeyName("id");
        Number id = insert.executeAndReturnKey(args);
        Assert.assertEquals(1, id);
        //3.批处理
        insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("test");
        insert.setGeneratedKeyName("id");
        int[] updateCount = insert.executeBatch(new Map[] {args, args, args});
        Assert.assertEquals(1, updateCount[0]);
        Assert.assertEquals(5, jdbcTemplate.update("select count(*) from test"));
    }

    @Test
    public void testSqlUpdate() {
        SqlUpdate insert = new InsertUserModel(jdbcTemplate);
        insert.update("name5");

        String updateSql = "update test set name=? where name=?";
        SqlUpdate update = new SqlUpdate(jdbcTemplate.getDataSource(), updateSql, new int[]{Types.VARCHAR, Types.VARCHAR});
        update.update("name6", "name5");
        String deleteSql = "delete from test where name=:name";

        SqlUpdate delete = new SqlUpdate(jdbcTemplate.getDataSource(), deleteSql, new int[]{Types.VARCHAR});
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "name5");
        delete.updateByNamedParam(paramMap);
    }

    @Test
    public void testSqlFunction() {
        jdbcTemplate.update("insert into test(name) values('name5')");
        String countSql = "select count(*) from test";
        SqlFunction<Integer> sqlFunction1 = new SqlFunction<Integer>(jdbcTemplate.getDataSource(), countSql);
        Assert.assertEquals(1, sqlFunction1.run());
        String selectSql = "select name from test where name=?";
        SqlFunction<String> sqlFunction2 = new SqlFunction<String>(jdbcTemplate.getDataSource(), selectSql);
        sqlFunction2.declareParameter(new SqlParameter(Types.VARCHAR));
        String name = (String) sqlFunction2.runGeneric(new Object[] {"name5"});
        Assert.assertEquals("name5", name);
    }

    @Test
    public void testMappingSqlQuery() {
        jdbcTemplate.update("insert into test(name) values('name5')");
        SqlQuery<UserModel> query = new UserModelMappingSqlQuery(jdbcTemplate);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "name5");
        UserModel result = query.findObjectByNamedParam(paramMap);
        Assert.assertNotNull(result);
    }

    @Test
    public void testSqlQuery() {
        SqlQuery query = new UserModelSqlQuery(jdbcTemplate);
        List<UserModel> result = query.execute("name5");
        Assert.assertEquals(0, result.size());
    }


















    /**
     * BeanPropertySqlParameterSource
     * 封装了一个JavaBean对象，通过JavaBean对象属性来决定命名参数的值。
     */
    @Test
    public void testNamedParameterJdbcTemplate2() {
       /* UserModel model = new UserModel();
        model.setMyName("name5");
        String insertSql = "insert into test(name) values(:myName)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(model);
        namedParameterJdbcTemplate.update(insertSql, paramSource);*/
    }

    @Test
    public void testNamedParameterJdbcTemplate1() {
        String insertSql = "insert into test(name) values(:name)";
        String selectSql = "select * from test where name=:name";
        String deleteSql = "delete from test where name=:name";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", "name5");
        namedParameterJdbcTemplate.update(insertSql, paramMap);
        final List<Integer> result = new ArrayList<Integer>();
        namedParameterJdbcTemplate.query(selectSql, paramMap,
                new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet rs) throws SQLException {
                        result.add(rs.getInt("id"));
                    }
                });
        Assert.assertEquals(1, result.size());
        SqlParameterSource paramSource = new MapSqlParameterSource(paramMap);
        namedParameterJdbcTemplate.update(deleteSql, paramSource);
    }

    /**
     * 对以下几种方法的封装
     */
    @Test
    public void testQueryFor1(){
        //1.查询一行数据并返回int型结果
        //jdbcTemplate.queryForInt("select count(*) from test");
        //2. 查询一行数据并将该行数据转换为Map返回
        jdbcTemplate.queryForMap("select * from test where name='name5'");
        //3.查询一行任何类型的数据，最后一个参数指定返回结果类型
        jdbcTemplate.queryForObject("select count(*) from test", Integer.class);
        //4.查询一批数据，默认将每行数据转换为Map
        jdbcTemplate.queryForList("select * from test");
        //5.只查询一列数据列表，列类型是String类型，列名字是name
        jdbcTemplate.queryForList("select name from test where name=?", new Object[]{"name5"}, String.class);
        //6.查询一批数据，返回为SqlRowSet，类似于ResultSet，但不再绑定到连接上
        SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from test");
    }

    @Test
    public void testResultSet3() {
        jdbcTemplate.update("insert into test(name) values('name5')");
        String listSql = "select * from test";
        List result = jdbcTemplate.query(listSql, new ResultSetExtractor<List>() {
            @Override
            public List extractData(ResultSet rs)
                    throws SQLException, DataAccessException {
                List result = new ArrayList();
                while(rs.next()) {
                    Map row = new HashMap();
                    row.put(rs.getInt("id"), rs.getString("name"));
                    result.add(row);
                }
                return result;
            }});
        Assert.assertEquals(0, result.size());
        jdbcTemplate.update("delete from test where name='name5'");
    }

    @Test
    public void testResultSet2() {
        jdbcTemplate.update("insert into test(name) values('name5')");
        String listSql = "select * from test";
        final List result = new ArrayList();
        jdbcTemplate.query(listSql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                Map row = new HashMap();
                row.put(rs.getInt("id"), rs.getString("name"));
                result.add(row);
            }});
        Assert.assertEquals(1, result.size());
        jdbcTemplate.update("delete from test where name='name5'");
    }

    @Test
    public void testResultSet1() {
        jdbcTemplate.update("insert into "+T_USER+"(USERNAME) values('name5')");
        String listSql = "select * from "+T_USER;
        List result = jdbcTemplate.query(listSql, new RowMapper<Map>() {
            @Override
            public Map mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map row = new HashMap();
                row.put(rs.getInt("id"), rs.getString("name"));
                return row;
            }});
        Assert.assertEquals(1, result.size());
        jdbcTemplate.update("delete from "+T_USER+" where USERNAME='name5'");
    }

    @Test
    public void testPreparedStatement2() {
        String insertSql = "insert into "+T_USER+" (USERNAME) values (?)";
        int count = jdbcTemplate.update(insertSql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                pstmt.setObject(1, "name4");
            }});
        Assert.assertEquals(1, count);
        String deleteSql = "delete from"+T_USER+" where USERNAME=?";
        count = jdbcTemplate.update(deleteSql, new Object[] {"name4"});
        Assert.assertEquals(1, count);
    }

    @Test
    public void testPreparedStatement1() {
        int count = jdbcTemplate.execute(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                return conn.prepareStatement("select count(*) from " +T_USER);
            }
        }, new PreparedStatementCallback<Integer>() {
            @Override
            public Integer doInPreparedStatement(PreparedStatement pstmt)
                    throws SQLException, DataAccessException {
                pstmt.execute();
                ResultSet rs = pstmt.getResultSet();
                rs.next();
                return rs.getInt(1);
            }
        });
        Assert.assertEquals(0, count);
    }

    @Test
    public void testJdbcTemplate() {
        //1.声明SQL
        String sql = "select * from T_USER";
        jdbcTemplate.query(sql,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                //2.处理结果集
                String value = rs.getString("USERNAME");
                System.out.println("Column USERNAME:" + value);
            }
        });
    }


}
