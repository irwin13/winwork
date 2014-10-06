package com.irwin13.winwork.basic.service;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author irwin Timestamp : 06/10/2014 17:09
 */
public interface WinWorkJsonParser {
    public <T> T parseToObject(String json, boolean compressed, Class<T> clazz);
    public <T> T parseToObject(String json, boolean compressed, TypeReference<T> typeReference);
    public String parseToString(boolean compressed, Object object);
}
