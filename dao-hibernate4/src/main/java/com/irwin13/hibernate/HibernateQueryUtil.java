package com.irwin13.hibernate;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;
import com.irwin13.winwork.basic.utilities.PojoUtil;
import com.irwin13.winwork.basic.utilities.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author irwin Timestamp : 28/04/2014 17:10
 */
public final class HibernateQueryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateQueryUtil.class);

    public static String buildFilter(Object model, boolean includeSuperClass, List<String> exceptionFields,
                                     String condition) {
        StringBuilder filter = new StringBuilder();

        Class<? extends Object> clazz = model.getClass();

        final String modelName = StringUtil.lowerCaseFirstLetter(clazz.getSimpleName());

        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];
            if ((method.getName().startsWith("is") || method.getName().startsWith("get"))
                    && !method.getName().equalsIgnoreCase("getClass")) {
                try {
                    if (method.invoke(model, ((Object[])null)) != null) {
                        String propertyName = PojoUtil.getFieldNameFromGetMethod(method.getName());
                        if (!exceptionFields.contains(propertyName)) {
                            if (i == methods.length - 1) {
                                filter.append(modelName + "." + propertyName + "=:" + propertyName);
                            } else {
                                filter.append(modelName + "." + propertyName + "=:" + propertyName + " " + condition + " ");
                            }
                        }
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                } catch (InvocationTargetException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                }
            }
        }

        return filter.toString();
    }

    public static List<String> commonExceptionFields() {
        List<String> exceptionFields = new LinkedList<String>();
        exceptionFields.add("class");
        exceptionFields.add("index");
        exceptionFields.add("searchParameter");
        return exceptionFields;
    }

    public static void setSearchParameter(Query query, String searchKeyword, Map<String, Class<?>> searchProperties)  {

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

    public static String buildSearchQuery(Class<?> model, String searchKeyword,
                                          Map<String, Class<?>> searchProperties,
                                          boolean isSelectCount, SortParameter sortParameter) {
        StringBuilder query = new StringBuilder();
        final String modelName = StringUtil.lowerCaseFirstLetter(model.getSimpleName());

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
            if (WinWorkBasicEntity.class.isAssignableFrom(model)) {
                query.append(" ) AND " + modelName + ".active = :STATUS_ACTIVE ");
            } else {
                query.append(" ) ");
            }
        } else {
            if (WinWorkBasicEntity.class.isAssignableFrom(model)) {
                query.append(" WHERE " + modelName + ".active = :STATUS_ACTIVE ");
            }
        }

        if (sortParameter != null) {
            if (Strings.isNullOrEmpty(sortParameter.getColumnName())) {
                query.append(" ORDER BY " + WinWorkConstants.DEFAULT_PROPERTY_ORDER);
            } else {
                query.append(" ORDER BY " + sortParameter.getColumnName());
            }

            if (sortParameter.getSortMethod() == null) {
                query.append(" DESC");
            } else {
                query.append(" " + sortParameter.getSortMethod());
            }
        }

        return query.toString();
    }

    private static String searchFilterAppender(int index, String modelName,
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

    public static String buildModelQuery(Object model, boolean isSelectCount, SortParameter sortParameter) {
        StringBuilder query = new StringBuilder();

        Class<? extends Object> clazz = model.getClass();

        // lower case the first letter of the class name
        final String modelName = StringUtil.lowerCaseFirstLetter(clazz.getSimpleName());

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
        final String modelName = StringUtil.lowerCaseFirstLetter(model.getSimpleName());

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

    private static boolean isNumeric(String searchKeyword) {
        boolean isNumeric = false;
        if (StringUtils.isNumeric(searchKeyword.replaceAll("%", "")))
            isNumeric = true;

        return isNumeric;
    }

    private static boolean isDate(String searchKeyword) {
        boolean isDate;
        try {
            WinWorkConstants.SDF_DEFAULT.parse(searchKeyword.replaceAll("%", ""));
            isDate = true;
        } catch (ParseException e) {
            isDate = false;
        }

        return isDate;
    }

    private static Map<String, Class<?>> filteredMap(boolean isNumeric, boolean isDate, Map<String, Class<?>> source) {
        Map<String, Class<?>> filteredMap = new TreeMap<String, Class<?>>();

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

        return filteredMap;
    }

}
