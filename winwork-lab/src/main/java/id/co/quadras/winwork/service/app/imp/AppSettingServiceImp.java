package id.co.quadras.winwork.service.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppSettingDao;
import id.co.quadras.winwork.model.entity.app.AppSetting;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BaseEntityCommonService;
import id.co.quadras.winwork.service.app.AppSettingService;
import id.co.quadras.winwork.shared.WinWorkException;
import id.co.quadras.winwork.util.PojoUtil;

import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:39
 *
 * Edit By : Nova, 02/05/13
 * Add implement method getByCategory
 *
 */
public class AppSettingServiceImp implements AppSettingService {

    private final BaseEntityCommonService commonService;
    private final AppSettingDao dao;

    @Inject
    public AppSettingServiceImp(BaseEntityCommonService commonService, AppSettingDao dao) {
        this.commonService = commonService;
        this.dao = dao;
    }

    @Override
    public List<AppSetting> select(AppSetting filter, SortParameter sortParameter) {
        commonService.onSelect(filter);
        return dao.select(filter, sortParameter);
    }

    @Override
    public List<AppSetting> selectPaged(AppSetting filter, SortParameter sortParameter, int start, int size) {
        commonService.onSelect(filter);
        return dao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppSetting filter) {
        commonService.onSelect(filter);
        return dao.selectCount(filter);
    }

    @Override
    public List<AppSetting> selectSearch(String searchKeyword, SortParameter sortParameter) {
        return dao.selectSearch(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter);
    }

    @Override
    public List<AppSetting> selectSearchPaged(String searchKeyword,
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
    public AppSetting getById(String id, boolean init) {
        return dao.getById(id, init);
    }

    @Override
    public String insert(AppSetting model) {
        commonService.onInsert(model);
        return dao.insert(model);
    }

    @Override
    public void update(AppSetting model) {
        commonService.onUpdate(model);
        dao.merge(model);
    }

    @Override
    public void delete(AppSetting model) {
        dao.delete(model);
    }

    @Override
    public void softDelete(AppSetting model) {
        commonService.onSoftDelete(model);
        dao.merge(model);
    }

    @Override
    public void insertOrUpdate(AppSetting model) {
        commonService.onInsertOrUpdate(model) ;
        dao.insertOrUpdate(model);
    }

    @Override
    public AppSetting getByCode(String code) {
        AppSetting appSetting = null;

        AppSetting filter = new AppSetting();
        filter.setActive(Boolean.TRUE);
        filter.setCode(code);

        List<AppSetting> list = select(filter, null);
        if (list != null && !list.isEmpty()) {
            appSetting = list.get(0);
        }

        return appSetting;
    }

    @Override
    public List<AppSetting> getByCategory(String category) {

        AppSetting filter = new AppSetting();
        filter.setActive(Boolean.TRUE);
        filter.setSettingCategory(category);

        List<AppSetting> listAppSetting = select(filter, null);

        return listAppSetting;
    }

    @Override
    public String getSettingStringValue(String code) {
        String result;
        AppSetting appSetting = getByCode(code);
        if (appSetting == null) {
            throw new WinWorkException("FATAL : AppSetting with code = '" + code +"' not found in database");
        }
        result = appSetting.getStringValue();
        return result;
    }

    @Override
    public int getSettingIntValue(String code) {
        return Integer.valueOf(getSettingStringValue(code));
    }
}
