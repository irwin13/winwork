package com.irwin13.winwork.basic.model.entity.app;

import com.irwin13.winwork.basic.annotations.FormReadExclude;
import com.irwin13.winwork.basic.annotations.Searchable;
import com.irwin13.winwork.basic.annotations.Sortable;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;
import com.irwin13.winwork.basic.utilities.WinWorkObjects;


public class AppOption extends WinWorkBasicEntity {

    @FormReadExclude
    public static final String MODEL_NAME = "appOption";

    @Searchable
    @Sortable
    private String optionCategory;

    @Searchable
    @Sortable
    private String name;

    public String getOptionCategory() {
        return optionCategory;
    }

    public void setOptionCategory(String optionCategory) {
        this.optionCategory = optionCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return WinWorkObjects.beanToString(this, true);
    }

}
