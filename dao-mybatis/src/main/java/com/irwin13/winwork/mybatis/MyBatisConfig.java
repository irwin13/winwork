package com.irwin13.winwork.mybatis;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 21/04/2014 20:13
 */
public class MyBatisConfig extends WinWorkConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisConfig.class);

    private Configuration configuration;

    @Inject
    public MyBatisConfig(@Named("configFile") String file) {
        try {
            DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder(file);
            configuration = configurationBuilder.getConfiguration();
        } catch (ConfigurationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
