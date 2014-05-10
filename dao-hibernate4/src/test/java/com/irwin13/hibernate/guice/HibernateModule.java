package com.irwin13.hibernate.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.irwin13.hibernate.HibernateConfig;
import com.irwin13.hibernate.dao.AppSettingDao;
import com.irwin13.hibernate.dao.AppSettingDaoImp;
import com.irwin13.hibernate.guice.provider.HibernateSessionFactoryProvider;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import org.hibernate.SessionFactory;

/**
 * @author irwin Timestamp : 28/04/2014 17:15
 */
public class HibernateModule extends AbstractModule {

    @Override
    protected void configure() {
        // general components
        bind(String.class)
                .annotatedWith(Names.named("configFile"))
                .toInstance("common-config.xml");

        bind(String.class)
                .annotatedWith(Names.named("hibernateConfigFile"))
                .toInstance("hibernate.cfg.xml");

        bind(WinWorkConfig.class).to(HibernateConfig.class).in(Singleton.class);
        bind(SessionFactory.class).toProvider(HibernateSessionFactoryProvider.class).in(Singleton.class);
        bind(AppSettingDao.class).to(AppSettingDaoImp.class);
    }
}
