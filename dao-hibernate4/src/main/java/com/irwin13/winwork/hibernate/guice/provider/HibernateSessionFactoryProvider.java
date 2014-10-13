package com.irwin13.winwork.hibernate.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
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
    private final String hibernateConfigFile;

    @Inject
    public HibernateSessionFactoryProvider(@Named("hibernateConfigFile") String hibernateConfigFile) {
        this.hibernateConfigFile = hibernateConfigFile;
    }

    @Override
    public SessionFactory get() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.configure(hibernateConfigFile);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            LOGGER.info("Hibernate initialized successfully");
        }
        return sessionFactory;
    }

}
