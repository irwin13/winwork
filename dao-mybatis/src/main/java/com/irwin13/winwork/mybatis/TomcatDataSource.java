package com.irwin13.winwork.mybatis;

import com.google.common.base.Strings;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.Set;

/**
 * @author irwin Timestamp : 22/04/2014 18:52
 */
public class TomcatDataSource implements DataSourceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TomcatDataSource.class);
    private final PoolProperties poolProperties = new PoolProperties();

    @Override
    public void setProperties(Properties properties) {

        LOGGER.info("===============================================");
        LOGGER.info("Initialize Tomcat Data Source with properties :");
        Set<String> propertyNames = properties.stringPropertyNames();
        for (String propertyKey : propertyNames) {
            if (!propertyKey.contains("password")) {
                LOGGER.info("{} = {}", propertyKey, properties.getProperty(propertyKey));
            } else {
                LOGGER.info("{} = {}", propertyKey, "*****");
            }
        }
        LOGGER.info("===============================================");

        poolProperties.setUrl(properties.getProperty("tomcat.connectionPool.url"));
        poolProperties.setDriverClassName(properties.getProperty("tomcat.connectionPool.driver"));
        poolProperties.setUsername(properties.getProperty("tomcat.connectionPool.username"));
        poolProperties.setPassword(properties.getProperty("tomcat.connectionPool.password"));

        poolProperties.setValidationQuery(properties.getProperty("tomcat.connectionPool.validationQuery"));

        poolProperties.setTestOnBorrow(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.testOnBorrow")));
        poolProperties.setJmxEnabled(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.jmxEnabled")));
        poolProperties.setTestWhileIdle(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.testWhileIdle")));
        poolProperties.setTestOnReturn(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.testOnReturn")));

        poolProperties.setInitialSize(Integer.valueOf(properties.getProperty("tomcat.connectionPool.initialSize")));
        poolProperties.setMaxActive(Integer.valueOf(properties.getProperty("tomcat.connectionPool.maxActive")));
        poolProperties.setMaxIdle(Integer.valueOf(properties.getProperty("tomcat.connectionPool.maxIdle")));
        poolProperties.setMinIdle(Integer.valueOf(properties.getProperty("tomcat.connectionPool.minIdle")));

        poolProperties.setMinEvictableIdleTimeMillis(Integer.valueOf(properties.getProperty("tomcat.connectionPool.minEvictableIdleTimeMillis")));

        poolProperties.setValidationInterval(Integer.valueOf(properties.getProperty("tomcat.connectionPool.validationInterval")));
        poolProperties.setTimeBetweenEvictionRunsMillis(Integer.valueOf(properties.getProperty("tomcat.connectionPool.timeBetweenEvictionRunsMillis")));

        String maxWait = properties.getProperty("tomcat.connectionPool.maxWait");
        if (!Strings.isNullOrEmpty(maxWait)) {
            poolProperties.setMaxWait(Integer.valueOf(maxWait));
        }

        poolProperties.setLogAbandoned(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.logAbandoned")));
        poolProperties.setRemoveAbandoned(Boolean.valueOf(properties.getProperty("tomcat.connectionPool.removeAbandoned")));
        poolProperties.setRemoveAbandonedTimeout(Integer.valueOf(properties.getProperty("tomcat.connectionPool.removeAbandonedTimeout")));

        poolProperties.setJdbcInterceptors(properties.getProperty("tomcat.connectionPool.jdbcInterceptors"));

    }

    @Override
    public DataSource getDataSource() {
        DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        ((org.apache.tomcat.jdbc.pool.DataSource) dataSource).setPoolProperties(poolProperties);
        return dataSource;
    }
}
