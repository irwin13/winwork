package com.irwin13.hibernate.dao.test;

import com.irwin13.hibernate.dao.AppSettingDao;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 28/04/2014 13:28
 */
public class AppSettingSearchSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingSearchSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private List<AppSetting> appSettingList = new LinkedList<AppSetting>();
    private String key;

    @Given("AppSetting for search with code $key")
    public void given(String key) {
        LOGGER.debug("key = {}", key);
        this.key = key;
    }

    @When("search AppSetting is executed")
    public void when() {
        appSettingList = dao.selectSearch(
                new SearchParameter(key + "%", null, null));
    }

    @Then("AppSettingList size after search should be equals $size")
    public void then(int size) {
        Assert.assertEquals(appSettingList.size(), size);
    }

}
