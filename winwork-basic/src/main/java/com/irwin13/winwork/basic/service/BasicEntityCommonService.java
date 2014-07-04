package com.irwin13.winwork.basic.service;

import com.google.common.base.Strings;
import com.irwin13.winwork.basic.WinWorkConstants;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;

import java.util.Date;

/**
 * @author irwin Timestamp : 16/06/2014 11:27
 */
public class BasicEntityCommonService {

    public void onSelect(WinWorkBasicEntity model) {
        if (model != null) {
            model.setActive(Boolean.TRUE);
        }
    }

    public void onInsert(WinWorkBasicEntity model) {
        Date current = new Date();
        if (Strings.isNullOrEmpty(model.getCreateBy())) {
            model.setCreateBy(WinWorkConstants.USER_SYSTEM);
        }

        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }

        model.setLastUpdateDate(current);
        model.setCreateDate(current);
        model.setActive(Boolean.TRUE);
    }

    public void onUpdate(WinWorkBasicEntity model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }

        model.setLastUpdateDate(new Date());
    }

    public void onSoftDelete(WinWorkBasicEntity model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }

        model.setLastUpdateDate(new Date());
        model.setActive(Boolean.FALSE);
    }

    public void onInsertOrUpdate(WinWorkBasicEntity model) {
        Date current = new Date();
        if (Strings.isNullOrEmpty(model.getCreateBy())) {
            model.setCreateBy(WinWorkConstants.USER_SYSTEM);
        }
        if (model.getCreateDate() == null) {
            model.setCreateDate(current);
        }

        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }

        model.setLastUpdateDate(current);
        model.setActive(Boolean.TRUE);
    }

}
