package com.irwin13.winwork.core;

import com.irwin13.winwork.core.model.DecoratedToString;

/**
 * Created by irwin on 30/03/2015.
 */
public class WinWorkConfiguration extends DecoratedToString {

    private String velocityLoaderType;
    private boolean velocityActivateCache;
    private int velocityModificationCheckInterval;
    private String velocityRootResource;

    private String logFile;
    private int logFileMaxKeep;
    private String logFileMaxSize;
    private String logLevel;

    private String assetsRootUrl;

    public String getVelocityLoaderType() {
        return velocityLoaderType;
    }

    public void setVelocityLoaderType(String velocityLoaderType) {
        this.velocityLoaderType = velocityLoaderType;
    }

    public boolean isVelocityActivateCache() {
        return velocityActivateCache;
    }

    public void setVelocityActivateCache(boolean velocityActivateCache) {
        this.velocityActivateCache = velocityActivateCache;
    }

    public int getVelocityModificationCheckInterval() {
        return velocityModificationCheckInterval;
    }

    public void setVelocityModificationCheckInterval(int velocityModificationCheckInterval) {
        this.velocityModificationCheckInterval = velocityModificationCheckInterval;
    }

    public String getVelocityRootResource() {
        return velocityRootResource;
    }

    public void setVelocityRootResource(String velocityRootResource) {
        this.velocityRootResource = velocityRootResource;
    }

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
