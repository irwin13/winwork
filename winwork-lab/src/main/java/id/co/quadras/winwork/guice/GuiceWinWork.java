package id.co.quadras.winwork.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import id.co.quadras.winwork.guice.module.*;
import id.co.quadras.winwork.util.LogUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 23/09/13 17:36
 */
public final class GuiceWinWork {

    private static Injector injector;

    private GuiceWinWork() {}

    private static List<AbstractModule> getModuleList() {
        List<AbstractModule> moduleList = new LinkedList<AbstractModule>();
        moduleList.add(new WinWorkModule());
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
