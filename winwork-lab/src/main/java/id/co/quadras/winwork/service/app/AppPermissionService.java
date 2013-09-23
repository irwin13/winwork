package id.co.quadras.winwork.service.app;

import id.co.quadras.winwork.model.entity.app.AppPermission;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.service.BasicOperationService;

import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:21
 */
public interface AppPermissionService extends BasicOperationService<AppPermission, String> {

    public List<AppPermission> getChildList(AppPermission parent);
    public List<AppPermission> getNullParent();

    public List<AppPermission> getMenuList(List<AppRole> appRoleList);
    public List<AppPermission> sortMenu(List<AppPermission> menuList);
    public List<AppPermission> getUserAccessList(List<AppRole> appRoleList);

    public List<AppPermission> sortMenuByLevel(List<AppPermission> parentMenu);
}
