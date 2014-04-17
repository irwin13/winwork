package com.irwin13.winwork.basic.model;

import com.irwin13.winwork.basic.utilities.PojoUtil;

/**
 * @author irwin Timestamp : 17/04/2014 19:20
 */
public class SortParameter {

    public static final String ASC = " ASC ";
    public static final String DESC = " DESC ";

    private final String columnName;
    private final String sortMethod;

    public SortParameter(String columnName, String sortMethod) {
        this.columnName = columnName;
        this.sortMethod = sortMethod;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, false);
    }
}
