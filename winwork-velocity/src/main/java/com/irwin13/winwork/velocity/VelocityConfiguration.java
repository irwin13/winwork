package com.irwin13.winwork.velocity;

import com.irwin13.winwork.core.model.DecoratedToString;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 31/03/2015.
 */
public class VelocityConfiguration extends DecoratedToString {

    @NotNull
    private String velocityLoaderType;
    private boolean velocityActivateCache;
    @NotNull
    private Integer velocityModificationCheckInterval;
    @NotNull
    private String velocityRootResource;

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
}
