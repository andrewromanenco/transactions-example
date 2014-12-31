package com.romanenco.transactions.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.romanenco.transactions.DistributedTX;

/**
 * Sample code to run in a container (this has to be full j2e server with
 * transaction manager installed in jndi)
 *
 * @author aromanenco
 *
 */
public class TestAction implements Controller {

    private static final Logger LOGGER = Logger.getLogger(TestAction.class);

    private final DistributedTX dTX;

    public TestAction(DistributedTX dTX) {
        super();
        this.dTX = dTX;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest req,
            HttpServletResponse res) throws Exception {

        dTX.success();
        final int successCount = dTX.getTotalCount();
        String exception = null;
        try {
            dTX.fail();
        } catch (Exception e) {
            LOGGER.error("On insert", e);
            exception = e.getClass().getName();
        }
        final int failCount = dTX.getTotalCount();
        res.getWriter().write(String.format(
                "Executed! after success: %d; after fail: %d; exception: %s",
                successCount,
                failCount,
                exception));
        return null;
    }

}
