package com.romanenco.transactions;

/**
 * This layer can be run under declarative transaction management or as is.
 * See test cases for more details.
 *
 * @author aromanenco
 *
 */
public class LocalTX {

    private final Service service;

    public LocalTX(Service service) {
        this.service = service;
    }

    public void success() {
        service.insertIntoUnique("a1", "b1");
        service.insertIntoUnique("a2", "b2");
    }

    public void fail() {
        service.insertIntoUnique("a1", "b1");
        service.insertIntoUnique("a2", "b2");
        service.insertIntoUnique("a2", "b2");
        service.insertIntoUnique("a4", "b4");
    }

    public int count() {
        return service.listDataFromUnique().size();
    }

}
