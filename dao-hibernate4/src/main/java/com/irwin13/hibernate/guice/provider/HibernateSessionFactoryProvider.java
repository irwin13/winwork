package com.irwin13.hibernate.guice.provider;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author irwin Timestamp : 25/04/2014 16:48
 */
@Singleton
public class HibernateSessionFactoryProvider implements Provider<SessionFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateSessionFactoryProvider.class);
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory get() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory(new StandardServiceRegistryBuilder().build());
            LOGGER.debug("Hibernate initialized successfully");
        }
        return sessionFactory;
    }

}
