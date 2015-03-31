package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkUser extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkUser";
    }

    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;
    private String lastLoginFrom;
    private Date lastLoginDate;
    private String displayLang;

    private transient List<WinWorkRole> winWorkRoleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getDisplayLang() {
        return displayLang;
    }

    public void setDisplayLang(String displayLang) {
        this.displayLang = displayLang;
    }

    public List<WinWorkRole> getWinWorkRoleList() {
        return winWorkRoleList;
    }

    public void setWinWorkRoleList(List<WinWorkRole> winWorkRoleList) {
        this.winWorkRoleList = winWorkRoleList;
    }
}
