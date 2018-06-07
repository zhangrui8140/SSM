package business.dao;

import business.table.HelloWorldTable;
import org.springframework.stereotype.Repository;

@Repository("HelloWorldMapper")
public interface HelloWorldMapper {
    HelloWorldTable selectByPrimaryKey(String authcd);
}
