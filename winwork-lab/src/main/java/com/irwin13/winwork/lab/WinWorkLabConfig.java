package com.irwin13.winwork.lab;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.irwin13.winwork.basic.config.WinWorkConfig;

/**
 * @author irwin Timestamp : 23/09/13 16:45
 */
public class WinWorkLabConfig extends WinWorkConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WinWorkLabConfig.class);

    private Configuration configuration;

    @Inject
    public WinWorkLabConfig(@Named("configFile") String file) {
        LOGGER.info("file config name = {}", file);
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
