package com.irwin13.winwork.logging;

import com.irwin13.winwork.core.WinWorkConfiguration;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 01/04/2015.
 */
public class LoggingConfiguration extends WinWorkConfiguration {

    @NotNull
    private String logConfigFile;
    @NotNull
    private String logFile;
    @NotNull
    private Integer logFileMaxKeep;
    @NotNull
    private String logFileMaxSize;
    @NotNull
    private String logLevel;

    public String getLogConfigFile() {
        return logConfigFile;
    }

    public void setLogConfigFile(String logConfigFile) {
        this.logConfigFile = logConfigFile;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public Integer getLogFileMaxKeep() {
        return logFileMaxKeep;
    }

    public void setLogFileMaxKeep(Integer logFileMaxKeep) {
        this.logFileMaxKeep = logFileMaxKeep;
    }

    public String getLogFileMaxSize() {
        return logFileMaxSize;
    }

    public void setLogFileMaxSize(String logFileMaxSize) {
        this.logFileMaxSize = logFileMaxSize;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
}
