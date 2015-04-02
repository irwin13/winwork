package com.irwin13.winwork.servlet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.irwin13.winwork.core.WinWorkException;
import com.irwin13.winwork.entity.WinWorkMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by irwin on 4/2/15.
 */
public class UserAccessCheck {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean validAccess(HttpServletRequest request, HttpServletResponse response,
                               ServletConfiguration config) throws IOException {
        boolean valid = true;
        WinWorkSession winWorkSession = new WinWorkSession();
        logger.debug("<<<<<<<<<<<<< USER ACCESS CHECK >>>>>>>>>>>>>>>>>>>");
        logger.debug("request servlet path = {}", request.getServletPath());

        if (!request.getServletPath().equals("/") && isCheckedPath(request.getServletPath(), config)) {
            logger.debug("request.getServletPath() {} IS MUST BE CHECKED", request.getServletPath());

            List<WinWorkMenu> permissionList = null;
            Object object = winWorkSession.get(request, WinWorkSession.USER_PERMISSION_LIST);
            if (object instanceof List) {
                permissionList = (List<WinWorkMenu>) object;
            } else if (object instanceof String) {
                throw new WinWorkException("Unexpected object from session " + WinWorkSession.USER_PERMISSION_LIST);
            }

            if (permissionList != null) {
                boolean denied = true;
                permissionLoop:
                for (WinWorkMenu permission : permissionList) {
                    if (!Strings.isNullOrEmpty(permission.getMenuPath()) &&
                            !permission.getMenuPath().equals("#")) {

                        String servletPath = request.getServletPath().replaceFirst("/", "");
                        String servletHttpMethod = request.getMethod();

                        if (permission.getMenuMethod().equalsIgnoreCase(servletHttpMethod) &&
                                permission.getMenuPath().equalsIgnoreCase(servletPath)) {
                            logger.debug("permission found with path {} and method {}",
                                    permission.getMenuPath(), permission.getMenuMethod());
                            denied = false;
                            break permissionLoop;
                        }
                    }
                }

                winWorkSession.updateMainWebSession(request);

                if (denied) {
                    logger.debug("permission denied for path {}", request.getServletPath());
                    response.sendRedirect(request.getContextPath() + "/publicAccess/noAccess");
                    valid = false;
                }

            } else {
                logger.debug("Missing permissionList in session, will be redirect to logout page");
                response.sendRedirect(request.getContextPath() + "/logout");
                valid = false;
            }
        }

        logger.debug("authorized access, you may continue for path {}", request.getServletPath());
        logger.debug("<<<<<<<<<<<<< USER ACCESS CHECK FINISH >>>>>>>>>>>>>>>>>>>");

        return valid;
    }
    
    private boolean isCheckedPath(String path, ServletConfiguration config) {
        if (path.equals("#") || path.equals("")) {
            return false;
        }

        String allAccessPath = config.getAllAccessPath();
        List<String> list = Splitter.on(";").splitToList(allAccessPath);
        for (String checkedPath : list) {
            if (!checkedPath.equals("/") && checkedPath.startsWith(allAccessPath)) {
                logger.debug("path {} is start with {}", path, checkedPath);
                return false;
            }
        }
        return true;
    }

}
