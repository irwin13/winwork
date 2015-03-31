package com.irwin13.winwork.core;

import com.irwin13.winwork.core.model.DecoratedToString;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 30/03/2015.
 */
public class WinWorkConfiguration extends DecoratedToString {

    @NotNull
    private String logFile;
    @NotNull
    private Integer logFileMaxKeep;
    @NotNull
    private String logFileMaxSize;
    @NotNull
    private String logLevel;
    @NotNull
    private String assetsRootUrl;

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public int getLogFileMaxKeep() {
        return logFileMaxKeep;
    }

    public void setLogFileMaxKeep(int logFileMaxKeep) {
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

    public String getAssetsRootUrl() {
        return assetsRootUrl;
    }

    public void setAssetsRootUrl(String assetsRootUrl) {
        this.assetsRootUrl = assetsRootUrl;
    }
}
