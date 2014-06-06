package com.irwin13.winwork.mybatis.test;

import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.basic.utilities.StringUtil;
import com.irwin13.winwork.mybatis.dao.AppSettingDao;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 06/06/2014 13:13
 */
public class BatchUpdateSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchUpdateSteps.class);
    private AppSettingDao dao = BatchTest.injector.getInstance(AppSettingDao.class);
    private PodamFactory podamFactory = new PodamFactoryImpl();

    private int loop;

    @Given("loop update for $loop")
    public void given(int loop) {
        LOGGER.debug("loop = {}", loop);
        this.loop = loop;
    }

    @When("batch update is executed with code $code")
    public void when(String code) {
        List<AppSetting> appSettingList = new LinkedList<AppSetting>();
        for (int i = 0; i < loop; i++) {
            AppSetting setting = podamFactory.manufacturePojo(AppSetting.class);
            setting.setId(StringUtil.random32UUID());
            setting.setCreateDate(null);
            setting.setLastUpdateDate(null);
            setting.setCode("TEST");
            appSettingList.add(setting);
        }
        dao.batchInsert(appSettingList);

        for (AppSetting setting : appSettingList) {
            setting.setCode(code);
        }
        dao.batchUpdate(appSettingList);
    }

    @Then("should be exists $count with code $code")
    public void then(int count, String code) {
        AppSetting filter = new AppSetting();
        filter.setCode(code);
        Assert.assertEquals(dao.selectCount(filter), count);
    }

}
