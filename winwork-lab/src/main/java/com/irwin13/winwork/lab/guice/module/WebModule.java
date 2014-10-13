package com.irwin13.winwork.lab.guice.module;

import java.util.HashMap;
import java.util.Map;

import com.irwin13.winwork.lab.controller.ChangePasswordController;
import com.irwin13.winwork.lab.controller.LeftMenuController;
import com.irwin13.winwork.lab.controller.LoginController;
import com.irwin13.winwork.lab.controller.LogoutController;
import com.irwin13.winwork.lab.controller.MainController;
import com.irwin13.winwork.lab.controller.NoAccessController;
import com.irwin13.winwork.lab.controller.StatusController;
import com.irwin13.winwork.lab.controller.app.AppOptionController;
import com.irwin13.winwork.lab.controller.app.AppPermissionController;
import com.irwin13.winwork.lab.controller.app.AppRoleController;
import com.irwin13.winwork.lab.controller.app.AppSettingController;
import com.irwin13.winwork.lab.controller.app.AppUserController;
import com.irwin13.winwork.lab.filter.MDCNodeNameFilter;
import com.irwin13.winwork.lab.filter.UserAccessFilter;
import com.irwin13.winwork.lab.filter.UserSessionFilter;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class WebModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {

        filter("/*").through(MDCNodeNameFilter.class);

        if (Boolean.valueOf(getServletContext().getInitParameter("enableUserSessionFilter"))) {
            // UserSession filter
            Map<String, String> sessionFilterParam = new HashMap<String, String>();
            StringBuilder noSessionString = new StringBuilder();
            noSessionString.append("/login;");
            noSessionString.append("/logout;");
            noSessionString.append("/api;");
            noSessionString.append("/status");
            sessionFilterParam.put("noSessionPath", noSessionString.toString());
            filter("/*").through(UserSessionFilter.class, sessionFilterParam);
        }

        if (Boolean.valueOf(getServletContext().getInitParameter("enableUserAccessFilter"))) {
            // UserAccess filter
            Map<String, String> accessFilterParam = new HashMap<String, String>();
            StringBuilder allAccessString = new StringBuilder();
            allAccessString.append("/;");
            allAccessString.append("/index;");
            allAccessString.append("/main;");
            allAccessString.append("/login;");
            allAccessString.append("/logout;");
            allAccessString.append("/status;");
            allAccessString.append("/publicAccess;");
            allAccessString.append("/api;");
            allAccessString.append("/changePassword");
            accessFilterParam.put("allAccessPath", allAccessString.toString());
            filter("/*").through(UserAccessFilter.class, accessFilterParam);
        }

        // common controller
        bind(StatusController.class);
        bind(LoginController.class);
        bind(LogoutController.class);
        bind(ChangePasswordController.class);
        bind(MainController.class);
        bind(LeftMenuController.class);
        bind(NoAccessController.class);

        // APP
        bind(AppOptionController.class);
        bind(AppSettingController.class);
        bind(AppUserController.class);
        bind(AppPermissionController.class);
        bind(AppRoleController.class);

        serve("/*").with(GuiceContainer.class);
    }
}
