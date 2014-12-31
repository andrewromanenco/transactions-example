package com.romanenco.transactions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;

/**
 * LocalTX instance is managed by spring and transaction scope is set as well.
 *
 * See, how behavior is different from NoTXTest - now all db inserts share the
 * same transactions instead auto-commit.
 *
 * @author aromanenco
 *
 */
public class LocalTXTest {

    @Test
    public void testSuccess() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx =
            new ClassPathXmlApplicationContext("local-context.xml");
        final LocalTX ltx = (LocalTX) ctx.getBean("localTx");
        ltx.success();
        Assert.assertEquals(2, ltx.count());
    }

    @Test
    public void testFail() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx =
            new ClassPathXmlApplicationContext("local-context.xml");
        final LocalTX ltx = (LocalTX) ctx.getBean("localTx");
        boolean duplicationDetected = false;
        try {
            ltx.fail();
        } catch (DuplicateKeyException e) {
            duplicationDetected = true;
        }
        Assert.assertTrue(duplicationDetected);
        Assert.assertEquals(0, ltx.count());  // all records were rolled back
    }

}
