package id.co.quadras.winwork.filter;

import com.google.inject.Singleton;
import id.co.quadras.winwork.util.StringCommon;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

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

        MDC.put("nodeName", StringCommon.getNodeName());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
