package id.co.quadras.winwork.dao.hibernate;

import com.google.common.base.Strings;
import id.co.quadras.winwork.model.annotations.MDCLog;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.model.vo.SortParameter;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 14/03/13 16:26
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
        LOGGER.debug("Select QUERY = {}", sqlQuery);

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
    public List<M> selectPaged(Session session, M filter,  SortParameter sortParameter, int start, int size) {
        if (filter == null) return Collections.EMPTY_LIST;

        String sqlQuery = HibernateQueryUtil.buildModelQuery(filter, false, sortParameter);
        LOGGER.debug("Select Paged QUERY = {}", sqlQuery);
        LOGGER.debug("Select Paged start = {}", start);
        LOGGER.debug("Select Paged size = {}", size);

        Query query = session.createQuery(sqlQuery);
        query.setProperties(filter);
        query.setFirstResult(start);
        query.setMaxResults(size);
        List<M> list = query.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectPaged(M filter, SortParameter sortParameter, int start, int size) {
        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectPaged(session, filter, sortParameter, start, size);
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
        LOGGER.debug("COUNT Query = {}", sqlQuery);

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
    public List<M> selectSearch(Session session, String searchKeyword, Map<String, Class<?>> searchProperties,
                                SortParameter sortParameter) {

        LOGGER.debug("searchKeyword = {}", searchKeyword);
        LOGGER.debug("searchProperties = {}", searchProperties);
        LOGGER.debug("sortParameter = {}", sortParameter);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchKeyword, searchProperties,
                                                            false, sortParameter);

        LOGGER.debug("selectSearch QUERY {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            HibernateQueryUtil.setSearchParameter(q, searchKeyword, searchProperties);
        }

        if (BaseEntity.class.isAssignableFrom(getModelClass())) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }

        List<M> list = q.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                SortParameter sortParameter) {

        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectSearch(session, searchKeyword, searchProperties, sortParameter);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @MDCLog
    public long selectSearchCount(Session session, String searchKeyword, Map<String, Class<?>> searchProperties) {
        long result;

        LOGGER.debug("COUNT searchKeyword = {}", searchKeyword);
        LOGGER.debug("COUNT searchProperties = {}", searchProperties);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchKeyword, searchProperties, true, null);

        LOGGER.debug("COUNT selectSearchCount QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            HibernateQueryUtil.setSearchParameter(q, searchKeyword, searchProperties);
        }
        if (BaseEntity.class.isAssignableFrom(getModelClass())) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }

        result = (Long) q.uniqueResult();

        return result;
    }

    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties) {

        Session session = null;
        long result = 0;

        try {
            session = openNewSession();
            result = selectSearchCount(session, searchKeyword, searchProperties);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @MDCLog
    public List<M> selectSearchPaged(Session session, String searchKeyword, Map<String, Class<?>> searchProperties,
                                     SortParameter sortParameter, int start, int size) {

        LOGGER.debug("PAGED searchKeyword = {}", searchKeyword);
        LOGGER.debug("PAGED searchProperties = {}", searchProperties);
        LOGGER.debug("PAGED sortParameter = {}", sortParameter);
        LOGGER.debug("PAGED start = {}", start);
        LOGGER.debug("PAGED size = {}", size);

        String query = HibernateQueryUtil.buildSearchQuery(modelClass, searchKeyword, searchProperties,
                                                            false, sortParameter);

        LOGGER.debug("PAGED selectSearchPaged QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            HibernateQueryUtil.setSearchParameter(q, searchKeyword, searchProperties);
        }
        if (BaseEntity.class.isAssignableFrom(getModelClass())) {
            q.setBoolean("STATUS_ACTIVE", Boolean.TRUE);
        }
        q.setFirstResult(start);
        q.setMaxResults(size);

        List<M> list = q.list();

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                     SortParameter sortParameter, int start, int size) {

        Session session = null;
        List<M> list = null;

        try {
            session = openNewSession();
            list = selectSearchPaged(session, searchKeyword, searchProperties, sortParameter, start, size);
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public M getById(Session session, I id, boolean init) {
        LOGGER.debug("Id = {}", id);
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
