package id.co.quadras.winwork.service;

import com.google.common.base.Strings;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.shared.WinWorkConstants;

import java.util.Date;

/**
 * @author irwin Timestamp : 26/03/13 11:25
 */
public class BaseEntityCommonService {

    public void onSelect(BaseEntity model) {
        if (model != null) {
            model.setActive(Boolean.TRUE);
        }
    }

    public void onInsert(BaseEntity model) {
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
        if (model.getLastUpdateDate() == null) {
            model.setLastUpdateDate(current);
        }

        model.setActive(Boolean.TRUE);
    }

    public void onUpdate(BaseEntity model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }
        if (model.getLastUpdateDate() == null) {
            model.setLastUpdateDate(new Date());
        }
    }

    public void onSoftDelete(BaseEntity model) {
        if (Strings.isNullOrEmpty(model.getLastUpdateBy())) {
            model.setLastUpdateBy(WinWorkConstants.USER_SYSTEM);
        }
        if (model.getLastUpdateDate() == null) {
            model.setLastUpdateDate(new Date());
        }

        model.setActive(Boolean.FALSE);
    }

    public void onInsertOrUpdate(BaseEntity model) {
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
        if (model.getLastUpdateDate() == null) {
            model.setLastUpdateDate(current);
        }
        model.setActive(Boolean.TRUE);
    }

}
