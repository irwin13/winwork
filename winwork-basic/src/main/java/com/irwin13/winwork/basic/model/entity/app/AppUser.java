package com.irwin13.winwork.basic.model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irwin13.winwork.basic.annotations.FormReadExclude;
import com.irwin13.winwork.basic.annotations.Searchable;
import com.irwin13.winwork.basic.annotations.Sortable;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;
import com.irwin13.winwork.basic.utilities.WinWorkObjects;

import java.util.Date;
import java.util.List;


public class AppUser extends WinWorkBasicEntity {

    @FormReadExclude
    public static final String MODEL_NAME = "appUser";

    @Searchable
    @Sortable
    private String username;

    @FormReadExclude
    private String password;

    @Searchable
    @Sortable
    private String firstName;

    @Searchable
    @Sortable
    private String lastName;

    @Searchable
    @Sortable
    private String email;

    @Searchable
    @Sortable
    private String phone;

    @FormReadExclude
    private String lastLoginFrom;

    @FormReadExclude
    private java.util.Date lastLogin;

    @FormReadExclude
    private String displayLang;

    @FormReadExclude
    @JsonIgnore
    private List<AppRole> appRoleList;

    public List<AppRole> getAppRoleList() {
        return appRoleList;
    }

    public void setAppRoleList(List<AppRole> appRoleList) {
        this.appRoleList = appRoleList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDisplayLang() {
        return displayLang;
    }

    public void setDisplayLang(String displayLang) {
        this.displayLang = displayLang;
    }

    @Override
    public String toString() {
        return WinWorkObjects.beanToString(this, true);
    }

}
