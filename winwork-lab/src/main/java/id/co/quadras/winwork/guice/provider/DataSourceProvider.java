package id.co.quadras.winwork.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import id.co.quadras.winwork.shared.AbstractConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * @author irwin Timestamp : 31/05/13 17:02
 */
public class DataSourceProvider implements Provider<DataSource> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceProvider.class);

    private final AbstractConfiguration config;

    @Inject
    public DataSourceProvider(AbstractConfiguration config) {
        this.config = config;
    }

    @Override
    public DataSource get() {
        org.apache.tomcat.jdbc.pool.DataSource tomcatDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        tomcatDataSource.setPoolProperties(getPoolProperties());
        return tomcatDataSource;
    }

    private PoolProperties getPoolProperties() {
        PoolProperties poolProperties = new PoolProperties();

        LOGGER.info("Database properties : ");
        LOGGER.info("Database URL : {}", config.getString("database.url"));
        LOGGER.info("Database Driver : {}", config.getString("database.driver"));
        LOGGER.info("Database Schema : {}", config.getString("database.user"));
        LOGGER.info("Database Max Active : {}", config.getString("database.maxActive"));
        LOGGER.info("Database Max Idle : {}", config.getString("database.maxIdle"));

        poolProperties.setUrl(config.getString("database.url"));
        poolProperties.setDriverClassName(config.getString("database.driver"));
        poolProperties.setUsername(config.getString("database.user"));
        poolProperties.setPassword(config.getString("database.password"));

        poolProperties.setValidationQuery("SELECT 1 FROM DUAL");

        poolProperties.setTestOnBorrow(true);
        poolProperties.setJmxEnabled(false);
        poolProperties.setTestWhileIdle(false);
        poolProperties.setTestOnReturn(false);

        poolProperties.setInitialSize(10);
        poolProperties.setMaxActive(config.getInt("database.maxActive"));
        poolProperties.setMaxIdle(config.getInt("database.maxIdle"));
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
