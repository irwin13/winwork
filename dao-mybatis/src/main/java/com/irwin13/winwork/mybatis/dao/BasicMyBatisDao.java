package com.irwin13.winwork.mybatis.dao;

import com.google.common.base.Preconditions;
import com.irwin13.winwork.basic.annotations.MDCLog;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.utilities.PojoUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 17/04/2014 21:16
 */
public class BasicMyBatisDao<M extends Serializable, I extends Serializable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicMyBatisDao.class);

    public static final String SQLMAP = "Sqlmap";

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
    private final String mapperNamespace;

    public BasicMyBatisDao(Class<M> modelClass, SqlSessionFactory sqlSessionFactory, String mapperNamespace) {
        this.modelClass = modelClass;
        this.sqlSessionFactory = sqlSessionFactory;
        this.mapperNamespace = mapperNamespace;
    }

    public Class<M> getModelClass() {
        return modelClass;
    }

    public SqlSession openNewSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public SqlSession openNewSqlSession(ExecutorType executorType) {
        return sqlSessionFactory.openSession(executorType);
    }

    public void closeSqlSession(SqlSession session) {
        if (session != null) session.close();
    }

    public String getMapperName() {
        return mapperNamespace;
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
        return session.insert(getMapperName() + INSERT, model);
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
        return session.update(getMapperName() + UPDATE, model);
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
        return session.delete(getMapperName() + DELETE, model);
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> select(SqlSession session, M filter, SortParameter sortParameter) {
        if (filter == null) return Collections.EMPTY_LIST;
        Map<String, Object> parameterMap = PojoUtil.beanToMap(filter, true);
        if (sortParameter != null) {
            parameterMap.put("columnName", sortParameter.getColumnName());
            parameterMap.put("sortMethod", sortParameter.getSortMethod());
        } else {
            parameterMap.put("columnName", null);
            parameterMap.put("sortMethod", null);
        }
        LOGGER.trace("parameter map = {}", parameterMap);
        List<M> list = session.selectList(getMapperName() + SELECT, parameterMap);
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

        LOGGER.trace("Select Paged start = {}", fetchStart);
        LOGGER.trace("Select Paged size = {}", fetchSize);

        Map<String, Object> parameterMap = PojoUtil.beanToMap(filter, true);
        if (sortParameter != null) {
            parameterMap.put("columnName", sortParameter.getColumnName());
            parameterMap.put("sortMethod", sortParameter.getSortMethod());
        } else {
            parameterMap.put("columnName", null);
            parameterMap.put("sortMethod", null);
        }
        LOGGER.trace("parameter map = {}", parameterMap);
        List<M> list = session.selectList(getMapperName() + SELECT, parameterMap, new RowBounds(fetchStart, fetchSize));

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

        Map<String, Object> parameterMap = PojoUtil.beanToMap(filter, true);
        parameterMap.put("columnName", null);
        parameterMap.put("sortMethod", null);

        LOGGER.trace("parameter map = {}", parameterMap);
        result = (Long) session.selectOne(getMapperName() + SELECT_COUNT, parameterMap);
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
        if (searchParameter == null) {
            return Collections.EMPTY_LIST;
        }
        SearchParameter sp = new SearchParameter(searchParameter.getSearchKeyword() + "%",
                searchParameter.getColumnName(),
                searchParameter.getSortMethod());
        LOGGER.trace("searchParameter = {}", sp);
        List<M> list = session.selectList(getMapperName() + SELECT_SEARCH, sp);
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
        long result = 0;
        if (searchParameter == null) {
            return result;
        }
        SearchParameter sp = new SearchParameter(searchParameter.getSearchKeyword() + "%",
                searchParameter.getColumnName(),
                searchParameter.getSortMethod());
        LOGGER.trace("COUNT searchParameter = {}", sp);
        result = (Long) session.selectOne(getMapperName() + SELECT_SEARCH_COUNT, sp);
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

        if (searchParameter == null) {
            return Collections.EMPTY_LIST;
        }

        SearchParameter sp = new SearchParameter(searchParameter.getSearchKeyword() + "%",
                searchParameter.getColumnName(),
                searchParameter.getSortMethod());

        LOGGER.trace("PAGED searchParameter = {}", sp);
        LOGGER.trace("PAGED start = {}", fetchStart);
        LOGGER.trace("PAGED size = {}", fetchSize);

        List<M> list = session.selectList(getMapperName() + SELECT_SEARCH, sp,
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
        LOGGER.trace("Id = {}", id);
        if (id == null) return null;
        M object = (M) session.selectOne(getMapperName() + SELECT_BY_ID, id);
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

    public void batchInsert(List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            for (M model : modelList) {
                session.insert(getMapperName() + INSERT, model);
                LOGGER.trace("batch insert = {}", model);
            }
            session.flushStatements();
            session.commit();
        } finally {
            closeSqlSession(session);
        }
    }

    public void batchInsert(SqlSession session, List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        for (M model : modelList) {
            session.insert(getMapperName() + INSERT, model);
            LOGGER.trace("batch insert = {}", model);
        }
    }

    public void batchUpdate(List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            for (M model : modelList) {
                session.update(getMapperName() + UPDATE, model);
                LOGGER.trace("batch update = {}", model);
            }
            session.flushStatements();
            session.commit();
        } finally {
            closeSqlSession(session);
        }
    }

    public void batchUpdate(SqlSession session, List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        for (M model : modelList) {
            session.update(getMapperName() + UPDATE, model);
            LOGGER.trace("batch update = {}", model);
        }
    }

    public void batchDelete(List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            for (M model : modelList) {
                session.delete(getMapperName() + DELETE, model);
                LOGGER.trace("batch delete = {}", model);
            }
            session.flushStatements();
            session.commit();
        } finally {
            closeSqlSession(session);
        }
    }

    public void batchDelete(SqlSession session, List<M> modelList) {
        Preconditions.checkNotNull(modelList);
        for (M model : modelList) {
            session.delete(getMapperName() + DELETE, model);
            LOGGER.trace("batch delete = {}", model);
        }
    }

}
