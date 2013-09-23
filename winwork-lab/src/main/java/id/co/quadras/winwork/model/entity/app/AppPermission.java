package id.co.quadras.winwork.model.entity.app;

import id.co.quadras.winwork.model.annotations.ExcludeFormRead;
import id.co.quadras.winwork.model.annotations.Searchable;
import id.co.quadras.winwork.model.annotations.Sortable;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.List;


public class AppPermission extends BaseEntity {

    @ExcludeFormRead
    public static final String MODEL_NAME = "appPermission";

    @Searchable
    @Sortable
    private String name;

    @Searchable
    @Sortable
    private String description;

    @Searchable
    @Sortable
    private String httpPath;

    @Searchable
    @Sortable
    private String httpMethod;

    private Boolean asMenu;
    private Integer menuOrder;
    private AppPermission parentMenu;

    @ExcludeFormRead
    private String iconFile;
    @ExcludeFormRead
    private transient List<AppPermission> childMenuList;
    @ExcludeFormRead
    private transient String tab;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getIconFile() {
        return iconFile;
    }

    public void setIconFile(String iconFile) {
        this.iconFile = iconFile;
    }

    public Boolean getAsMenu() {
        return asMenu;
    }

    public void setAsMenu(Boolean asMenu) {
        this.asMenu = asMenu;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public AppPermission getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(AppPermission parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<AppPermission> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<AppPermission> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, true);
    }

}
