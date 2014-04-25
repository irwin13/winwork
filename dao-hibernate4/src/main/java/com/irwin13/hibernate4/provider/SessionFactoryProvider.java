package com.irwin13.hibernate4.provider;

import com.google.inject.Singleton;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;

/**
 * @author irwin Timestamp : 25/04/2014 16:48
 */
@Singleton
public class SessionFactoryProvider implements Provider<SessionFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactoryProvider.class);
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
