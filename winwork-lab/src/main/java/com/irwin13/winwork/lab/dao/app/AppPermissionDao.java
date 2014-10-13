package com.irwin13.winwork.lab.dao.app;


import java.util.List;

import com.irwin13.winwork.basic.dao.WinWorkDao;
import com.irwin13.winwork.basic.model.entity.app.AppPermission;

/**
 * @author irwin Timestamp : 05/07/13 10:59
 */
public interface AppPermissionDao extends WinWorkDao<AppPermission, String> {
    public List<AppPermission> getChildList(AppPermission parent);
    public List<AppPermission> getNullParent();
}
