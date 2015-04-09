package com.irwin13.winresto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by irwin on 4/7/15.
 */
@WebListener
public class WinRestoListener implements ServletContextListener {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("========================================================================================");
        logger.info("=================================== Starting Application ===============================");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("========================================================================================");
        logger.info("=================================== Shutdown Application ===============================");

    }
}
