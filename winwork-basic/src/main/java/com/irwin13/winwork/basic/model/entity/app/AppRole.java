package com.irwin13.winwork.basic.model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irwin13.winwork.basic.annotations.FormReadExclude;
import com.irwin13.winwork.basic.annotations.Searchable;
import com.irwin13.winwork.basic.annotations.Sortable;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;
import com.irwin13.winwork.basic.utilities.PojoUtil;

import java.util.List;

/**
 * @author irwin Timestamp : 12/04/13 16:30
 */
public class AppRole extends WinWorkBasicEntity {

    @FormReadExclude
    public static final String MODEL_NAME = "appRole";

    @Searchable
    @Sortable
    private String name;

    @Searchable
    @Sortable
    private String description;

    @FormReadExclude
    @JsonIgnore
    private List<AppUser> appUserList;

    @FormReadExclude
    private List<AppPermission> appPermissionList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

    public List<AppPermission> getAppPermissionList() {
        return appPermissionList;
    }

    public void setAppPermissionList(List<AppPermission> appPermissionList) {
        this.appPermissionList = appPermissionList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, true);
    }

}
