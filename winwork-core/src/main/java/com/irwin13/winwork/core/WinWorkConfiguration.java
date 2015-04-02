package com.irwin13.winwork.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Strings;
import com.irwin13.winwork.core.model.DecoratedToString;

import java.io.File;
import java.io.IOException;

/**
 * Created by irwin on 01/04/2015.
 */
public abstract class WinWorkConfiguration extends DecoratedToString {

    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private static Object CONFIG;

    public static <T> T getConfig(Class<T> type) {
        String configFile = System.getProperty(WinWorkConstants.WINWORK_CONFIG_FILE);
        if (Strings.isNullOrEmpty(configFile)) {
            throw new WinWorkException("ERROR : missing startup parameter '" + WinWorkConstants.WINWORK_CONFIG_FILE + "' " +
                    "for example -D" + WinWorkConstants.WINWORK_CONFIG_FILE + "=config.yaml");
        }

        if (CONFIG == null) {
            try {
                CONFIG = objectMapper.readValue(new File(configFile), type);
            } catch (IOException e) {
                throw new WinWorkException(e);
            }
        }

        return (T) CONFIG;
    }
}
