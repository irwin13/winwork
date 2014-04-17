package com.irwin13.winwork.basic.exception;

/**
 * @author irwin Timestamp : 17/04/2014 19:33
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
