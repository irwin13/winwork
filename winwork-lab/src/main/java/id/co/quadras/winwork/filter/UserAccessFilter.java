package id.co.quadras.winwork.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import id.co.quadras.winwork.model.entity.app.AppPermission;
import id.co.quadras.winwork.service.MessageParser;
import id.co.quadras.winwork.shared.WebSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author irwin Timestamp : 12/04/13 18:24
 */
@Singleton
public class UserAccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccessFilter.class);

    private List<String> allAccessPathList;

    @Inject
    private WebSession webSession;

    @Inject
    private MessageParser messageParser;


    @Override
    public void init(FilterConfig config) throws ServletException {
        String allAccessPath = config.getInitParameter("allAccessPath");
        String[] allAccessPathArray = allAccessPath.split(";");
        allAccessPathList = Arrays.asList(allAccessPathArray);
        LOGGER.debug("allAccessPathList = {}", allAccessPathList);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOGGER.debug("<<<<<<<<<<<<< USER ACCESS CHECK >>>>>>>>>>>>>>>>>>>");
        LOGGER.debug("request servlet path = {}", request.getServletPath());

        if (!request.getServletPath().equals("/") && isCheckedPath(request.getServletPath())) {
            LOGGER.debug("request.getServletPath() {} IS MUST BE CHECKED", request.getServletPath());

            List<AppPermission> permissionList = null;
            Object object = webSession.get(request, WebSession.USER_PERMISSION_LIST);
            if (object instanceof List) {
                permissionList = (List<AppPermission>) object;
            } else if (object instanceof String) {
                permissionList = messageParser.parseToObject(false, (String) object, new TypeReference<List<AppPermission>>() {});
            }

            if (permissionList != null) {
                boolean denied = true;
                permissionLoop:
                for (AppPermission permission : permissionList) {
                    if (!Strings.isNullOrEmpty(permission.getHttpPath()) &&
                            !permission.getHttpPath().equals("#")) {

                        String servletPath = request.getServletPath().replaceFirst("/", "");
                        String servletHttpMethod = request.getMethod();

                        if (permission.getHttpMethod().equalsIgnoreCase(servletHttpMethod) &&
                                permission.getHttpPath().equalsIgnoreCase(servletPath)) {
                            LOGGER.debug("permission found with path {} and method {}",
                                    permission.getHttpPath(), permission.getHttpMethod());
                            denied = false;
                            break permissionLoop;
                        }
                    }
                }

                webSession.updateMainWebSession(request);

                if (denied) {
                    LOGGER.debug("permission denied for path {}", request.getServletPath());
                    response.sendRedirect(request.getContextPath() + "/publicAccess/noAccess");
                    return;
                }

            } else {
                LOGGER.debug("Missing permissionList in session, will be redirect to logout page");
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
        }

        LOGGER.debug("authorized access, you may continue for path {}", request.getServletPath());
        LOGGER.debug("<<<<<<<<<<<<< USER ACCESS CHECK FINISH >>>>>>>>>>>>>>>>>>>");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

    private boolean isCheckedPath(String path) {
        if (path.equals("#") || path.equals("")) {
            return false;
        }
        for (String allAccessPath : allAccessPathList) {
            if (!allAccessPath.equals("/") && path.startsWith(allAccessPath)) {
                LOGGER.debug("path {} is start with {}", path, allAccessPath);
                return false;
            }
        }
        return true;
    }

}
