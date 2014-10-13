package com.irwin13.winwork.lab.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;

import com.google.inject.Singleton;
import com.irwin13.winwork.basic.utilities.WinWorkUtil;

/**
 * @author irwin Timestamp : 15/10/12 16:54
 */
@Singleton
public class MDCNodeNameFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        MDC.put("nodeName", WinWorkUtil.getNodeName());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
