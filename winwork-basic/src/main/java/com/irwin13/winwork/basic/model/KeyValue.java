package com.irwin13.winwork.basic.model;

import com.irwin13.winwork.basic.utilities.PojoUtil;

/**
 * @author irwin Timestamp : 12/04/13 17:04
 */
public class KeyValue {

    private final String key;
    private final String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, false);
    }
}
