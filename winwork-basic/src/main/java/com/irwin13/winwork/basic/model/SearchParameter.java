package com.irwin13.winwork.basic.model;

import com.irwin13.winwork.basic.utilities.PojoUtil;

/**
 * @author irwin Timestamp : 17/04/2014 19:07
 */
public class SearchParameter {

    public static final String ASC = " ASC ";
    public static final String DESC = " DESC ";

    private final String searchKeyword;
    private final String columnName;
    private final String sortMethod;

    public SearchParameter(String searchKeyword, String columnName, String sortMethod) {
        this.searchKeyword = searchKeyword;
        this.columnName = columnName;
        this.sortMethod = sortMethod;
    }

    public String getSearchKeyword() {
        return searchKeyword;
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
