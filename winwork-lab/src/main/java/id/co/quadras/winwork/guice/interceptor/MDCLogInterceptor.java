package id.co.quadras.winwork.guice.interceptor;

import id.co.quadras.winwork.util.LogUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author irwin Timestamp : 07/02/13 15:18
 */
public class MDCLogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        LogUtil.setNodeNameMDC();
        return methodInvocation.proceed();
    }
}
