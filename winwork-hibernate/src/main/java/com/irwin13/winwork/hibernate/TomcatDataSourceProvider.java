package com.irwin13.winwork.hibernate;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.hibernate.HibernateException;
import org.hibernate.ejb.connection.InjectedDataSourceConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by irwin on 01/04/2015.
 */
public class TomcatDataSourceProvider extends InjectedDataSourceConnectionProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void configure(Properties props) throws HibernateException {
        org.apache.tomcat.jdbc.pool.DataSource tomcatDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        tomcatDataSource.setPoolProperties(getPoolProperties(props));
        setDataSource(tomcatDataSource);
        super.configure(props);
    }

    private PoolProperties getPoolProperties(Properties properties) {
        PoolProperties poolProperties = new PoolProperties();

        LOGGER.info("Hibernate properties : ");
        LOGGER.info("Database URL : {}", properties.getProperty("hibernate.connection.url"));
        LOGGER.info("Database Driver : {}", properties.getProperty("hibernate.connection.driver_class"));
        LOGGER.info("Database Schema : {}", properties.getProperty("hibernate.connection.username"));
        LOGGER.info("Database Max Active : {}", properties.getProperty("hibernate.connection.maxPoolSize"));
        LOGGER.info("Database Max Idle : {}", properties.getProperty("hibernate.connection.maxIdleSize"));

        poolProperties.setUrl(properties.getProperty("hibernate.connection.url"));
        poolProperties.setDriverClassName(properties.getProperty("hibernate.connection.driver_class"));
        poolProperties.setUsername(properties.getProperty("hibernate.connection.username"));
        poolProperties.setPassword(properties.getProperty("hibernate.connection.password"));

        poolProperties.setValidationQuery("SELECT 1 FROM DUAL");

        poolProperties.setTestOnBorrow(true);
        poolProperties.setJmxEnabled(false);
        poolProperties.setTestWhileIdle(false);
        poolProperties.setTestOnReturn(false);

        poolProperties.setInitialSize(Integer.valueOf(properties.getProperty("hibernate.connection.initialSize")));
        poolProperties.setMaxActive(Integer.valueOf(properties.getProperty("hibernate.connection.maxPoolSize")));
        poolProperties.setMaxIdle(Integer.valueOf(properties.getProperty("hibernate.connection.maxIdleSize")));
        poolProperties.setMinIdle(3);

        poolProperties.setMinEvictableIdleTimeMillis(10000);

        poolProperties.setValidationInterval(30000);
        poolProperties.setTimeBetweenEvictionRunsMillis(5000);

        poolProperties.setMaxWait(5000);

        poolProperties.setLogAbandoned(false);
        poolProperties.setRemoveAbandoned(true);
        poolProperties.setRemoveAbandonedTimeout(60);

        poolProperties.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        return poolProperties;
    }
}
