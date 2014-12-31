package com.romanenco.transactions.impl;

import javax.sql.DataSource;

/**
 * Table for one of datasources
 *
 * @author aromanenco
 *
 */
public class NonUniqueDAOImpl extends DAOImpl {

    public NonUniqueDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void init() {
        jdbcTemplate.update("DROP TABLE sample_table IF EXISTS");
        jdbcTemplate.update("CREATE TABLE sample_table (p char(20) primary key, v char(20))");
    }

}
