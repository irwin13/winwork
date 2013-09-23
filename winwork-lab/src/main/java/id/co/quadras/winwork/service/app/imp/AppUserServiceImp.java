package id.co.quadras.winwork.service.app.imp;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppUserDao;
import id.co.quadras.winwork.model.entity.app.AppUser;
import id.co.quadras.winwork.model.vo.SortParameter;
import id.co.quadras.winwork.service.BaseEntityCommonService;
import id.co.quadras.winwork.service.app.AppUserService;
import id.co.quadras.winwork.shared.WinWorkConstants;
import id.co.quadras.winwork.util.PojoUtil;
import id.co.quadras.winwork.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author irwin Timestamp : 15/04/13 14:39
 */
public class AppUserServiceImp implements AppUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImp.class);

    private final BaseEntityCommonService commonService;
    private final AppUserDao dao;

    @Inject
    public AppUserServiceImp(BaseEntityCommonService commonService, AppUserDao dao) {
        this.commonService = commonService;
        this.dao = dao;
    }

    @Override
    public List<AppUser> select(AppUser filter, SortParameter sortParameter) {
        commonService.onSelect(filter);
        return dao.select(filter, sortParameter);
    }

    @Override
    public List<AppUser> selectPaged(AppUser filter, SortParameter sortParameter, int start, int size) {
        commonService.onSelect(filter);
        return dao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppUser filter) {
        commonService.onSelect(filter);
        return dao.selectCount(filter);
    }

    @Override
    public List<AppUser> selectSearch(String searchKeyword, SortParameter sortParameter) {
        return dao.selectSearch(searchKeyword,
                PojoUtil.getSearchableField(dao.getModelClass()), sortParameter);
    }

    @Override
    public List<AppUser> selectSearchPaged(String searchKeyword,
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
    public AppUser getById(String id, boolean init) {
        return dao.getById(id, init);
    }

    @Override
    public String insert(AppUser model) {
        commonService.onInsert(model);
        if (Strings.isNullOrEmpty(model.getPassword())) {
            try {
                model.setPassword(SecurityUtil.createHash(WinWorkConstants.DEFAULT_PASSWORD, SecurityUtil.DEFAULT_HASH));
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }

        if (Strings.isNullOrEmpty(model.getDisplayLang())) {
            model.setDisplayLang(WinWorkConstants.DEFAULT_LANG);
        }
        return dao.insert(model);
    }

    @Override
    public void update(AppUser model) {
        commonService.onUpdate(model);
        dao.merge(model);
    }

    @Override
    public void delete(AppUser model) {
        dao.delete(model);
    }

    @Override
    public void softDelete(AppUser model) {
        commonService.onSoftDelete(model);
        dao.merge(model);
    }

    @Override
    public void insertOrUpdate(AppUser model) {
        commonService.onInsertOrUpdate(model) ;
        dao.insertOrUpdate(model);
    }
}
