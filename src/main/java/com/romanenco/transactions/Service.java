package com.romanenco.transactions;

import java.util.Map;

public interface Service {

    void insertIntoBoth(String primaryKey, String value);
    void insertIntoNonUnique(String primaryKey, String value);
    void insertIntoUnique(String primaryKey, String value);
    void resetDataSources();
    Map<String, String> listDataFromNonUnique();
    Map<String, String> listDataFromUnique();

}
