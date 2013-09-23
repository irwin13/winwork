package id.co.quadras.winwork;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import id.co.quadras.winwork.shared.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 23/09/13 16:45
 */
public class WinWorkConfig extends AbstractConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(WinWorkConfig.class);

    private Configuration configuration;

    @Inject
    public WinWorkConfig(@Named("configFile") String file) {
        LOGGER.info("file config name = {}", file);
        ConfigurationFactory factory = new ConfigurationFactory();
        factory.setConfigurationFileName(file);
        try {
            configuration = factory.getConfiguration();
        } catch (ConfigurationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
