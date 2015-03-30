package com.irwin13.winwork.core.model;

/**
 * @author irwin Timestamp : 17/04/2014 19:20
 */
public class SortParameter extends DecoratedToString {

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

}
