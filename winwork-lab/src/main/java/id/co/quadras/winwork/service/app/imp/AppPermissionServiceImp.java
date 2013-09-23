package id.co.quadras.winwork.service.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppPermissionDao;
import id.co.quadras.winwork.model.entity.app.AppPermission;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BaseEntityCommonService;
import id.co.quadras.winwork.service.app.AppPermissionService;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:39
 */
public class AppPermissionServiceImp implements AppPermissionService {

    private final BaseEntityCommonService commonService;
    private final AppPermissionDao dao;

    @Inject
    public AppPermissionServiceImp(BaseEntityCommonService commonService, AppPermissionDao dao) {
        this.commonService = commonService;
        this.dao = dao;
    }

    @Override
    public List<AppPermission> select(AppPermission filter, SortParameter sortParameter) {
        commonService.onSelect(filter);
        return dao.select(filter, sortParameter);
    }

    @Override
    public List<AppPermission> selectPaged(AppPermission filter, SortParameter sortParameter, int start, int size) {
        commonService.onSelect(filter);
        return dao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppPermission filter) {
        commonService.onSelect(filter);
        return dao.selectCount(filter);
    }

    @Override
    public List<AppPermission> selectSearch(String searchKeyword, SortParameter sortParameter) {
        return dao.selectSearch(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter);
    }

    @Override
    public List<AppPermission> selectSearchPaged(String searchKeyword,
                                             SortParameter sortParameter, int start, int size) {
        return dao.selectSearchPaged(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter, start, size);
    }

    @Override
    public long selectSearchCount(String searchKeyword) {
        return dao.selectSearchCount(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()));
    }

    @Override
    public AppPermission getById(String id, boolean init) {
        return dao.getById(id, init);
    }

    @Override
    public String insert(AppPermission model) {
        commonService.onInsert(model);
        if (model.getMenuOrder() == null) {
            model.setMenuOrder(0);
        }
        return dao.insert(model);
    }

    @Override
    public void update(AppPermission model) {
        commonService.onUpdate(model);
        if (model.getMenuOrder() == null) {
            model.setMenuOrder(0);
        }
        dao.merge(model);
    }

    @Override
    public void delete(AppPermission model) {
        dao.delete(model);
    }

    @Override
    public void softDelete(AppPermission model) {
        commonService.onSoftDelete(model);
        if (model.getMenuOrder() == null) {
            model.setMenuOrder(0);
        }
        dao.merge(model);
    }

    @Override
    public void insertOrUpdate(AppPermission model) {
        commonService.onInsertOrUpdate(model) ;
        if (model.getMenuOrder() == null) {
            model.setMenuOrder(0);
        }
        dao.insertOrUpdate(model);
    }

    @Override
    public List<AppPermission> getChildList(AppPermission parent) {
        return dao.getChildList(parent);
    }

    @Override
    public List<AppPermission> getNullParent() {
        return dao.getNullParent();
    }

    @Override
    public List<AppPermission> getMenuList(List<AppRole> appRoleList) {
        List<AppPermission> menuList = null;

        if (appRoleList != null && !appRoleList.isEmpty()) {
            menuList = new LinkedList<AppPermission>();
            for (AppRole appRole : appRoleList) {
                List<AppPermission> subMenuList = appRole.getAppPermissionList();
                if (subMenuList != null) {
                    for (AppPermission appPermission : subMenuList) {
                        if ((appPermission.getAsMenu() != null && appPermission.getAsMenu())
                                && !menuList.contains(appPermission)) {
                            menuList.add(appPermission);
                        }
                    }
                }
            }
        }

        if (menuList != null) {
            Collections.sort(menuList, new Comparator<AppPermission>() {
                @Override
                public int compare(AppPermission o1, AppPermission o2) {
                    int result = 0;
                    if (o1.getMenuOrder() > o2.getMenuOrder()) {
                        result = 1;
                    } else if (o1.getMenuOrder() < o2.getMenuOrder()) {
                        result = -1;
                    } else if (o1.getMenuOrder() == o2.getMenuOrder()) {
                        result = 0;
                    }
                    return result;
                }
            });
        } else {
            menuList = Collections.emptyList();
        }

        return menuList;
    }

    @Override
    public List<AppPermission> sortMenu(List<AppPermission> menuList) {
        List<AppPermission> orderList = new LinkedList<AppPermission>();
        for (AppPermission menu : menuList) {
            addChild(menuList, menu);
            if (menu.getParentMenu() == null)
                orderList.add(menu);
        }
        return orderList;
    }

    private void addChild(List<AppPermission> menuList, AppPermission menu) {
        List<AppPermission> childList = new LinkedList<AppPermission>();
        for (AppPermission menu2 : menuList) {
            if (menu2.getParentMenu() != null && menu2.getParentMenu().equals(menu)) {
                childList.add(menu2);
                addChild(menuList, menu2);
            }

            if (childList.size() > 0)
                menu.setChildMenuList(childList);
        }
    }

    @Override
    public List<AppPermission> getUserAccessList(List<AppRole> appRoleList) {
        List<AppPermission> menuList = null;

        if (appRoleList != null && !appRoleList.isEmpty()) {
            menuList = new LinkedList<AppPermission>();
            for (AppRole appRole : appRoleList) {
                List<AppPermission> subMenuList = appRole.getAppPermissionList();
                if (subMenuList != null) {
                    for (AppPermission appPermission : subMenuList) {
                        if (!menuList.contains(appPermission)) {
                            menuList.add(appPermission);
                        }
                    }
                }
            }
        }

        if (menuList != null) {
            Collections.sort(menuList, new Comparator<AppPermission>() {
                @Override
                public int compare(AppPermission o1, AppPermission o2) {
                    int result = 0;
                    if (o1.getMenuOrder() > o2.getMenuOrder()) {
                        result = 1;
                    } else if (o1.getMenuOrder() < o2.getMenuOrder()) {
                        result = -1;
                    } else if (o1.getMenuOrder() == o2.getMenuOrder()) {
                        result = 0;
                    }
                    return result;
                }
            });
        } else {
            menuList = Collections.emptyList();
        }

        return menuList;
    }

    @Override
    public List<AppPermission> sortMenuByLevel(List<AppPermission> parentMenu) {
        List<AppPermission> result = new LinkedList<AppPermission>();

        for (AppPermission menu : parentMenu) {
            if (!menu.getName().equalsIgnoreCase("Home")) result.add(menu);
            getChildMenu(result, 0, menu);
        }

        return result;
    }

    private void getChildMenu(List<AppPermission> result, int level, AppPermission parent) {

        List<AppPermission> childList = getChildList(parent);
        if (childList != null) {
            int count = 5;
            for (AppPermission menu : childList) {
                StringBuilder sb = new StringBuilder();
                sb.delete(0, sb.length());
                for(int i =0 ; i < (level + count); i++){
                    sb.append("&nbsp;");
                }
                menu.setTab(sb.toString());
                result.add(menu);
                level++;
                getChildMenu(result, level, menu);
                level--;
            }
        }
    }

}
