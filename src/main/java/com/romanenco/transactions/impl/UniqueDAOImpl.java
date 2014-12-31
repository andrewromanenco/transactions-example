package com.romanenco.transactions.impl;

import javax.sql.DataSource;

/**
 * Table with additional unique constraint
 *
 * @author aromanenco
 *
 */
public class UniqueDAOImpl extends DAOImpl {

    public UniqueDAOImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void init() {
        jdbcTemplate.update("DROP TABLE sample_table IF EXISTS");  // due to hsqldb mem sharing
        jdbcTemplate.update("CREATE TABLE sample_table (p char(20) primary key," +
        		" v char(20) unique)");
    }

}
