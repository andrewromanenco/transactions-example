package com.romanenco.transactions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;

/**
 * No transaction management, all db call are auto-committed.
 *
 * Pay attention how LocalTX instance is created: it lives outside spring
 * context and spring is not managing transaction scoping in any way.
 *
 * "Service" has no transaction management configured.
 * Compare to LocalTXTest.
 *
 * @author aromanenco
 *
 */
public class NoTXTest {

    @Test
    public void testSuccess() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("local-context.xml");
        final Service service = (Service)ctx.getBean("service");
        final LocalTX ltx = new LocalTX(service);
        ltx.success();
        Assert.assertEquals(2, ltx.count());
    }

    @Test
    public void testFail() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("local-context.xml");
        final Service service = (Service)ctx.getBean("service");
        final LocalTX ltx = new LocalTX(service);
        boolean duplicationDetected = false;
        try {
            ltx.fail();
        } catch (DuplicateKeyException e) {
            duplicationDetected = true;
        }
        Assert.assertTrue(duplicationDetected);
        Assert.assertEquals(2, ltx.count());  // some records are still there
    }

}
