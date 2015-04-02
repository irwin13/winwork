package com.irwin13.winwork.servlet;

import com.irwin13.winwork.core.WinWorkConfiguration;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 4/2/15.
 */
public class ServletConfiguration extends WinWorkConfiguration {

    @NotNull
    private String allAccessPath;
    @NotNull
    private String emptySessionPath;
    private int sessionExpired = 1800;
    @NotNull
    private String sessionStorage;

    public String getAllAccessPath() {
        return allAccessPath;
    }

    public void setAllAccessPath(String allAccessPath) {
        this.allAccessPath = allAccessPath;
    }

    public String getEmptySessionPath() {
        return emptySessionPath;
    }

    public void setEmptySessionPath(String emptySessionPath) {
        this.emptySessionPath = emptySessionPath;
    }

    public int getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(int sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public String getSessionStorage() {
        return sessionStorage;
    }

    public void setSessionStorage(String sessionStorage) {
        this.sessionStorage = sessionStorage;
    }
}
