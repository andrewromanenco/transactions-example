package com.romanenco.transactions;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * This is an example of local transaction management in code; database
 * related calls are actually placed in a transaction scope.
 *
 * @author aromanenco
 *
 */
public class LocalTXProgram {

    private final TransactionTemplate transactionTemplate;
    private final Service service;

    public LocalTXProgram(PlatformTransactionManager transactionManager, Service service) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.service = service;
    }

    public void success() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                service.insertIntoUnique("a1", "b1");
                service.insertIntoUnique("a2", "b2");
            }
        }
        );
    }

    public void fail() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus arg0) {
                service.insertIntoUnique("a1", "b1");
                service.insertIntoUnique("a2", "b2");
                service.insertIntoUnique("a2", "b2");  // this will FAIL, due to constraint
                service.insertIntoUnique("a4", "b4");
            }
        }
        );
    }

    public int count() {
        return service.listDataFromUnique().size();
    }

}
