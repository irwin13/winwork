package com.irwin13.winwork.entity;

import com.irwin13.winwork.core.model.WinWorkEntity;

import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 31/03/2015.
 */
public class WinWorkSetting extends WinWorkEntity {

    @Override
    public String entityName() {
        return "winWorkSetting";
    }

    @NotNull
    private String settingCode;
    @NotNull
    private String settingValue;
    private String description;

    public String getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
