package com.irwin13.winwork.velocity;

import com.irwin13.winwork.core.WinWorkComponent;
import com.irwin13.winwork.core.WinWorkConfiguration;
import com.irwin13.winwork.core.WinWorkException;

/**
 * Created by irwin on 01/04/2015.
 */
public class VelocityComponent implements WinWorkComponent<VelocityWrapper> {

    @Override
    public VelocityWrapper get(WinWorkConfiguration config) throws WinWorkException {
        VelocityConfiguration velocityConfiguration = (VelocityConfiguration) config;
        return new VelocityWrapper(velocityConfiguration);
    }

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public void start(WinWorkConfiguration config) throws WinWorkException {
        VelocityConfiguration velocityConfiguration = (VelocityConfiguration) config;

        VelocityWrapper.initVelocity(velocityConfiguration.getVelocityLoaderType(),
                String.valueOf(velocityConfiguration.isVelocityActivateCache()),
                velocityConfiguration.getVelocityModificationCheckInterval().toString(),
                velocityConfiguration.getVelocityRootResource());
    }

    @Override
    public void stop(WinWorkConfiguration config) throws WinWorkException {

    }
}
