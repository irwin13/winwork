package com.irwin13.winwork.lab.dao;

/**
 * @author irwin Timestamp : 02/07/13 16:26
 */
public interface EntityResolver {
    public <T> T getById(String id, Class<T> clazz);
}
