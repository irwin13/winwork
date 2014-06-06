package com.irwin13.winwork.mybatis.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.mybatis.dao.AppSettingDao;
import com.irwin13.winwork.mybatis.dao.AppSettingDaoImp;
import com.irwin13.winwork.mybatis.guice.MyBatisModule;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 06/06/2014 11:24
 */
public class BatchInsertTest {

    private Injector injector = Guice.createInjector(new MyBatisModule());
    private PodamFactory podamFactory = new PodamFactoryImpl();

    @Test
    public void insert() {
        int countData = 5000;
        List<AppSetting> batchList = new LinkedList<AppSetting>();
        for (int i = 0; i < countData; i++) {
            AppSetting appSetting = podamFactory.manufacturePojo(AppSetting.class);
            appSetting.setCode("insert");
            appSetting.setCreateDate(null);
            appSetting.setLastUpdateDate(null);
            batchList.add(appSetting);
        }

        AppSettingDao dao = injector.getInstance(AppSettingDaoImp.class);
        dao.batchInsert(batchList);
        Assert.assertNotNull(batchList);
    }
}
