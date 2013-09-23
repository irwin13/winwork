package id.co.quadras.winwork.dao.app;

import id.co.quadras.winwork.dao.BasicOperationDao;
import id.co.quadras.winwork.model.entity.app.AppPermission;

import java.util.List;

/**
 * @author irwin Timestamp : 05/07/13 10:59
 */
public interface AppPermissionDao extends BasicOperationDao<AppPermission, String> {
    public List<AppPermission> getChildList(AppPermission parent);
    public List<AppPermission> getNullParent();
}
