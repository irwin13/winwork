package com.irwin13.winwork.mybatis.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author irwin Timestamp : 17/04/2014 21:02
 */
public class SqlSessionProvider implements Provider<SqlSession> {

    private final SqlSessionFactory sqlSessionFactory;

    @Inject
    public SqlSessionProvider(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public SqlSession get() {
        return sqlSessionFactory.openSession();
    }
}
