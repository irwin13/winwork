package id.co.quadras.winwork;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import id.co.quadras.winwork.guice.GuiceWinWork;
import id.co.quadras.winwork.util.StringCommon;
import id.co.quadras.winwork.util.WinWorkVelocityUtil;
import org.apache.velocity.app.Velocity;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.ServletContextEvent;
import java.util.Properties;

/**
 * @author irwin Timestamp : 23/09/13 16:45
 */
public class WinWorkContextListener extends GuiceServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WinWorkContextListener.class);
    public static final long START = System.currentTimeMillis();

    @Override
    protected Injector getInjector() {
        return GuiceWinWork.getInjector();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        MDC.put("nodeName", StringCommon.getNodeName());
        LOGGER.info("================================================================================================");
        LOGGER.info("=================================== Starting QifWebContextListener ===============================");

        String activateCache = servletContextEvent.getServletContext().getInitParameter("activateCache");
        String modificationCheckInterval = servletContextEvent.getServletContext().getInitParameter("modificationCheckInterval");
        String rootUrl = servletContextEvent.getServletContext().getInitParameter("rootUrl");

        LOGGER.info("init param activateCache = {}", activateCache);
        LOGGER.info("init param modificationCheckInterval = {}", modificationCheckInterval);
        LOGGER.info("init param rootUrl = {}", rootUrl);

        super.contextInitialized(servletContextEvent);
        initVelocity(activateCache, modificationCheckInterval, rootUrl);
    }

    private void initVelocity(String activateCache, String modificationCheckInterval, String rootUrl) {
        Properties velocityProperties = WinWorkVelocityUtil.urlProperties(activateCache, modificationCheckInterval, rootUrl);
        Velocity.init(velocityProperties);
        LOGGER.info("init VELOCITY with param = {}", velocityProperties);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        MDC.put("nodeName", StringCommon.getNodeName());
        LOGGER.info("================================================================================================");
        LOGGER.info("=================================== Shutdown QifWebContextListener ===============================");

        LOGGER.info("Shutdown Hibernate ...");
        SessionFactory sessionFactory = GuiceWinWork.getInjector().getInstance(SessionFactory.class);
        sessionFactory.close();
        LOGGER.info("Shutdown Hibernate complete");

        super.contextDestroyed(servletContextEvent);
    }

}
