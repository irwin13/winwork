package com.irwin13.winwork.hibernate;

import com.irwin13.winwork.core.WinWorkConfiguration;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 01/04/2015.
 */
public class HibernateConfiguration extends WinWorkConfiguration {

    @NotNull
    private String jdbcUrl;
    @NotNull
    private String driverClass;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String validationQuery;

    private int initialPoolSize = 1;
    private int maxPoolSize = 10;
    private int minIdleSize = 3;
    private int maxIdleSize = 5;
    private int maxWait = 5000;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

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

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getMinIdleSize() {
        return minIdleSize;
    }

    public void setMinIdleSize(int minIdleSize) {
        this.minIdleSize = minIdleSize;
    }

    public int getMaxIdleSize() {
        return maxIdleSize;
    }

    public void setMaxIdleSize(int maxIdleSize) {
        this.maxIdleSize = maxIdleSize;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }
}
