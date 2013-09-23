package id.co.quadras.winwork.service.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppRoleDao;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BaseEntityCommonService;
import id.co.quadras.winwork.service.app.AppRoleService;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:39
 */
public class AppRoleServiceImp implements AppRoleService {

    private final BaseEntityCommonService commonService;
    private final AppRoleDao dao;

    @Inject
    public AppRoleServiceImp(BaseEntityCommonService commonService, AppRoleDao dao) {
        this.commonService = commonService;
        this.dao = dao;
    }

    @Override
    public List<AppRole> select(AppRole filter, SortParameter sortParameter) {
        commonService.onSelect(filter);
        return dao.select(filter, sortParameter);
    }

    @Override
    public List<AppRole> selectPaged(AppRole filter, SortParameter sortParameter, int start, int size) {
        commonService.onSelect(filter);
        return dao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppRole filter) {
        commonService.onSelect(filter);
        return dao.selectCount(filter);
    }

    @Override
    public List<AppRole> selectSearch(String searchKeyword, SortParameter sortParameter) {
        return dao.selectSearch(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter);
    }

    @Override
    public List<AppRole> selectSearchPaged(String searchKeyword,
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
    public AppRole getById(String id, boolean init) {
        return dao.getById(id, init);
    }

    @Override
    public String insert(AppRole model) {
        commonService.onInsert(model);
        return dao.insert(model);
    }

    @Override
    public void update(AppRole model) {
        commonService.onUpdate(model);
        dao.merge(model);
    }

    @Override
    public void delete(AppRole model) {
        dao.delete(model);
    }

    @Override
    public void softDelete(AppRole model) {
        commonService.onSoftDelete(model);
        dao.merge(model);
    }

    @Override
    public void insertOrUpdate(AppRole model) {
        commonService.onInsertOrUpdate(model) ;
        dao.insertOrUpdate(model);
    }
}
