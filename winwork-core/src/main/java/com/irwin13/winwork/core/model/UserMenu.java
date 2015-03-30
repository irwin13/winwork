package com.irwin13.winwork.core.model;

import java.util.List;

/**
 * @author irwin Timestamp : 04/11/13 17:02
 */
public class UserMenu extends DecoratedToString {

    private String id;
    private String httpPath;
    private String name;
    private Boolean asMenu;
    private Integer menuOrder;
    private UserMenu parentMenu;

    private List<UserMenu> childMenuList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Boolean isAsMenu() {
        return asMenu;
    }

    public void setAsMenu(Boolean asMenu) {
        this.asMenu = asMenu;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<UserMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public UserMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(UserMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMenu)) return false;

        UserMenu userMenu = (UserMenu) o;

        if (!id.equals(userMenu.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
