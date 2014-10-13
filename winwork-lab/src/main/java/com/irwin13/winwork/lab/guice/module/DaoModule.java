package com.irwin13.winwork.lab.guice.module;

import com.google.inject.AbstractModule;
import com.irwin13.winwork.lab.dao.app.AppOptionDao;
import com.irwin13.winwork.lab.dao.app.AppPermissionDao;
import com.irwin13.winwork.lab.dao.app.AppRoleDao;
import com.irwin13.winwork.lab.dao.app.AppSettingDao;
import com.irwin13.winwork.lab.dao.app.AppUserDao;
import com.irwin13.winwork.lab.dao.app.imp.AppOptionDaoImp;
import com.irwin13.winwork.lab.dao.app.imp.AppPermissionDaoImp;
import com.irwin13.winwork.lab.dao.app.imp.AppRoleDaoImp;
import com.irwin13.winwork.lab.dao.app.imp.AppSettingDaoImp;
import com.irwin13.winwork.lab.dao.app.imp.AppUserDaoImp;

/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class DaoModule extends AbstractModule {

    @Override
    protected void configure() {
        // APP
        bind(AppUserDao.class).to(AppUserDaoImp.class);
        bind(AppRoleDao.class).to(AppRoleDaoImp.class);
        bind(AppPermissionDao.class).to(AppPermissionDaoImp.class);
        bind(AppSettingDao.class).to(AppSettingDaoImp.class);
        bind(AppOptionDao.class).to(AppOptionDaoImp.class);

    }
}
