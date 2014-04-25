package com.irwin13.winwork.mybatis.guice.provider;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author irwin Timestamp : 17/04/2014 21:02
 */
@Singleton
public class SqlSessionFactoryProvider implements Provider<SqlSessionFactory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlSessionFactoryProvider.class);

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public SqlSessionFactory get() {
        if (sqlSessionFactory == null) {
            try {
                InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        return sqlSessionFactory;
    }
}
