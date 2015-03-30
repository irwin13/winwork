package com.irwin13.winwork.core.model;

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

}
