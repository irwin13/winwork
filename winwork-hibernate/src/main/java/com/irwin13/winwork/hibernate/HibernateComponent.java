package com.irwin13.winwork.hibernate;

import com.irwin13.winwork.core.WinWorkComponent;
import com.irwin13.winwork.core.WinWorkConfiguration;
import com.irwin13.winwork.core.WinWorkException;
import org.hibernate.SessionFactory;

/**
 * Created by irwin on 09/04/2015.
 */
public class HibernateComponent implements WinWorkComponent<SessionFactory> {

    private SessionFactory sessionFactory;

    @Override
    public SessionFactory get(WinWorkConfiguration config) throws WinWorkException {
        if (singleton() && sessionFactory != null) {

        }

        return sessionFactory;
    }

    @Override
    public boolean singleton() {
        return true;
    }

    @Override
    public void start(WinWorkConfiguration config) throws WinWorkException {

    }

    @Override
    public void stop(WinWorkConfiguration config) throws WinWorkException {

    }
}
