package com.irwin13.hibernate.dao.test;

import com.irwin13.hibernate.dao.AppSettingDao;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irwin Timestamp : 28/04/2014 13:28
 */
public class AppSettingDeleteSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingDeleteSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private String id;

    @Given("a AppSetting for delete with id $id")
    public void given(String id) {
        LOGGER.debug("id = {}", id);
        this.id = id;
    }

    @When("delete AppSetting is executed")
    public void when() {
        AppSetting filter = new AppSetting();
        filter.setId(id);
        dao.delete(filter);
        LOGGER.debug("DELETE SUCCESS");
    }

    @Then("AppSetting not exists after delete should be $exists")
    public void then(String exists) {
        AppSetting appSetting = dao.getById(id, false);
        Assert.assertEquals(appSetting == null, Boolean.valueOf(exists));
    }

}
