package com.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//名字默认是类名首字母小写
@Repository
public class PersonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert() {
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        String username = UUID.randomUUID().toString();
        jdbcTemplate.update(sql, username, 19);
    }

}
