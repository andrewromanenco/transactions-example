package com.romanenco.transactions.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.romanenco.transactions.DAO;

/**
 * Classic dao for insert, read, delete
 *
 * @author aromanenco
 *
 */
public abstract class DAOImpl implements DAO {

    protected final JdbcTemplate jdbcTemplate;

    public DAOImpl(DataSource dataSource) {
        super();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public abstract void init();

    @Override
    public void insert(String primaryKey, String value) {
        jdbcTemplate.update("INSERT INTO sample_table (p, v) VALUES(?, ?)",
                primaryKey,
                value);
    }

    @Override
    public void truncate() {
        jdbcTemplate.update("TRUNCATE TABLE sample_table");
    }

    @Override
    public Map<String, String> list() {
        final List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT p, v FROM sample_table");
        final Map<String, String> result = new HashMap<>();
        for (Map<String, Object> map: list) {
            result.put((String)map.get("p"), (String)map.get("v"));
        }
        return result;
    }


}
