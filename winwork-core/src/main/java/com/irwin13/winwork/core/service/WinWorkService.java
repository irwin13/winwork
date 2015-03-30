package com.irwin13.winwork.core.service;

import com.google.common.base.Strings;
import com.irwin13.winwork.core.Searchable;
import com.irwin13.winwork.core.Sortable;
import com.irwin13.winwork.core.WinWorkException;
import com.irwin13.winwork.core.model.SearchParameter;
import com.irwin13.winwork.core.model.SortParameter;
import com.irwin13.winwork.core.model.WinWorkEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by irwin on 30/03/2015.
 */
public abstract class WinWorkService <M extends WinWorkEntity, I extends Serializable> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String DEFAULT_USER = "WinWorkDefaultUser";

    public abstract List<M> select(M filter, SortParameter sortParameter);
    public abstract List<M> select(M filter, SortParameter sortParameter, int fetchStart, int fetchSize);
    public abstract long selectCount(M filter);

    public abstract List<M> selectSearch(SearchParameter searchParameter);
    public abstract List<M> selectSearch(SearchParameter searchParameter, int fetchStart, int fetchSize);
    public abstract long selectSearchCount(String searchKeyword);

    public abstract M getById(I id, boolean fetchChild);

    public abstract I insert(M model);
    public abstract void update(M model);
    public abstract void softDelete(M model);

    public abstract Class<M> getModelClass();

    public void onSelect(M model) {
        if (model != null) {
            model.setActive(Boolean.TRUE);
        }
    }

    public void onInsert(M model) {
        Date current = new Date();
        if (Strings.isNullOrEmpty(model.getCreateBy())) {
            model.setCreateBy(DEFAULT_USER);
        }

        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(DEFAULT_USER);
        }

        model.setLastUpdateDate(current);
        model.setCreateDate(current);
        model.setActive(Boolean.TRUE);
    }

    public void onUpdate(M model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(DEFAULT_USER);
        }

        model.setLastUpdateDate(new Date());
    }

    public void onSoftDelete(M model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(DEFAULT_USER);
        }

        model.setLastUpdateDate(new Date());
        model.setActive(Boolean.FALSE);
    }

    public void onInsertOrUpdate(M model) {
        Date current = new Date();
        if (Strings.isNullOrEmpty(model.getCreateBy())) {
            model.setCreateBy(DEFAULT_USER);
        }
        if (model.getCreateDate() == null) {
            model.setCreateDate(current);
        }

        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(DEFAULT_USER);
        }

        model.setLastUpdateDate(current);
        model.setActive(Boolean.TRUE);
    }

    public Map<String, Class<?>> searchableFields() {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();
        Field[] fieldArray = getModelClass().getDeclaredFields();
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
                            throw new WinWorkException("FATAL : class : " + getModelClass().getName()
                                    + ", field : " + field.getName()
                                    + " on Searchable annotation, customAttribute and customAttributeType " +
                                    "doesn't have the same length of data");
                        }

                        for (int index = 0; index < attributeArray.length; index++) {
                            result.put(searchable.customAttribute()[index],
                                    searchable.customAttributeType()[index]);
                            logger.trace("Annotation Searchable found, put field {} with type {}",
                                    new Object[] {field.getName(), field.getType()});
                        }
                    } else {
                        result.put(field.getName(), field.getType());
                        logger.trace("Annotation Searchable found, put field {} with type {}",
                                new Object[] {field.getName(), field.getType()});
                    }
                }
            }
        }
        return result;
    }

    public Map<String, Class<?>> sortableFields() {
        Map<String, Class<?>> result = new LinkedHashMap<String, Class<?>>();
        Field[] fieldArray = getModelClass().getDeclaredFields();
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

                            logger.trace("Annotation Sortable found, put field {} with type {}",
                                    new Object[] {field.getName(), field.getType()});
                        }
                    } else {
                        result.put(field.getName(), field.getType());
                        logger.trace("Annotation Sortable found, put field {} with type {}",
                                new Object[] {field.getName(), field.getType()});
                    }
                }
            }
        }
        return result;
    }

}
