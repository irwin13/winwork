package com.irwin13.winwork.servlet;

import com.google.common.base.Strings;
import com.irwin13.winwork.core.WinWorkConfiguration;
import com.irwin13.winwork.core.WinWorkConstants;
import com.irwin13.winwork.core.WinWorkException;
import com.irwin13.winwork.core.model.WinWorkEntity;
import com.irwin13.winwork.entity.WinWorkUser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by irwin on 4/2/15.
 */
public class WinWorkSession {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public static final String REDIS = "Redis";
    public static final String HTTP_SESSION = "HttpSession";
    private static final String WEB_SESSION = "WinWorkSession";
    private static final String MAIN_SESSION = "MainSession";

    public static final String COOKIE_NAME = "WinWorkCookie";
    public static final String LOGIN_USER = "loginUser";
    public static final String MENU_LIST = "userMenuList";
    public static final String USER_PERMISSION_LIST = "userPermissionList";

    private final String storageSystem;

    public WinWorkSession() {
        ServletConfiguration config = WinWorkConfiguration.getConfig(ServletConfiguration.class);
        this.storageSystem = config.getSessionStorage();
    }

    public void createMainWebSession(HttpServletRequest request) {
        String sessionContent = mainSessionContent(request);
        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // String key = defaultNamespaceWebSessionKey(getCookieId(request), MAIN_SESSION);
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            request.getSession().setAttribute(MAIN_SESSION, sessionContent);
        }
    }

    public void updateMainWebSession(HttpServletRequest request) {
        if (isMainWebSessionActive(request)) {
            String mainSessionContent = mainSessionContent(request);

            if (storageSystem.equalsIgnoreCase(REDIS)) {
                // TODO implement Redis-based session
            } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
                request.getSession().setAttribute(MAIN_SESSION, mainSessionContent);
            }
        }
    }

    private String mainSessionContent(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(request.getRemoteAddr()) // client IP address
                .append("-")
                .append(new DateTime()); // timestamp last session activity
        return stringBuilder.toString();
    }

    public boolean isMainWebSessionActive(HttpServletRequest request) {
        boolean result = false;

        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // String key = defaultNamespaceWebSessionKey(getCookieId(request), MAIN_SESSION);
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            result = (request.getSession().getAttribute(MAIN_SESSION) != null);
        }

        return result;
    }

    public void set(HttpServletRequest request, String name, Object value) {
        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            request.getSession().setAttribute(name, value);
        }
    }

    public Object get(HttpServletRequest request, String name) {
        Object result = null;
        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            result = request.getSession().getAttribute(name);
        }
        return result;
    }

    public void remove(HttpServletRequest request, String name) {
        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            request.getSession().removeAttribute(name);
        }
    }

    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
        if (storageSystem.equalsIgnoreCase(REDIS)) {
            // TODO implement Redis-based session
        } else if (storageSystem.equalsIgnoreCase(HTTP_SESSION)) {
            request.getSession().invalidate();
        }
    }

    public String getCookieId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieId = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(COOKIE_NAME)) {
                    cookieId = cookie.getValue();
                    break;
                }
            }
        }
        return cookieId;
    }

    private String defaultNamespaceWebSessionKey(String cookieId, String key) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(WEB_SESSION);
        stringBuilder.append(":");
        stringBuilder.append(cookieId);
        stringBuilder.append(":");
        stringBuilder.append(key);
        return stringBuilder.toString();
    }

    public void setUserLogged(HttpServletRequest request, WinWorkEntity bean, boolean isOnlyUpdate) {
        WinWorkUser user = loginUser(request);
        if (user != null) {
            if (isOnlyUpdate) bean.setLastUpdateBy(user.getUsername());
            else {
                bean.setCreateBy(user.getUsername());
                bean.setLastUpdateBy(user.getUsername());
            }
        } else {
            if (isOnlyUpdate) bean.setCreateBy(WinWorkConstants.USER_SYSTEM);
            else {
                bean.setCreateBy(WinWorkConstants.USER_SYSTEM);
                bean.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
            }
        }
    }

    public WinWorkUser loginUser(HttpServletRequest request) {
        WinWorkUser appUser = (WinWorkUser) get(request, LOGIN_USER);
        if (appUser == null) {
            throw new WinWorkException("Unexpected error, null WinWorkUser where it's should be exists");
        }
        return appUser;
    }

    public String displayLang(HttpServletRequest request) {
        WinWorkUser appUser = loginUser(request);
        if (appUser != null && !Strings.isNullOrEmpty(appUser.getDisplayLang())) {
            return appUser.getDisplayLang();
        } else {
            return WinWorkConstants.DEFAULT_LANG;
        }
    }

    public Map<String, Object> mapWithLoginUser(HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put(LOGIN_USER, loginUser(request));
        return objectMap;
    }

}
