package com.irwin13.winwork.mybatis.test;

import com.irwin13.winwork.basic.test.AbstractEmbedder;

import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 06/06/2014 13:13
 */
public class BatchEmbedder extends AbstractEmbedder {

    @Override
    public List<Object> stepsList() {
        List<Object> stepsList = new LinkedList<Object>();
        stepsList.add(new BatchInsertSteps());
        stepsList.add(new BatchUpdateSteps());
        stepsList.add(new BatchDeleteSteps());
        return stepsList;
    }

    @Override
    public String storyFilter() {
        return "story/dao/batch/*.story";
    }
}
