package com.irwin13.winwork.hibernate;

import com.google.common.base.Strings;
import com.irwin13.winwork.core.WinWorkConstants;
import com.irwin13.winwork.core.WinWorkUtil;
import com.irwin13.winwork.core.model.SortParameter;
import com.irwin13.winwork.core.model.WinWorkEntity;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by irwin on 01/04/2015.
 */
public abstract class WinWorkHibernateDao<M extends Serializable, I extends Serializable> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Class<M> modelClass;
    private final SessionFactory sessionFactory;

    public WinWorkHibernateDao(Class<M> modelClass, SessionFactory sessionFactory) {
        this.modelClass = modelClass;
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
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
            logger.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
            throw e;
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
            logger.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
            throw e;
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
            logger.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
            throw e;
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
            logger.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
            throw e;
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
            logger.error(e.getLocalizedMessage(), e);
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            closeSession(session);
        }
    }

    public void saveOrUpdate(Session session, M model) {
        session.saveOrUpdate(model);
    }

    @SuppressWarnings("unchecked")
    public List<M> select(Session session, M filter, SortParameter sortParameter) {
        if (filter == null) return Collections.EMPTY_LIST;

        String sqlQuery = buildModelQuery(filter, false, sortParameter);
        logger.debug("Select QUERY = {}", sqlQuery);

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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectPaged(Session session, M filter,  SortParameter sortParameter, int start, int size) {
        if (filter == null) return Collections.EMPTY_LIST;

        String sqlQuery = buildModelQuery(filter, false, sortParameter);
        logger.debug("Select Paged QUERY = {}", sqlQuery);
        logger.debug("Select Paged start = {}", start);
        logger.debug("Select Paged size = {}", size);

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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    public long selectCount(Session session, M filter) {
        long result = 0;
        if (filter == null) return result;

        String sqlQuery = buildModelQuery(filter, true, null);
        logger.debug("COUNT Query = {}", sqlQuery);

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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearch(Session session, String searchKeyword, Map<String, Class<?>> searchProperties,
                                SortParameter sortParameter) {

        logger.debug("searchKeyword = {}", searchKeyword);
        logger.debug("searchProperties = {}", searchProperties);
        logger.debug("sortParameter = {}", sortParameter);

        String query = buildSearchQuery(modelClass, searchKeyword, searchProperties,
                false, sortParameter);

        logger.debug("selectSearch QUERY {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            setSearchParameter(q, searchKeyword, searchProperties);
        }

        if (WinWorkEntity.class.isAssignableFrom(getModelClass())) {
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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    public long selectSearchCount(Session session, String searchKeyword, Map<String, Class<?>> searchProperties) {
        long result;

        logger.debug("COUNT searchKeyword = {}", searchKeyword);
        logger.debug("COUNT searchProperties = {}", searchProperties);

        String query = buildSearchQuery(modelClass, searchKeyword, searchProperties, true, null);

        logger.debug("COUNT selectSearchCount QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            setSearchParameter(q, searchKeyword, searchProperties);
        }
        if (WinWorkEntity.class.isAssignableFrom(getModelClass())) {
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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<M> selectSearchPaged(Session session, String searchKeyword, Map<String, Class<?>> searchProperties,
                                     SortParameter sortParameter, int start, int size) {

        logger.debug("PAGED searchKeyword = {}", searchKeyword);
        logger.debug("PAGED searchProperties = {}", searchProperties);
        logger.debug("PAGED sortParameter = {}", sortParameter);
        logger.debug("PAGED start = {}", start);
        logger.debug("PAGED size = {}", size);

        String query = buildSearchQuery(modelClass, searchKeyword, searchProperties,
                false, sortParameter);

        logger.debug("PAGED selectSearchPaged QUERY = {}", query);

        Query q = session.createQuery(query);
        if (!Strings.isNullOrEmpty(searchKeyword)) {
            setSearchParameter(q, searchKeyword, searchProperties);
        }
        if (WinWorkEntity.class.isAssignableFrom(getModelClass())) {
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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    @SuppressWarnings("unchecked")
    public M getById(Session session, I id, boolean init) {
        logger.debug("Id = {}", id);
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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
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
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            closeSession(session);
        }

        return (list == null) ? Collections.EMPTY_LIST : list;
    }

    public List<M> selectNativeSQL(Session session, String sql) {
        return session.createSQLQuery(sql).addEntity(modelClass).list();
    }

    public String buildFilter(Object model, boolean includeSuperClass, List<String> exceptionFields,
                                     String condition) {
        StringBuilder filter = new StringBuilder();

        Class<? extends Object> clazz = model.getClass();

        final String modelName = WinWorkUtil.lowerCaseFirstLetter(clazz.getSimpleName());

        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];
            if ((method.getName().startsWith("is") || method.getName().startsWith("get"))
                    && !method.getName().equalsIgnoreCase("getClass")) {
                try {
                    if (method.invoke(model, ((Object[])null)) != null) {
                        String propertyName = WinWorkUtil.getFieldNameFromGetMethod(method.getName());
                        if (!exceptionFields.contains(propertyName)) {
                            if (i == methods.length - 1) {
                                filter.append(modelName + "." + propertyName + "=:" + propertyName);
                            } else {
                                filter.append(modelName + "." + propertyName + "=:" + propertyName + " " + condition + " ");
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    logger.error(e.getLocalizedMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.error(e.getLocalizedMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }

        return filter.toString();
    }

    public List<String> commonExceptionFields() {
        List<String> exceptionFields = new LinkedList<String>();
        exceptionFields.add("class");
        exceptionFields.add("index");
        exceptionFields.add("searchParameter");
        return exceptionFields;
    }

    public void setSearchParameter(Query query, String searchKeyword, Map<String, Class<?>> searchProperties)  {

        boolean isNumeric = isNumeric(searchKeyword);
        boolean isDate = isDate(searchKeyword);
        Map<String, Class<?>> filteredMap = filteredMap(isNumeric, isDate, searchProperties);

        for (Map.Entry<String, Class<?>> entry : filteredMap.entrySet()) {
            if (entry.getValue().equals(String.class)) {
                query.setString(WinWorkConstants.SEARCH_KEYWORD, searchKeyword);
            } else if (entry.getValue().equals(Integer.class) && isNumeric) {
                Integer val = null;
                try {
                    val = Integer.valueOf(searchKeyword.replaceAll("%", ""));
                } catch (NumberFormatException e) {}
                if (val != null)
                    query.setInteger(WinWorkConstants.SEARCH_KEYWORD_INT, val);
            } else if (entry.getValue().equals(Long.class) && isNumeric) {
                Long val = null;
                try {
                    val = Long.valueOf(searchKeyword.replaceAll("%", ""));
                } catch (NumberFormatException e) {}
                if (val != null)
                    query.setLong(WinWorkConstants.SEARCH_KEYWORD_LONG, val);
            } else if (entry.getValue().equals(Float.class) && isNumeric) {
                Float val = null;
                try {
                    val = Float.valueOf(searchKeyword.replaceAll("%", ""));
                } catch (NumberFormatException e) {}
                if (val != null)
                    query.setFloat(WinWorkConstants.SEARCH_KEYWORD_FLOAT, val);
            } else if (entry.getValue().equals(Double.class) && isNumeric) {
                Double val=null;
                try {
                    val = Double.valueOf(searchKeyword.replaceAll("%", ""));
                } catch (NumberFormatException e) {}
                if (val != null)
                    query.setDouble(WinWorkConstants.SEARCH_KEYWORD_DOUBLE, val);
            } else if (entry.getValue().equals(BigDecimal.class) && isNumeric) {
                BigDecimal val = null;
                try {
                    val = new BigDecimal(searchKeyword.replaceAll("%", ""));
                } catch (NumberFormatException e) {}
                if (val != null)
                    query.setBigDecimal(WinWorkConstants.SEARCH_KEYWORD_BIG_DECIMAL, val);
            } else if (entry.getValue().equals(Date.class) && isDate) {
                Date date = null;
                try {
                    date = WinWorkConstants.SDF_DEFAULT.parse(searchKeyword.replaceAll("%", ""));
                } catch (ParseException e) {}
                if (date != null)
                    query.setDate(WinWorkConstants.SEARCH_KEYWORD_DATE, date);
            }
        }
    }

    public String buildSearchQuery(Class<?> model, String searchKeyword,
                                          Map<String, Class<?>> searchProperties,
                                          boolean isSelectCount, SortParameter sortParameter) {
        StringBuilder query = new StringBuilder();
        final String modelName = WinWorkUtil.lowerCaseFirstLetter(model.getSimpleName());

        if (isSelectCount) {
            query.append("SELECT COUNT(" + modelName + ") FROM " + model.getName() + " " + modelName);
        } else {
            query.append("FROM " + model.getName() + " " + modelName);
        }

        if (!Strings.isNullOrEmpty(searchKeyword)) {

            boolean isNumeric = isNumeric(searchKeyword);
            boolean isDate = isDate(searchKeyword);
            Map<String, Class<?>> filteredMap = filteredMap(isNumeric, isDate, searchProperties);

            int index = 0;
            for (Map.Entry<String, Class<?>> entry : filteredMap.entrySet()) {
                if (entry.getValue().equals(String.class)) {
                    query.append(searchFilterAppender(index, modelName, entry.getKey(),
                            "LIKE", WinWorkConstants.SEARCH_KEYWORD, String.class));

                } else if (entry.getValue().equals(Integer.class) && isNumeric) {

                    Integer val = null;
                    try {
                        val = Integer.valueOf(searchKeyword.replaceAll("%", ""));
                    } catch (NumberFormatException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_INT, Integer.class));

                } else if (entry.getValue().equals(Long.class) && isNumeric) {
                    Long val = null;
                    try {
                        val = Long.valueOf(searchKeyword.replaceAll("%", ""));
                    } catch (NumberFormatException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_LONG, Long.class));

                } else if (entry.getValue().equals(Float.class) && isNumeric) {
                    Float val = null;
                    try {
                        val = Float.valueOf(searchKeyword.replaceAll("%", ""));
                    } catch (NumberFormatException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_FLOAT, Float.class));

                } else if (entry.getValue().equals(Double.class) && isNumeric) {
                    Double val = null;
                    try {
                        val = Double.valueOf(searchKeyword.replaceAll("%", ""));
                    } catch (NumberFormatException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_DOUBLE, Double.class));

                } else if (entry.getValue().equals(BigDecimal.class) && isNumeric) {
                    BigDecimal val = null;
                    try {
                        val = new BigDecimal(searchKeyword.replaceAll("%", ""));
                    } catch (NumberFormatException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_BIG_DECIMAL, BigDecimal.class));

                } else if ((entry.getValue().equals(Date.class) || entry.getValue().equals(java.sql.Date.class))
                        && isDate) {

                    Date val = null;
                    try {
                        val = WinWorkConstants.SDF_DEFAULT.parse(searchKeyword.replaceAll("%", ""));
                    } catch (ParseException e) {}

                    if (val != null)
                        query.append(searchFilterAppender(index, modelName, entry.getKey(),
                                "=", WinWorkConstants.SEARCH_KEYWORD_DATE, Date.class));
                }
                index++;
            }
            if (WinWorkEntity.class.isAssignableFrom(model)) {
                query.append(" ) AND " + modelName + ".active = :STATUS_ACTIVE ");
            } else {
                query.append(" ) ");
            }
        } else {
            if (WinWorkEntity.class.isAssignableFrom(model)) {
                query.append(" WHERE " + modelName + ".active = :STATUS_ACTIVE ");
            }
        }

        if (sortParameter != null) {
            if (Strings.isNullOrEmpty(sortParameter.getColumnName())) {
                query.append(" ORDER BY " + WinWorkConstants.DEFAULT_PROPERTY_ORDER);
            } else {
                query.append(" ORDER BY " + sortParameter.getColumnName());
            }

            if (Strings.isNullOrEmpty(sortParameter.getSortMethod())) {
                query.append(" DESC");
            } else {
                query.append(" " + sortParameter.getSortMethod());
            }
        }

        return query.toString();
    }

    private String searchFilterAppender(int index, String modelName,
                                               String fieldName, String comparator, String parameterName, Class parameterClass) {

        if (index == 0) {
            if (parameterClass.equals(String.class)) {
                return " WHERE (LOWER(" + modelName + "." + fieldName + ") " + comparator + " :" + parameterName;
            } else {
                return " WHERE (" + modelName + "." + fieldName + " " + comparator + " :" + parameterName;
            }
        } else {
            if (parameterClass.equals(String.class)) {
                return " OR LOWER(" + modelName + "." + fieldName + ") " + comparator + " :" + parameterName;
            } else {
                return " OR " + modelName + "." + fieldName + " " + comparator + " :" + parameterName;
            }
        }
    }

    public String buildModelQuery(Object model, boolean isSelectCount, SortParameter sortParameter) {
        StringBuilder query = new StringBuilder();

        Class<? extends Object> clazz = model.getClass();

        // lower case the first letter of the class name
        final String modelName = WinWorkUtil.lowerCaseFirstLetter(clazz.getSimpleName());

        if (isSelectCount) {
            query.insert(0, "SELECT COUNT(" + modelName + ") FROM " + clazz.getName() + " " + modelName);
        } else {
            query.insert(0, "FROM " + clazz.getName() + " " + modelName);
        }

        String filter = buildFilter(model, true, commonExceptionFields(), "AND");
        if (filter.length() > 0) {
            query.append(" WHERE ");
            query.append(filter);
            // delete the last AND
            query.delete(query.length() - 4, query.length());
        }


        if (sortParameter != null && !Strings.isNullOrEmpty(sortParameter.getColumnName())) {
            query.append(" ORDER BY " + sortParameter.getColumnName());

            if (sortParameter != null && sortParameter.getSortMethod() != null) {
                query.append(" " + sortParameter.getSortMethod());
            } else {
                query.append(" ASC");
            }
        }

        return query.toString();
    }

    public String buildGenericQuery(Class<?> model, boolean isSelectCount) {
        StringBuilder query = new StringBuilder();

        // lower case the first letter of the class name
        final String modelName = WinWorkUtil.lowerCaseFirstLetter(model.getSimpleName());

        if (isSelectCount) {
            query.insert(0, "SELECT COUNT(" + modelName + ") FROM " + model.getName() + " " + modelName);
        } else {
            query.insert(0, "FROM " + model.getName() + " " + modelName);
        }

        String filter = buildFilter(model, true, commonExceptionFields(), "AND");
        if (filter.length() > 0) {
            query.append(" WHERE ");
            query.append(filter);
            // delete the last AND
            query.delete(query.length() - 4, query.length());
        }

        return query.toString();
    }

    private boolean isNumeric(String searchKeyword) {
        boolean isNumeric = false;
        if (StringUtils.isNumeric(searchKeyword.replaceAll("%", "")))
            isNumeric = true;

        return isNumeric;
    }

    private boolean isDate(String searchKeyword) {
        boolean isDate;
        try {
            WinWorkConstants.SDF_DEFAULT.parse(searchKeyword.replaceAll("%", ""));
            isDate = true;
        } catch (ParseException e) {
            isDate = false;
        }

        return isDate;
    }

    private Map<String, Class<?>> filteredMap(boolean isNumeric, boolean isDate, Map<String, Class<?>> source) {
        Map<String, Class<?>> filteredMap = new TreeMap<String, Class<?>>();

        if (source != null) {
            for (Map.Entry<String, Class<?>> entry : source.entrySet()) {
                if (isNumeric) {
                    if (entry.getValue().equals(Long.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    } else if (entry.getValue().equals(Integer.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    } else if (entry.getValue().equals(Double.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    } else if (entry.getValue().equals(Float.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    } else if (entry.getValue().equals(BigDecimal.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    }
                } else if (isDate) {
                    if (entry.getValue().equals(Date.class) || entry.getKey().equals(java.sql.Date.class)) {
                        filteredMap.put(entry.getKey(), entry.getValue());
                    }
                }

                if (entry.getValue().equals(String.class)) {
                    filteredMap.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return filteredMap;
    }

}
