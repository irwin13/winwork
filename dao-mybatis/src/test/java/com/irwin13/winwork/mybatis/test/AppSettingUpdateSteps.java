package com.irwin13.winwork.mybatis.test;

import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.mybatis.dao.AppSettingDao;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 28/04/2014 13:28
 */
public class AppSettingUpdateSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingUpdateSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private AppSetting appSetting;
    private String id;

    @Given("a AppSetting for update with id $id")
    public void given(String id) {
        LOGGER.debug("id = {}", id);
        this.id = id;
    }

    @When("update AppSetting is executed with stringValue change to $newValue")
    public void when(String newValue) {
        appSetting = dao.getById(id, false);
        appSetting.setStringValue(newValue);

        dao.update(appSetting);
    }

    @Then("AppSetting after update stringValue should be equals $code")
    public void then(String newValue) {
        Assert.assertEquals(appSetting.getStringValue(), newValue);
    }

}
