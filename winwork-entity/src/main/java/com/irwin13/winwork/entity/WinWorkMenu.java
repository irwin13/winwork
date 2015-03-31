package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkMenu extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkMenu";
    }

    @NotNull
    private String name;
    private String description;
    @NotNull
    private String menuPath;
    @NotNull
    private String menuMethod;
    private int menuOrder;
    private boolean displayMenu;
    private String menuIcon;
    private WinWorkMenu parentMenu;
    private String parentMenuId;

    private transient List<WinWorkMenu> childMenuList;
    private transient String tab;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuMethod() {
        return menuMethod;
    }

    public void setMenuMethod(String menuMethod) {
        this.menuMethod = menuMethod;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public boolean isDisplayMenu() {
        return displayMenu;
    }

    public void setDisplayMenu(boolean displayMenu) {
        this.displayMenu = displayMenu;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public WinWorkMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(WinWorkMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public List<WinWorkMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<WinWorkMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }
}
