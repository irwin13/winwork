package com.irwin13.hibernate.dao.test;

import com.irwin13.hibernate.dao.AppSettingDao;
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
public class AppSettingSelectSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettingSelectSteps.class);
    private AppSettingDao dao = AppSettingDaoTest.injector.getInstance(AppSettingDao.class);

    private List<AppSetting> appSettingList = new LinkedList<AppSetting>();
    private String code;

    @Given("AppSetting for select with code $code")
    public void given(String code) {
        LOGGER.debug("code = {}", code);
        this.code = code;
    }

    @When("select AppSetting is executed")
    public void when() {
        AppSetting filter = new AppSetting();
        filter.setCode(code);
        appSettingList = dao.select(filter, null);
    }

    @Then("AppSettingList size after select should be equals $size")
    public void then(int size) {
        Assert.assertEquals(appSettingList.size(), size);
    }

}
