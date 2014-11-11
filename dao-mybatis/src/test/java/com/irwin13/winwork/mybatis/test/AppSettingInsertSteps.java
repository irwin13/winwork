package com.irwin13.winwork.mybatis.test;

import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.basic.utilities.WinWorkString;
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
public class AppSettingInsertSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingInsertSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private AppSetting appSetting;
    private String code;

    @Given("a AppSetting for insert with code $code")
    public void given(String code) {
        LOGGER.debug("code = {}", code);
        this.code = code;
    }

    @When("insert AppSetting is executed")
    public void when() {
        AppSetting setting = new AppSetting();
        setting.setActive(Boolean.TRUE);
        setting.setId(WinWorkString.random32UUID());
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
    }

    @Then("AppSetting exists after insert should be $exists")
    public void then(String exists) {
        Assert.assertEquals(appSetting != null, Boolean.valueOf(exists));
    }
}
