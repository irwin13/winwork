package com.irwin13.winwork.lab.guice.module;

import com.google.inject.AbstractModule;
import com.irwin13.winwork.lab.service.app.AppOptionService;
import com.irwin13.winwork.lab.service.app.AppPermissionService;
import com.irwin13.winwork.lab.service.app.AppRoleService;
import com.irwin13.winwork.lab.service.app.AppSettingService;
import com.irwin13.winwork.lab.service.app.AppUserService;
import com.irwin13.winwork.lab.service.app.imp.AppOptionServiceImp;
import com.irwin13.winwork.lab.service.app.imp.AppPermissionServiceImp;
import com.irwin13.winwork.lab.service.app.imp.AppRoleServiceImp;
import com.irwin13.winwork.lab.service.app.imp.AppSettingServiceImp;
import com.irwin13.winwork.lab.service.app.imp.AppUserServiceImp;


/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        // APP
        bind(AppUserService.class).to(AppUserServiceImp.class);
        bind(AppRoleService.class).to(AppRoleServiceImp.class);
        bind(AppPermissionService.class).to(AppPermissionServiceImp.class);
        bind(AppSettingService.class).to(AppSettingServiceImp.class);
        bind(AppOptionService.class).to(AppOptionServiceImp.class);

    }
}
