package com.irwin13.hibernate;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 28/04/2014 17:16
 */
public class HibernateConfig extends WinWorkConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateConfig.class);

    private Configuration configuration;

    @Inject
    public HibernateConfig(@Named("configFile") String file) {
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
