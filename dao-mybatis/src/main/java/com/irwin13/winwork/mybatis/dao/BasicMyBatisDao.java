package com.irwin13.winwork.mybatis.dao;

import com.irwin13.winwork.basic.annotations.MDCLog;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author irwin Timestamp : 17/04/2014 21:16
 */
public class BasicMyBatisDao<M extends Serializable, I extends Serializable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicMyBatisDao.class);
    private static final String INSERT = ".insert";
    private static final String UPDATE = ".update";
    private static final String DELETE = ".delete";
    private static final String SELECT_BY_ID = ".selectById";
    private static final String SELECT = ".select";
    private static final String SELECT_COUNT = ".selectCount";
    private static final String SELECT_SEARCH = ".selectSearch";
    private static final String SELECT_SEARCH_COUNT = ".selectSearchCount";

    private final Class<M> modelClass;
    private final SqlSessionFactory sqlSessionFactory;

    public BasicMyBatisDao(Class<M> modelClass, SqlSessionFactory sqlSessionFactory) {
        this.modelClass = modelClass;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Class<M> getModelClass() {
        return modelClass;
    }

    public SqlSession openNewSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public void closeSqlSession(SqlSession session) {
        if (session != session) session.close();
    }

    public int insert(M model) {
        SqlSession session = null;
        int result = 0;

        try {
            session = openNewSqlSession();
            result = insert(session, model);
            session.commit();
        } finally {
            closeSqlSession(session);
        }

        return result;
    }

    public int insert(SqlSession session, M model) {
        return session.insert(getModelClass().getCanonicalName() + INSERT, model);
    }

    public int update(M model) {
        SqlSession session = null;
        int result = 0;
        try {
            session = openNewSqlSession();
            result = update(session, model);
            session.commit();
        } finally {
            closeSqlSession(session);
        }
        return result;
    }

    public int update(SqlSession session, M model) {
        return session.update(getModelClass().getCanonicalName() + UPDATE, model);
    }

    public int delete(M model) {
        SqlSession session = null;
        int result = 0;
        try {
            session = openNewSqlSession();
            result = delete(session, model);
            session.commit();
        } finally {
            closeSqlSession(session);
        }

        return result;
    }

    public int delete(SqlSession session, M model) {
        return session.delete(getModelClass().getCanonicalName() + DELETE, model);
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> select(SqlSession session, M filter, SortParameter sortParameter) {
        if (filter == null) return Collections.EMPTY_LIST;
        List<M> list = session.selectList(getModelClass().getCanonicalName() + SELECT,
                new Object[]{filter, sortParameter});
        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> select(M filter, SortParameter sortParameter) {
        SqlSession session = null;
        List<M> list = null;

        try {
            session = openNewSqlSession();
            list = select(session, filter, sortParameter);
        } finally {
            closeSqlSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> select(SqlSession session, M filter, SortParameter sortParameter, int fetchStart, int fetchSize) {
        if (filter == null) return Collections.EMPTY_LIST;

        LOGGER.debug("Select Paged start = {}", fetchStart);
        LOGGER.debug("Select Paged size = {}", fetchSize);

        List<M> list = session.selectList(getModelClass().getCanonicalName() + SELECT,
                new Object[]{filter, sortParameter}, new RowBounds(fetchStart, fetchSize));

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> select(M filter, SortParameter sortParameter, int fetchStart, int fetchSize) {
        SqlSession session = null;
        List<M> list = null;

        try {
            session = openNewSqlSession();
            list = select(session, filter, sortParameter, fetchStart, fetchSize);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSqlSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @MDCLog
    public long selectCount(SqlSession session, M filter) {
        long result = 0;
        if (filter == null) return result;
        result = (Long) session.selectOne(getModelClass().getCanonicalName() + SELECT_COUNT, filter);
        return result;
    }

    public long selectCount(M filter) {
        SqlSession session = null;
        long result = 0;

        try {
            session = openNewSqlSession();
            result = selectCount(session, filter);
        } finally {
            closeSqlSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> selectSearch(SqlSession session, SearchParameter searchParameter) {
        LOGGER.debug("searchParameter = {}", searchParameter);
        List<M> list = session.selectList(getModelClass().getCanonicalName() + SELECT_SEARCH, searchParameter);
        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(SearchParameter searchParameter) {

        SqlSession session = null;
        List<M> list = null;

        try {
            session = openNewSqlSession();
            list = selectSearch(session, searchParameter);
        } finally {
            closeSqlSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @MDCLog
    public long selectSearchCount(SqlSession session, SearchParameter searchParameter) {
        long result;
        LOGGER.debug("COUNT searchParameter = {}", searchParameter);
        result = (Long) session.selectOne(getModelClass().getCanonicalName() + SELECT_SEARCH_COUNT, searchParameter);
        return result;
    }

    public long selectSearchCount(SearchParameter searchParameter) {

        SqlSession session = null;
        long result = 0;

        try {
            session = openNewSqlSession();
            result = selectSearchCount(session, searchParameter);
        } finally {
            closeSqlSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> selectSearch(SqlSession session, SearchParameter searchParameter,
                                     int fetchStart, int fetchSize) {

        LOGGER.debug("PAGED searchParameter = {}", searchParameter);
        LOGGER.debug("PAGED start = {}", fetchStart);
        LOGGER.debug("PAGED size = {}", fetchSize);

        List<M> list = session.selectList(getModelClass().getCanonicalName() + SELECT_SEARCH, searchParameter,
                new RowBounds(fetchStart, fetchSize));

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(SearchParameter searchParameter, int fetchStart, int fetchSize) {

        SqlSession session = null;
        List<M> list = null;

        try {
            session = openNewSqlSession();
            list = selectSearch(session, searchParameter, fetchStart, fetchSize);
        } finally {
            closeSqlSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public M selectById(SqlSession session, I id, boolean fetchChild) {
        LOGGER.debug("Id = {}", id);
        M object = (M) session.selectOne(getModelClass().getCanonicalName() + SELECT_BY_ID, id);
        if (fetchChild) {
            // TODO different query with child
        }
        return object;
    }

    public M selectById(I id, boolean fetchChild) {
        SqlSession session = null;
        M result = null;

        try {
            session = openNewSqlSession();
            result = selectById(session, id, fetchChild);
        } finally {
            closeSqlSession(session);
        }

        return result;
    }
}
