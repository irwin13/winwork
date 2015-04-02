package com.irwin13.winwork.servlet;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.irwin13.winwork.core.WinWorkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by irwin on 4/2/15.
 */
public class UserSessionCheck {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean sessionExists(HttpServletRequest request, HttpServletResponse response,
                                 ServletConfiguration config) throws IOException {
        boolean validSession = false;

        WinWorkSession winWorkSession = new WinWorkSession();
        logger.debug("<<<<<<<<<<<<< USER SESSION CHECK >>>>>>>>>>>>>>>>>>>");
        logger.debug("request servlet path = {}", request.getServletPath());

        String cookieId = winWorkSession.getCookieId(request);
        if (Strings.isNullOrEmpty(cookieId)) {
            cookieId = WinWorkUtil.random32UUID();
            logger.debug("missing cookie, creating new one with value = {}", cookieId);
        }

        Cookie cookie = new Cookie(WinWorkSession.COOKIE_NAME, cookieId);
        cookie.setPath("/");
        cookie.setMaxAge(config.getSessionExpired());
        response.addCookie(cookie);

        if (!emptySessionPath(request, config)) {
            if (winWorkSession.isMainWebSessionActive(request)) {
                logger.debug("Session Active");
                validSession = true;
            } else {
                logger.debug("Session NOT Active, will be redirect to logout page");
                response.sendRedirect(request.getContextPath() + "/logout");
                validSession = false;
            }
        }

        logger.debug("<<<<<<<<<<<<< USER SESSION CHECK FINISH >>>>>>>>>>>>>>>>>>>");

        return validSession;
    }

    private boolean emptySessionPath(HttpServletRequest request, ServletConfiguration config) {
        boolean bypass = false;
        String emptySessionPath = config.getEmptySessionPath();
        List<String> list = Splitter.on(";").splitToList(emptySessionPath);
        for (String path : list) {
            if (path.contains(request.getServletPath())) {
                return true;
            }
        }
        return bypass;
    }
}
