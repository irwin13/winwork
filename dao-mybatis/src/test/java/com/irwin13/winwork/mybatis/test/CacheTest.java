package com.irwin13.winwork.mybatis.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.mybatis.dao.AppSettingDao;
import com.irwin13.winwork.mybatis.guice.MyBatisModule;
import org.junit.Test;

import java.util.List;

/**
 * @author irwin Timestamp : 08/05/2014 15:34
 */
public class CacheTest {

    private static Injector injector = Guice.createInjector(new MyBatisModule());
    private AppSettingDao appSettingDao = injector.getInstance(AppSettingDao.class);

    @Test
    public void queryById() {
        String id = "6FF11123090345659A6DFD8C8C8652G0";
        for (int i = 0; i < 10; i++) {
            appSettingDao.getById(id, false);
        }
    }

    @Test
    public void querySelect() {
        AppSetting filter = new AppSetting();
        filter.setCode("http_method");
        for (int i = 0; i < 10; i++) {
            appSettingDao.select(filter, null);
        }

    }

    @Test
    public void querySearch() {
        for (int i = 0; i < 10; i++) {
            appSettingDao.selectSearch(null, new SearchParameter("key", null, null));
        }
    }
}
