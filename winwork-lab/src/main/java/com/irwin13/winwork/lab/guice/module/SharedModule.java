package com.irwin13.winwork.lab.guice.module;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.irwin13.winwork.basic.annotations.MDCLog;
import com.irwin13.winwork.basic.config.WinWorkConfig;
import com.irwin13.winwork.basic.log.MDCLogInterceptor;
import com.irwin13.winwork.basic.utilities.WinWorkVelocityUtil;
import com.irwin13.winwork.hibernate.guice.provider.HibernateSessionFactoryProvider;
import com.irwin13.winwork.lab.WinWorkLabConfig;
import com.irwin13.winwork.lab.dao.EntityResolver;
import com.irwin13.winwork.lab.dao.SessionFactoryEntityResolver;
import com.irwin13.winwork.lab.guice.provider.DataSourceProvider;
import com.irwin13.winwork.lab.guice.provider.ObjectMapperProvider;

/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class SharedModule extends AbstractModule {

    @Override
    protected void configure() {

        // JACKSON JSON MAPPER
        bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);

        // MDC Log AOP Interceptor
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(MDCLog.class), new MDCLogInterceptor());

        // Web Components
        bind(WinWorkVelocityUtil.class);

        // config
        bind(String.class).annotatedWith(Names.named("configFile")).toInstance("common-config.xml");
        bind(String.class).annotatedWith(Names.named("hibernateConfigFile")).toInstance("hibernate.cfg.xml");
        bind(WinWorkConfig.class).to(WinWorkLabConfig.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("webSessionStorageSystem")).toInstance("HttpSession");

        bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);
        bind(SessionFactory.class).toProvider(HibernateSessionFactoryProvider.class).in(Singleton.class);

        bind(EntityResolver.class).to(SessionFactoryEntityResolver.class);
    }
}
