package com.irwin13.winwork.mybatis.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import com.irwin13.winwork.mybatis.MyBatisConfig;
import com.irwin13.winwork.mybatis.dao.AppSettingDao;
import com.irwin13.winwork.mybatis.dao.AppSettingDaoImp;
import com.irwin13.winwork.mybatis.guice.provider.SqlSessionFactoryProvider;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author irwin Timestamp : 11/04/2014 14:42
 */
public class MyBatisModule extends AbstractModule {

    @Override
    protected void configure() {
        // general components
        bind(String.class)
                .annotatedWith(Names.named("configFile"))
                .toInstance("common-config.xml");

        bind(WinWorkConfig.class).to(MyBatisConfig.class).in(Singleton.class);

        bind(SqlSessionFactory.class).toProvider(SqlSessionFactoryProvider.class).in(Singleton.class);
        bind(AppSettingDao.class).to(AppSettingDaoImp.class);
    }
}
