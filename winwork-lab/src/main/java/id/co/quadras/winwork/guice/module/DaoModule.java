package id.co.quadras.winwork.guice.module;

import com.google.inject.AbstractModule;
import id.co.quadras.winwork.dao.app.*;
import id.co.quadras.winwork.dao.app.imp.*;

/**
 * @author irwin Timestamp : 22/09/13 0:02
 */
public class DaoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AppUserDao.class).to(AppUserDaoImp.class);
        bind(AppRoleDao.class).to(AppRoleDaoImp.class);
        bind(AppPermissionDao.class).to(AppPermissionDaoImp.class);
        bind(AppSettingDao.class).to(AppSettingDaoImp.class);
        bind(AppOptionDao.class).to(AppOptionDaoImp.class);
    }
}
