package com.irwin13.winwork.core;

/**
 * Created by irwin on 01/04/2015.
 */
public interface WinWorkComponent<T> {

    public T get(WinWorkConfiguration config) throws WinWorkException;
    public boolean singleton();
    public void start(WinWorkConfiguration config) throws WinWorkException;
    public void stop(WinWorkConfiguration config) throws WinWorkException;

}
