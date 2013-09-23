package id.co.quadras.winwork.service.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppOptionDao;
import id.co.quadras.winwork.model.entity.app.AppOption;
import id.co.quadras.winwork.model.enums.SortMethod;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BaseEntityCommonService;
import id.co.quadras.winwork.service.app.AppOptionService;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.List;

/**
 * @author irwin Timestamp : 12/04/13 17:33
 */
public class AppOptionServiceImp implements AppOptionService {

    private final BaseEntityCommonService commonService;
    private final AppOptionDao dao;

    @Inject
    public AppOptionServiceImp(BaseEntityCommonService commonService, AppOptionDao dao) {
        this.commonService = commonService;
        this.dao = dao;
    }

    @Override
    public List<AppOption> select(AppOption filter, SortParameter sortParameter) {
        commonService.onSelect(filter);
        return dao.select(filter, sortParameter);
    }

    @Override
    public List<AppOption> selectPaged(AppOption filter, SortParameter sortParameter, int start, int size) {
        commonService.onSelect(filter);
        return dao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppOption filter) {
        commonService.onSelect(filter);
        return dao.selectCount(filter);
    }

    @Override
    public List<AppOption> selectSearch(String searchKeyword, SortParameter sortParameter) {
        return dao.selectSearch(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter);
    }

    @Override
    public List<AppOption> selectSearchPaged(String searchKeyword,
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
    public AppOption getById(String id, boolean init) {
        return dao.getById(id, init);
    }

    @Override
    public String insert(AppOption model) {
        commonService.onInsert(model);
        return dao.insert(model);
    }

    @Override
    public void update(AppOption model) {
        commonService.onUpdate(model);
        dao.merge(model);
    }

    @Override
    public void delete(AppOption model) {
        dao.delete(model);
    }

    @Override
    public void softDelete(AppOption model) {
        commonService.onSoftDelete(model);
        dao.merge(model);
    }

    @Override
    public void insertOrUpdate(AppOption model) {
        commonService.onInsertOrUpdate(model) ;
        dao.insertOrUpdate(model);
    }

    @Override
    public List<AppOption> selectByCategory(String category) {
        List<AppOption> result;
        AppOption filter = new AppOption();
        filter.setActive(Boolean.TRUE);
        filter.setOptionCategory(category);
        result = select(filter, new SortParameter("name", SortMethod.ASC));
        return result;
    }
}
