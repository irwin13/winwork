package com.irwin13.winwork.basic.utilities;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.annotations.Searchable;
import com.irwin13.winwork.basic.annotations.Sortable;
import com.irwin13.winwork.basic.exception.WinWorkException;
import com.irwin13.winwork.basic.model.SortParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author irwin Timestamp : 17/04/2014 19:33
 */
public final class PojoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PojoUtil.class);

    public static String beanToString(Object bean, boolean includeSuperClass) {
        StringBuilder sb = new StringBuilder();

        Class<? extends Object> clazz = bean.getClass();

        sb.append("\n==== " + bean.getClass().getName() + " ======\n");
        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if ((method.getName().startsWith("get") ||
                    method.getName().startsWith("is")) &&
                    !method.getName().equalsIgnoreCase("getClass") &&
                    !method.getName().contains("Password")) {

                if (!Collection.class.isAssignableFrom(method.getReturnType())) {
                    try {
                        sb.append(method.getName() + " = " + method.invoke(bean, ((Object[])null)) + "\n");
                    } catch (IllegalArgumentException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    } catch (IllegalAccessException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    } catch (InvocationTargetException e) {
                        LOGGER.error(e.getLocalizedMessage(), e);
                    }
                }
            }
        }

        sb.append("=============================================\n");
        return sb.toString();
    }

    public static Map<String, Object> beanToMap(Object bean, boolean includeSuperClass) {
        Map<String, Object> map = new TreeMap<String, Object>();

        Class<? extends Object> clazz = bean.getClass();

        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if ((method.getName().startsWith("get") ||
                    method.getName().startsWith("is")) &&
                    !method.getName().equalsIgnoreCase("getClass")) {
                try {
                    map.put(getFieldNameFromGetMethod(method.getName()), method.invoke(bean, ((Object[])null)));
                } catch (IllegalArgumentException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                } catch (InvocationTargetException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                }
            }
        }

        return map;
    }

    /**
     * Extracting field name from getter method name. Example : getUsername will be converted to username.
     * @param methodName getter method name
     * @return field name
     */
    public static String getFieldNameFromGetMethod(String methodName) {
        String field;
        if (methodName.startsWith("is")) {
            field = methodName.substring(2, methodName.length());
        } else {
            field = methodName.substring(3, methodName.length());
        }
        return (field.substring(0, 1).toLowerCase() + field.substring(1, field.length()));
    }

    public static Map<String, Class<?>> getSearchableField(Class<?> objectClass) {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();
        Field[] fieldArray = objectClass.getDeclaredFields();
        for (int i = 0; i < fieldArray.length; i++) {
            Field field = fieldArray[i];

            Annotation[] annotationArray = field.getAnnotations();
            for (int a = 0; a < annotationArray.length; a++) {
                Annotation annotation = annotationArray[a];
                if (annotation.annotationType().equals(Searchable.class)) {
                    Searchable searchable = field.getAnnotation(Searchable.class);
                    if (searchable.customAttribute().length > 0
                            && searchable.customAttributeType().length > 0) {

                        String[] attributeArray = searchable.customAttribute();
                        Class<?>[] attributeTypeArray = searchable.customAttributeType();

                        if (attributeArray.length != attributeTypeArray.length) {
                            throw new WinWorkException("FATAL : class : " + objectClass.getName()
                                    + ", field : " + field.getName()
                                    + " on Searchable annotation, customAttribute and customAttributeType " +
                                    "doesn't have the same length of data");
                        }

                        for (int index = 0; index < attributeArray.length; index++) {
                            result.put(searchable.customAttribute()[index],
                                    searchable.customAttributeType()[index]);
                            LOGGER.trace("Annotation Searchable found, put field {} with type {}",
                                    new Object[] {field.getName(), field.getType()});
                        }
                    } else {
                        result.put(field.getName(), field.getType());
                        LOGGER.trace("Annotation Searchable found, put field {} with type {}",
                                new Object[] {field.getName(), field.getType()});
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, Class<?>> getSortableField(Class<?> objectClass) {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();
        Field[] fieldArray = objectClass.getDeclaredFields();
        for (int i = 0; i < fieldArray.length; i++) {
            Field field = fieldArray[i];

            Annotation[] annotationArray = field.getAnnotations();
            for (int a = 0; a < annotationArray.length; a++) {
                Annotation annotation = annotationArray[a];
                if (annotation.annotationType().equals(Sortable.class)) {
                    Sortable sortable = field.getAnnotation(Sortable.class);

                    if (sortable.customAttribute().length > 0) {

                        String[] attributeArray = sortable.customAttribute();
                        for (int index = 0; index < attributeArray.length; index++) {
                            result.put(sortable.customAttribute()[index], field.getType());

                            LOGGER.trace("Annotation Sortable found, put field {} with type {}",
                                    new Object[] {field.getName(), field.getType()});
                        }
                    } else {
                        result.put(field.getName(), field.getType());
                        LOGGER.trace("Annotation Sortable found, put field {} with type {}",
                                new Object[] {field.getName(), field.getType()});
                    }
                }
            }
        }
        return result;
    }

    /**
     * Find all field in a POJO that annotated with specified annotation class.
     * @param objectClass pojo to lookup
     * @param annotationClass annotation class to lookup
     * @return Map with field name as key and field class type as value
     */
    public static Map<String, Class<?>> getFieldAnnotateWith(Class<?> objectClass, Class<?> annotationClass) {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();
        Field[] fieldArray = objectClass.getDeclaredFields();
        for (int i = 0; i < fieldArray.length; i++) {
            Field field = fieldArray[i];

            Annotation[] annotationArray = field.getAnnotations();
            for (int a = 0; a < annotationArray.length; a++) {
                Annotation annotation = annotationArray[a];
                if (annotation.annotationType().equals(annotationClass)) {
                    result.put(field.getName(), field.getType());
                    LOGGER.trace("Annotation {} found, put field {} with type {}",
                            new Object[] {annotationClass.getName(), field.getName(), field.getType()});
                }
            }
        }
        return result;
    }

    public static Map<String, Class<?>> getFieldFromClass(Class<?> objectClass, boolean includeSuperClass) {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();

        Field[] fieldArray;
        if (includeSuperClass) {
            fieldArray = objectClass.getFields();
        } else {
            fieldArray = objectClass.getDeclaredFields();
        }

        for (int i = 0; i < fieldArray.length; i++) {
            Field field = fieldArray[i];
            result.put(field.getName(), field.getType());
        }
        return result;
    }

    public static SortParameter buildSortParameter(String columnName, String sortMethod) {
        SortParameter sortParameter = null;
        if (!Strings.isNullOrEmpty(columnName) && !Strings.isNullOrEmpty(sortMethod)) {
            sortParameter = new SortParameter(columnName, sortMethod);
        } else if (!Strings.isNullOrEmpty(columnName) && Strings.isNullOrEmpty(sortMethod)) {
            sortParameter = new SortParameter(columnName, SortParameter.ASC);
        }
        return sortParameter;
    }

    public static String sortParameterToHttpQueryParam(SortParameter sortParameter) {
        StringBuilder queryParam = new StringBuilder("");
        if (sortParameter != null) {
            if (!Strings.isNullOrEmpty(sortParameter.getColumnName())) {
                queryParam.append("columnName=" + sortParameter.getColumnName());
                if (sortParameter.getSortMethod() != null) {
                    queryParam.append("&sortMethod=" + sortParameter.getSortMethod());
                } else {
                    queryParam.append("&sortMethod=" + SortParameter.ASC);
                }
            }
        }
        return queryParam.toString();
    }

    public static String beanToHttpQueryParam(Object model, boolean includeSuperClass) {
        StringBuilder queryParam = new StringBuilder("");

        Class<? extends Object> clazz = model.getClass();
        Method[] methods = (includeSuperClass) ? clazz.getMethods() : clazz.getDeclaredMethods();

        int index = 0;
        for (int i = 0; i < methods.length; i++) {

            Method method = methods[i];
            if ((method.getName().startsWith("is") || method.getName().startsWith("get"))
                    && !method.getName().equalsIgnoreCase("getClass")) {

                try {
                    Object value = method.invoke(model, ((Object[]) null));
                    if (value != null) {
                        String propertyName = PojoUtil.getFieldNameFromGetMethod(method.getName());
                        if (index == 0) {
                            queryParam.append(propertyName);
                        } else {
                            queryParam.append("&");
                            queryParam.append(propertyName);
                        }
                        queryParam.append("=");
                        if (value instanceof Date) {
                            queryParam.append(WinWorkConstants.SDF_DEFAULT.format(value));
                        } else {
                            queryParam.append(value);
                        }

                        index++;
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

        return queryParam.toString();
    }

    public static String mapToHttpQueryParam(Map<String, String> map) {
        StringBuilder queryParam = new StringBuilder("");
        if (map == null) {
            throw new NullPointerException("Input parameter map is null");
        }

        int index = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (index == 0) {
                queryParam.append(entry.getKey());
            } else {
                queryParam.append("&");
                queryParam.append(entry.getKey());
            }
            queryParam.append("=");
            queryParam.append(entry.getValue());
            index++;
        }

        return queryParam.toString();
    }

}
