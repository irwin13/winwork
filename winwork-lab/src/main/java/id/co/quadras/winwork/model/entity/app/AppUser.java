package id.co.quadras.winwork.model.entity.app;

import id.co.quadras.winwork.model.annotations.ExcludeFormRead;
import id.co.quadras.winwork.model.annotations.Searchable;
import id.co.quadras.winwork.model.annotations.Sortable;
import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.Date;
import java.util.List;


public class AppUser extends BaseEntity {

    @ExcludeFormRead
    public static final String MODEL_NAME = "appUser";

    @Searchable
    @Sortable
    private String username;

    @ExcludeFormRead
    private String password;

    @Searchable
    @Sortable
    private String firstName;

    @Searchable
    @Sortable
    private String lastName;

    @Searchable
    @Sortable
    private String email;

    @Searchable
    @Sortable
    private String phone;

    @ExcludeFormRead
    private java.util.Date lastLogin;

    @ExcludeFormRead
    private String lastLoginFrom;

    @ExcludeFormRead
    private String displayLang;

    @ExcludeFormRead
    private List<AppRole> appRoleList;

    public List<AppRole> getAppRoleList() {
        return appRoleList;
    }

    public void setAppRoleList(List<AppRole> appRoleList) {
        this.appRoleList = appRoleList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getDisplayLang() {
        return displayLang;
    }

    public void setDisplayLang(String displayLang) {
        this.displayLang = displayLang;
    }

    public String getLastLoginFrom() {
        return lastLoginFrom;
    }

    public void setLastLoginFrom(String lastLoginFrom) {
        this.lastLoginFrom = lastLoginFrom;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, true);
    }

}
