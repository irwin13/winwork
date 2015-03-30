package com.irwin13.winwork.core.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by irwin on 30/03/2015.
 */
public abstract class DecoratedToString {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Class<? extends Object> clazz = getClass();

        sb.append("\n==== " + getClass().getName() + " ======\n");
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if ((method.getName().startsWith("get") ||
                    method.getName().startsWith("is")) &&
                    !method.getName().equalsIgnoreCase("getClass") &&
                    !method.getName().contains("Password")) {

                Object value = null;
                try {
                    value = method.invoke(this, ((Object[]) null));
                } catch (IllegalAccessException e) {
                    logger.error(e.getLocalizedMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }

                if (!Collection.class.isAssignableFrom(method.getReturnType())) {
                    sb.append(method.getName() + " = " + value + "\n");
                } else {
                    Collection collection = (Collection) value;
                    if (!collection.isEmpty()) {
                        Iterator iterator = collection.iterator();
                        sb.append(method.getName() + " = {\n" );
                        while (iterator.hasNext()) {
                            Object o = iterator.next();
                            sb.append("[" + o + "],\n");
                        }
                        sb.append(method.getName() + "}\n" );
                    }
                }
            }
        }

        sb.append("=============================================\n");
        return sb.toString();
    }
}
