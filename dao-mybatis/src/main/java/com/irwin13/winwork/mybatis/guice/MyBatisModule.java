package com.irwin13.winwork.mybatis.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.irwin13.winwork.mybatis.guice.provider.SqlSessionFactoryProvider;
import com.irwin13.winwork.mybatis.guice.provider.SqlSessionProvider;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author irwin Timestamp : 11/04/2014 14:42
 */
public class MyBatisModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SqlSessionFactory.class).toProvider(SqlSessionFactoryProvider.class).in(Singleton.class);
        bind(SqlSession.class).toProvider(SqlSessionProvider.class);
    }
}
