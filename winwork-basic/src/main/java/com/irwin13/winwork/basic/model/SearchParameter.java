package com.irwin13.winwork.basic.model;

import com.irwin13.winwork.basic.utilities.WinWorkObjects;

/**
 * @author irwin Timestamp : 17/04/2014 19:07
 */
public class SearchParameter extends SortParameter {

    private final String searchKeyword;

    public SearchParameter(String searchKeyword, String columnName, String sortMethod) {
        super(columnName, sortMethod);
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    @Override
    public String toString() {
        return WinWorkObjects.beanToString(this, true);
    }
}
