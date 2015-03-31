package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkOption extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkOption";
    }

    @NotNull
    private String optionCategory;
    @NotNull
    private String optionValue;
    private String description;

    public String getOptionCategory() {
        return optionCategory;
    }

    public void setOptionCategory(String optionCategory) {
        this.optionCategory = optionCategory;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
