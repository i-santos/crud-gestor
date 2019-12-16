package com.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public abstract class DAO<T> {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    
    @Autowired
    private DataSource dataSource;
//
//    private void initDataSource() throws NamingException {
//        if (dataSource == null) {
//            Context ctx = new InitialContext();
//            DataSource dataSource;
//            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/AppGestor");
//
//            dataSource = dataSource;
//        }
//    }

    abstract public boolean create(T t);

    abstract public ArrayList<T> read();

    abstract public boolean update(T t);

    abstract public boolean delete(T t);

    abstract protected T toObject(ResultSet rs) throws SQLException;

    protected Connection getConnection() throws NamingException, SQLException {

//        initDataSource();

        return dataSource.getConnection();

    }

    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }

}
