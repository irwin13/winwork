package com.irwin13.winwork.basic.config;

import org.apache.commons.configuration.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author irwin Timestamp : 17/04/2014 19:26
 */
public abstract class WinWorkConfig {

    public abstract Configuration getConfiguration();
    /**
     * Return String getConfiguration() value with key specified, if the value is not found, then will return the nullValue
     * @param key getConfiguration() key
     * @param nullValue value to return if the getConfiguration() not found
     * @return getConfiguration() value
     */
    public String getString(String key, String nullValue) {
        return getConfiguration().getString(key, nullValue);
    }

    public String getString(String key) {
        return getConfiguration().getString(key, null);
    }

    /**
     * Return int getConfiguration() value with key specified, if the value is not found, then will return the nullValue
     * @param key getConfiguration() key
     * @param nullValue value to return if the getConfiguration() not found
     * @return getConfiguration() value
     */
    public int getInt(String key, int nullValue) {
        return getConfiguration().getInt(key, nullValue);
    }

    public int getInt(String key) {
        return getConfiguration().getInt(key, 0);
    }

    /**
     * Return boolean getConfiguration() value with key specified, if the value is not found, then will return the nullValue
     * @param key getConfiguration() key
     * @param nullValue value to return if the getConfiguration() not found
     * @return getConfiguration() value
     */
    public boolean getBoolean(String key, boolean nullValue) {
        return getConfiguration().getBoolean(key, nullValue);
    }

    public boolean getBoolean(String key) {
        return getConfiguration().getBoolean(key, false);
    }

    /**
     * Return float getConfiguration() value with key specified, if the value is not found, then will return the nullValue
     * @param key getConfiguration() key
     * @param nullValue value to return if the getConfiguration() not found
     * @return getConfiguration() value
     */
    public float getFloat(String key, float nullValue) {
        return getConfiguration().getFloat(key, nullValue);
    }

    public float getFloat(String key) {
        return getConfiguration().getFloat(key, 0f);
    }

    public double getDouble(String key, double nullValue) {
        return getConfiguration().getDouble(key, nullValue);
    }

    public double getDouble(String key) {
        return getConfiguration().getDouble(key, 0d);
    }

    public long getLong(String key, long nullValue) {
        return getConfiguration().getLong(key, nullValue);
    }

    public long getLong(String key) {
        return getConfiguration().getLong(key, 0L);
    }

    /**
     * Return List of String web the properties file with comma delimiter or multiple declaration
     * @param key getConfiguration() key
     * @return List of String or empty List if key not found
     */
    @SuppressWarnings("unchecked")
    public List<Object> getListString(String key) {
        List<Object> list = getConfiguration().getList(key);
        return (list == null) ? Collections.EMPTY_LIST : list;
    }

}
