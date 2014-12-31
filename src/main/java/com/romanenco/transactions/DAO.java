package com.romanenco.transactions;

import java.util.Map;

public interface DAO {

    void insert(String primaryKey, String value);
    void truncate();
    Map<String, String> list();

}
