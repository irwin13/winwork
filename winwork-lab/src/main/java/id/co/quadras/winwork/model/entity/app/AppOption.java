package id.co.quadras.winwork.model.entity.app;

import id.co.quadras.winwork.model.annotations.ExcludeFormRead;
import id.co.quadras.winwork.model.annotations.Searchable;
import id.co.quadras.winwork.model.annotations.Sortable;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.util.PojoUtil;

public class AppOption extends BaseEntity {

    @ExcludeFormRead
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
        return PojoUtil.beanToString(this, true);
    }

}
