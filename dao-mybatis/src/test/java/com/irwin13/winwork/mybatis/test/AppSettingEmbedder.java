package com.irwin13.winwork.mybatis.test;

import com.irwin13.winwork.basic.test.AbstractEmbedder;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 21/04/2014 20:07
 */
public class AppSettingEmbedder extends AbstractEmbedder {

    @Override
    public List<Object> stepsList() {
        List<Object> stepsList = new LinkedList<Object>();
        stepsList.add(new AppSettingDeleteSteps());
        stepsList.add(new AppSettingInsertSteps());
        stepsList.add(new AppSettingSearchSteps());
        stepsList.add(new AppSettingSelectSteps());
        stepsList.add(new AppSettingUpdateSteps());
        return stepsList;
    }

    @Override
    public String storyFilter() {
        return "story/dao/**/*.story";
    }
}
