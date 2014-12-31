package com.romanenco.transactions;


/**
 * Layer to be covered by distributed transaction management.
 * Configured in spring.
 *
 * @author aromanenco
 *
 */
public class DistributedTX {

    private final Service service;

    public DistributedTX(Service service) {
        super();
        this.service = service;
    }

    public void success() {
        service.insertIntoBoth("a1", "b1");
        service.insertIntoBoth("a2", "b2");
    }

    public void fail() {
        service.insertIntoBoth("a1", "b1");
        service.insertIntoBoth("a2", "b2");
        service.insertIntoBoth("a3", "b2");  // will fail due to constraint
        service.insertIntoBoth("a4", "b4");
    }

    public int getTotalCount() {
        return service.listDataFromNonUnique().size()
                + service.listDataFromUnique().size();
    }

}
