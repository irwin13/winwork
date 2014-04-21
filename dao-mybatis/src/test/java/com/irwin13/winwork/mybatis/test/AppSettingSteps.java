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

import java.util.Date;

/**
 * @author irwin Timestamp : 21/04/2014 20:08
 */
public class AppSettingSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private AppSetting appSetting;
    private String code;

    @Given("a AppSetting with code $code")
    public void init(String code) {
        LOGGER.debug("code = {}", code);
        this.code = code;
    }

    @When("CRUD AppSettingDao is executed")
    public void crudExecuted() {
        AppSetting setting = new AppSetting();
        setting.setActive(Boolean.TRUE);
        setting.setId(StringUtil.random32UUID());
        setting.setCode(code);
        setting.setCreateBy("TEST");
        setting.setLastUpdateBy("TEST");
        setting.setCreateDate(new Date());
        setting.setLastUpdateDate(new Date());

        String generatedId = dao.insert(setting);
        LOGGER.debug("appSetting generatedId = {}", generatedId);
        LOGGER.debug("INSERT SUCCESS");

        appSetting = dao.getById(generatedId, false);
        LOGGER.debug("getById  = {}", appSetting);

        appSetting.setStringValue("NEW VALUE");
        appSetting.setLastUpdateDate(new Date());
        dao.update(appSetting);
        LOGGER.debug("UPDATE SUCCESS");

        dao.delete(appSetting);
        LOGGER.debug("DELETE SUCCESS");
    }

    @Then("AppSetting code equals $code")
    public void theKeyShouldBe(String code) {
        Assert.assertEquals(this.code, code);
        Assert.assertNotNull(appSetting.getStringValue());
    }

}
