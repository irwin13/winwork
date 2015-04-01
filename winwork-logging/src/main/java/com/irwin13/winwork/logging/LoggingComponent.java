package com.irwin13.winwork.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.irwin13.winwork.core.WinWorkComponent;
import com.irwin13.winwork.core.WinWorkConfiguration;
import com.irwin13.winwork.core.WinWorkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by irwin on 01/04/2015.
 */
public class LoggingComponent implements WinWorkComponent {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object get(WinWorkConfiguration config) {
        return null;
    }

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public void start(WinWorkConfiguration config) {
        LoggingConfiguration logConfig = (LoggingConfiguration) config;

        logger.info("Configure Logging : \n");
        logger.info(logConfig.toString());

        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator jc = new JoranConfigurator();
        jc.setContext(context);
        // override default configuration
        context.reset();

        context.putProperty("logFile", logConfig.getLogFile());
        context.putProperty("logFileMaxKeep", String.valueOf(logConfig.getLogFileMaxKeep()));
        context.putProperty("logFileMaxSize", logConfig.getLogFileMaxSize());
        context.putProperty("logLevel", logConfig.getLogLevel());

        try {
            jc.doConfigure(logConfig.getLogConfigFile());
        } catch (JoranException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw new WinWorkException(e);
        }

    }

    @Override
    public void stop(WinWorkConfiguration config) {

    }
}
