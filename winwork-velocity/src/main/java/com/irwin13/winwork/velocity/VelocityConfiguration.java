package com.irwin13.winwork.velocity;

import com.irwin13.winwork.core.WinWorkConfiguration;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 31/03/2015.
 */
public class VelocityConfiguration extends WinWorkConfiguration {

    @NotNull
    private String velocityLoaderType;

    private boolean velocityActivateCache;

    @NotNull
    private Integer velocityModificationCheckInterval;

    @NotNull
    private String velocityRootResource;

    @NotNull
    private String assetsRootUrl;

    @NotNull
    private String contextRootUrl;

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

    public Integer getVelocityModificationCheckInterval() {
        return velocityModificationCheckInterval;
    }

    public void setVelocityModificationCheckInterval(Integer velocityModificationCheckInterval) {
        this.velocityModificationCheckInterval = velocityModificationCheckInterval;
    }

    public String getVelocityRootResource() {
        return velocityRootResource;
    }

    public void setVelocityRootResource(String velocityRootResource) {
        this.velocityRootResource = velocityRootResource;
    }

    public String getAssetsRootUrl() {
        return assetsRootUrl;
    }

    public void setAssetsRootUrl(String assetsRootUrl) {
        this.assetsRootUrl = assetsRootUrl;
    }

    public String getContextRootUrl() {
        return contextRootUrl;
    }

    public void setContextRootUrl(String contextRootUrl) {
        this.contextRootUrl = contextRootUrl;
    }
}
