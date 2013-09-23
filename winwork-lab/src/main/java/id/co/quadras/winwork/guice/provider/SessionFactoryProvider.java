package id.co.quadras.winwork.guice.provider;

import com.google.inject.Provider;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 31/05/13 16:34
 */
public class SessionFactoryProvider implements Provider<SessionFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactoryProvider.class);
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory get() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
            LOGGER.info("Hibernate initialized successfully");
        }
        return sessionFactory;
    }
}
