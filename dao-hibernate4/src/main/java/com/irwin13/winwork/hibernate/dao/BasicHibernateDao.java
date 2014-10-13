package com.irwin13.winwork.hibernate.dao;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.annotations.MDCLog;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;
import com.irwin13.winwork.hibernate.HibernateQueryUtil;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 28/04/2014 17:07
 */
public class BasicHibernateDao<M extends Serializable, I extends Serializable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicHibernateDao.class);

    private final Class<M> modelClass;

    public BasicHibernateDao(Class<M> modelClass) {
        this.modelClass = modelClass;
    }

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<M> getModelClass() {
        return modelClass;
    }

    public Session openNewSession() {
        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        if (session != session) session.close();
    }

    public I insert(M model) {
        Session session = null;
        Transaction tx = null;
        I object = null;

        try {
            session = openNewSession();
            tx = session.beginTransaction();
            object = insert(session, model);
            tx.commit();
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            closeSession(session);
        }

        return object;
    }

    public I insert(Session session, M model) {
        return (I) session.save(model);
    }


    public void update(M model) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openNewSession();
            tx = session.beginTransaction();
            update(session, model);
            tx.commit();
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            closeSession(session);
        }
    }

    public void update(Session session, M model) {
        session.update(model);
    }

    public void merge(M model) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openNewSession();
            tx = session.beginTransaction();
            merge(session, model);
            tx.commit();
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            closeSession(session);
        }
    }

    public void merge(Session session, M model) {
        session.merge(model);
    }

    public void delete(M model) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openNewSession();
            tx = session.beginTransaction();
            delete(session, model);
            tx.commit();
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            closeSession(session);
        }
    }

    public void delete(Session session, M model) {
        session.delete(model);
    }

    public void saveOrUpdate(M model) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openNewSession();
            tx = session.beginTransaction();
            saveOrUpdate(session, model);
            tx.commit();
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
        } finally {
            closeSession(session);
        }
    }

    public void saveOrUpdate(Session session, M model) {
        session.saveOrUpdate(model);
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> select(Session session, M filter, SortParameter sortParameter) {
        if (filter == null) return Collections.EMPTY_LIST;

        String sqlQuery = HibernateQueryUtil.buildModelQuery(filter, false, sortParameter);
        LOGGER.trace("Select QUERY = {}", sqlQuery);

        Query query = session.createQuery(sqlQuery);
        query.setProperties(filter);
        List<M> list = query.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> select(M filter, SortParameter sortParameter) {
        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = select(session, filter, sortParameter);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> select(Session session, M filter,  SortParameter sortParameter, int fetchStart, int fetchSize) {
        if (filter == null) return Collections.EMPTY_LIST;

        String sqlQuery = HibernateQueryUtil.buildModelQuery(filter, false, sortParameter);
        LOGGER.trace("Select Paged QUERY = {}", sqlQuery);
        LOGGER.trace("Select Paged start = {}", fetchStart);
        LOGGER.trace("Select Paged size = {}", fetchSize);

        Query query = session.createQuery(sqlQuery);
        query.setProperties(filter);
        query.setFirstResult(fetchStart);
        query.setMaxResults(fetchSize);
        List<M> list = query.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> select(M filter, SortParameter sortParameter, int fetchStart, int fetchSize) {
        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = select(session, filter, sortParameter, fetchStart, fetchSize);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @MDCLog
    public long selectCount(Session session, M filter) {
        long result = 0;
        if (filter == null) return result;

        String sqlQuery = HibernateQueryUtil.buildModelQuery(filter, true, null);
        LOGGER.trace("COUNT Query = {}", sqlQuery);

        Query query = session.createQuery(sqlQuery);
        query.setProperties(filter);
        result = (Long) query.uniqueResult();

        return result;
    }

    public long selectCount(M filter) {
        Session session = null;
        long result = 0;

        try {
            session = openNewSession();
            result = selectCount(session, filter);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> selectSearch(Session session, SearchParameter searchParameter, Map<String, Class<?>> searchProperties) {

        LOGGER.trace("searchParameter = {}", searchParameter);
        LOGGER.trace("searchProperties = {}", searchProperties);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchParameter.getSearchKeyword(), searchProperties,
                false, new SortParameter(searchParameter.getColumnName(), searchParameter.getSortMethod()));

        LOGGER.trace("selectSearch QUERY {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchParameter.getSearchKeyword())) {
            HibernateQueryUtil.setSearchParameter(q, searchParameter.getSearchKeyword(), searchProperties);
        }

        if (WinWorkBasicEntity.class.isAssignableFrom(modelClass)) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }

        List<M> list = q.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(SearchParameter searchParameter, Map<String, Class<?>> searchProperties) {

        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectSearch(session, searchParameter, searchProperties);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @MDCLog
    public long selectSearchCount(Session session, SearchParameter searchParameter, Map<String, Class<?>> searchProperties) {
        long result;

        LOGGER.trace("COUNT searchParameter = {}", searchParameter);
        LOGGER.trace("COUNT searchProperties = {}", searchProperties);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchParameter.getSearchKeyword(),
                searchProperties, true, null);

        LOGGER.trace("COUNT selectSearchCount QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchParameter.getSearchKeyword())) {
            HibernateQueryUtil.setSearchParameter(q, searchParameter.getSearchKeyword(), searchProperties);
        }
        if (WinWorkBasicEntity.class.isAssignableFrom(modelClass)) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }

        result = (Long) q.uniqueResult();

        return result;
    }

    public long selectSearchCount(SearchParameter searchParameter, Map<String, Class<?>> searchProperties) {

        Session session = null;
        long result = 0;

        try {
            session = openNewSession();
            result = selectSearchCount(session, searchParameter, searchProperties);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> selectSearch(Session session, SearchParameter searchParameter, Map<String, Class<?>> searchProperties,
                                     int fetchStart, int fetchSize) {

        LOGGER.trace("PAGED searchParameter = {}", searchParameter);
        LOGGER.trace("PAGED searchProperties = {}", searchProperties);
        LOGGER.trace("PAGED start = {}", fetchStart);
        LOGGER.trace("PAGED size = {}", fetchSize);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchParameter.getSearchKeyword(), searchProperties,
                false, new SortParameter(searchParameter.getColumnName(), searchParameter.getSortMethod()));

        LOGGER.trace("PAGED selectSearchPaged QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchParameter.getSearchKeyword())) {
            HibernateQueryUtil.setSearchParameter(q, searchParameter.getSearchKeyword(), searchProperties);
        }
        if (WinWorkBasicEntity.class.isAssignableFrom(modelClass)) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }
        q.setFirstResult(fetchStart);
        q.setMaxResults(fetchSize);

        List<M> list = q.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(SearchParameter searchParameter, Map<String, Class<?>> searchProperties,
                                 int fetchStart, int fetchSize) {

        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectSearch(session, searchParameter, searchProperties, fetchStart, fetchSize);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public M getById(Session session, I id, boolean init) {
        LOGGER.trace("Id = {}", id);
        M object = (M) session.get(modelClass, id);
        if (init && object != null) Hibernate.initialize(object);
        return object;
    }

    public M getById(I id, boolean init) {
        Session session = null;
        M result = null;

        try {
            session = openNewSession();
            result = getById(session, id, init);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    public List<M> selectNativeSQL(String sql) {
        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectNativeSQL(session, sql);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    public List<M> selectNativeSQL(Session session, String sql) {
        return session.createSQLQuery(sql).addEntity(modelClass).list();
    }
}
