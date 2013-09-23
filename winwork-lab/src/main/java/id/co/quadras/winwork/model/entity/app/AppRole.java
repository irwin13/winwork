package id.co.quadras.winwork.model.entity.app;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.co.quadras.winwork.model.annotations.ExcludeFormRead;
import id.co.quadras.winwork.model.annotations.Searchable;
import id.co.quadras.winwork.model.annotations.Sortable;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.List;

/**
 * @author irwin Timestamp : 12/04/13 16:30
 */
public class AppRole extends BaseEntity {

    @ExcludeFormRead
    public static final String MODEL_NAME = "appRole";

    @Searchable
    @Sortable
    private String name;

    @Searchable
    @Sortable
    private String description;

    @ExcludeFormRead
    @JsonIgnore
    private List<AppUser> appUserList;

    @ExcludeFormRead
    private List<AppPermission> appPermissionList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

    public List<AppPermission> getAppPermissionList() {
        return appPermissionList;
    }

    public void setAppPermissionList(List<AppPermission> appPermissionList) {
        this.appPermissionList = appPermissionList;
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
