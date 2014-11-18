package com.irwin13.winwork.lab.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.irwin13.winwork.basic.model.UserAccess;
import com.irwin13.winwork.lab.service.WebSession;

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
    private ObjectMapper objectMapper;

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

        boolean checkedPath = isCheckedPath(request.getServletPath());
        if (!request.getServletPath().equals("/") && checkedPath) {
            LOGGER.debug("request.getServletPath() {} IS MUST BE CHECKED", request.getServletPath());

            boolean denied = checkUserAccess(request);
            if (denied) {
                LOGGER.debug("permission denied for path {}", request.getServletPath());
                response.sendRedirect(request.getContextPath() + "/publicAccess/noAccess");
                return;
            } else {
                if (checkedPath) {
                    webSession.updateMainWebSession(request);
                }
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
            if (!allAccessPath.equals("/") && (path.startsWith(allAccessPath) || path.equalsIgnoreCase(allAccessPath))) {
                LOGGER.debug("path {} is start with {}", path, allAccessPath);
                return false;
            }
        }
        return true;
    }


    private boolean checkUserAccess(HttpServletRequest request) throws IOException {
        boolean denied = true;

        List<UserAccess> permissionList = null;
        Object object = webSession.get(request, WebSession.USER_PERMISSION_LIST);
        if (object instanceof List) {
            permissionList = (List<UserAccess>) object;
        } else if (object instanceof String) {
            permissionList = objectMapper.readValue((String) object, new TypeReference<List<UserAccess>>() {});
        }

        if (permissionList != null) {
            permissionLoop:
            for (UserAccess permission : permissionList) {
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
        }

        return denied;
    }

}
