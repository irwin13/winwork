package com.irwin13.winwork.core;

/**
 * Created by irwin on 30/03/2015.
 */
public class WinWorkException extends RuntimeException {

    public WinWorkException(String message) {
        super(message);
    }

    public WinWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public WinWorkException(Throwable cause) {
        super(cause);
    }
}
