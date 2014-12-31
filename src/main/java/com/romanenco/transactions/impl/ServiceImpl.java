package com.romanenco.transactions.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.romanenco.transactions.DAO;
import com.romanenco.transactions.Service;

/**
 * Service to work with both datasources. Both datasources have to share a
 * transaction scope.
 *
 * @author aromanenco
 *
 */
public class ServiceImpl implements Service {

    private static final Logger LOGGER = Logger.getLogger(ServiceImpl.class);

    private final DAO unique;
    private final DAO nonUnique;

    public ServiceImpl(DAO unique, DAO nonUnique) {
        super();
        this.unique = unique;
        this.nonUnique = nonUnique;
    }

    @Override
    public void insertIntoBoth(String primaryKey, String value) {
        LOGGER.info("Inserting to both");
        nonUnique.insert(primaryKey, value);
        LOGGER.info("NonUnique - Done");
        unique.insert(primaryKey, value);
        LOGGER.info("Unique - Done");
    }

    @Override
    public void insertIntoNonUnique(String primaryKey, String value) {
        nonUnique.insert(primaryKey, value);
    }

    @Override
    public void insertIntoUnique(String primaryKey, String value) {
        unique.insert(primaryKey, value);
    }

    @Override
    public void resetDataSources() {
        unique.truncate();
        nonUnique.truncate();
    }

    @Override
    public Map<String, String> listDataFromNonUnique() {
        return nonUnique.list();
    }

    @Override
    public Map<String, String> listDataFromUnique() {
        return unique.list();
    }

}
