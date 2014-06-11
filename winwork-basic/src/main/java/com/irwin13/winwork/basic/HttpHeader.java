package com.irwin13.winwork.basic;

/**
 * @author irwin Timestamp : 11/06/2014 16:14
 */
public enum HttpHeader {

    ACCEPT_LANGUAGE("Accept-Language"),
    X_FORWARDED_FOR("X-Forwarded-For"),
    CONTENT_LENGTH("Content-Length"),
    HOST("Host"),
    X_REAL_IP("X-Real-IP"),
    CONTENT_TYPE("Content-Type"),

    // our own custom HTTP HEADER
    REMOTE_ADDRESS("Remote-Address");

    private final String value;

    HttpHeader(java.lang.String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
