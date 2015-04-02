package com.irwin13.winwork.core.dao;

/**
 * Created by irwin on 4/3/15.
 */
public interface EntityResolver {
    public <T> T getById(String id, Class<T> clazz);
}