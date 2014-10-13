package com.irwin13.winwork.lab.guice;

import java.util.LinkedList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.irwin13.winwork.basic.log.LogUtil;
import com.irwin13.winwork.lab.guice.module.DaoModule;
import com.irwin13.winwork.lab.guice.module.ServiceModule;
import com.irwin13.winwork.lab.guice.module.SharedModule;
import com.irwin13.winwork.lab.guice.module.ValidatorModule;
import com.irwin13.winwork.lab.guice.module.WebModule;

/**
 * @author irwin Timestamp : 23/09/13 17:36
 */
public final class GuiceWinWork {

    private static Injector injector;

    private GuiceWinWork() {}

    private static List<AbstractModule> getModuleList() {
        List<AbstractModule> moduleList = new LinkedList<AbstractModule>();
        moduleList.add(new SharedModule());
        moduleList.add(new DaoModule());
        moduleList.add(new ServiceModule());
        moduleList.add(new WebModule());
        moduleList.add(new ValidatorModule());
        return moduleList;
    }

    public static Injector getInjector() {
        LogUtil.setNodeNameMDC();
        if (injector == null) {
            injector = Guice.createInjector(getModuleList());
        }
        return injector;
    }

}
