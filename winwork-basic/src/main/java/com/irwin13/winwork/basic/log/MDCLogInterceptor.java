package com.irwin13.winwork.basic.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author irwin Timestamp : 21/05/2014 16:50
 */
public class MDCLogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        LogUtil.setNodeNameMDC();
        return methodInvocation.proceed();
    }
}
