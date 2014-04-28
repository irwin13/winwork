package com.irwin13.hibernate.guice.provider;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
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
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            LOGGER.debug("Hibernate initialized successfully");
        }
        return sessionFactory;
    }

}
