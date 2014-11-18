package com.irwin13.winwork.basic.model;

import com.google.common.base.Objects;
import com.irwin13.winwork.basic.utilities.WinWorkObjects;

/**
 * @author irwin Timestamp : 04/11/13 17:02
 */
public class UserAccess {

    private String id;
    private String httpPath;
    private String httpMethod;
    private Integer menuOrder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccess)) return false;

        UserAccess that = (UserAccess) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return WinWorkObjects.beanToString(this, false);
    }
}
