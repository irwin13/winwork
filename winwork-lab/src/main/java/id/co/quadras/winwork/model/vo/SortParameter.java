package id.co.quadras.winwork.model.vo;

import id.co.quadras.winwork.model.enums.SortMethod;
import id.co.quadras.winwork.util.PojoUtil;

/**
 * @author irwin Timestamp : 28/02/13 14:05
 */
public class SortParameter {

    private final String columnName;
    private final SortMethod sortMethod;

    public SortParameter(String columnName, SortMethod sortMethod) {
        this.columnName = columnName;
        this.sortMethod = sortMethod;
    }

    public String getColumnName() {
        return columnName;
    }

    public SortMethod getSortMethod() {
        return sortMethod;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, false);
    }

}
