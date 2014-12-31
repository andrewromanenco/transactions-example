package com.romanenco.transactions;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;

/**
 * Test cases for programming transaction management.
 * LocalTXProgram has transaction manager injected.
 *
 * @author aromanenco
 *
 */
public class LocalTXProgramTest {

    @Test
    public void testSuccess() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("local-context.xml");
        final LocalTXProgram ltx = (LocalTXProgram) ctx.getBean("localTxProgram");
        ltx.success();
        Assert.assertEquals(2, ltx.count());
    }

    @Test
    public void testFail() {
        @SuppressWarnings("resource")
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("local-context.xml");
        final LocalTXProgram ltx = (LocalTXProgram) ctx.getBean("localTxProgram");
        boolean duplicationDetected = false;
        try {
            ltx.fail();
        } catch (DuplicateKeyException e) {
            duplicationDetected = true;
        }
        Assert.assertTrue(duplicationDetected);
        Assert.assertEquals(0, ltx.count());  // due to programming transaction
                                              // marking, all actions are rolled back
    }

}
