package id.co.quadras.winwork.model.entity.app;

import id.co.quadras.winwork.model.annotations.ExcludeFormRead;
import id.co.quadras.winwork.model.annotations.Searchable;
import id.co.quadras.winwork.model.annotations.Sortable;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.util.PojoUtil;


public class AppSetting extends BaseEntity {

    @ExcludeFormRead
    public static final String MODEL_NAME = "appSetting";

    @Searchable
    @Sortable
    private String code;

    @Searchable
    @Sortable
    private String stringValue;

    @Searchable
    @Sortable
    private String description;

    @Searchable
    @Sortable
    private String settingCategory;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getStringValue() {
        return stringValue;
    }
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getSettingCategory() {
        return settingCategory;
    }
    public void setSettingCategory(String settingCategory) {
        this.settingCategory = settingCategory;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, true);
    }

}
