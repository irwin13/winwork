package com.irwin13.hibernate.dao;

import com.google.inject.Inject;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import com.irwin13.winwork.basic.utilities.PojoUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author irwin Timestamp : 28/04/2014 17:31
 */
public class AppSettingDaoImp implements AppSettingDao {

    private final SessionFactory sessionFactory;
    private final BasicHibernateDao<AppSetting, String> basicDao = new BasicHibernateDao<AppSetting, String>(AppSetting.class);

    @Inject
    public AppSettingDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        basicDao.setSessionFactory(sessionFactory);
    }

    public Session openNewSession() {
        return basicDao.openNewSession();
    }

    public void closeSession(Session session) {
        basicDao.closeSession(session);
    }

    @Override
    public Class<AppSetting> getModelClass() {
        return basicDao.getModelClass();
    }

    @Override
    public List<AppSetting> select(AppSetting filter, SortParameter sortParameter) {
        return basicDao.select(filter, sortParameter);
    }

    @Override
    public List<AppSetting> select(AppSetting filter, SortParameter sortParameter, int fetchStart, int fetchSize) {
        return basicDao.select(filter, sortParameter, fetchStart, fetchSize);
    }

    @Override
    public long selectCount(AppSetting filter) {
        return basicDao.selectCount(filter);
    }

    @Override
    public List<AppSetting> selectSearch(SearchParameter searchParameter) {
        return basicDao.selectSearch(searchParameter, PojoUtil.getSearchableField(getModelClass()));
    }

    @Override
    public List<AppSetting> selectSearch(SearchParameter searchParameter,
                                         int fetchStart, int fetchSize) {
        return basicDao.selectSearch(searchParameter, PojoUtil.getSearchableField(getModelClass()),
                fetchStart, fetchSize);
    }

    @Override
    public long selectSearchCount(SearchParameter searchParameter) {
        return basicDao.selectSearchCount(searchParameter, PojoUtil.getSearchableField(getModelClass()));
    }

    @Override
    public AppSetting getById(String id, boolean fetchChild) {
        return basicDao.getById(id, fetchChild);
    }

    @Override
    public String insert(AppSetting model) {
        return basicDao.insert(model);
    }

    @Override
    public void update(AppSetting model) {
        basicDao.update(model);
    }

    @Override
    public void delete(AppSetting model) {
        basicDao.delete(model);
    }

    @Override
    public void batchInsert(List<AppSetting> modelList) {
        throw new RuntimeException("This method is not implemented, yet");
    }

    @Override
    public void batchUpdate(List<AppSetting> modelList) {
        throw new RuntimeException("This method is not implemented, yet");
    }

    @Override
    public void batchDelete(List<AppSetting> modelList) {
        throw new RuntimeException("This method is not implemented, yet");
    }

    @Override
    public void merge(AppSetting model) {
        basicDao.merge(model);
    }

    @Override
    public void saveOrUpdate(AppSetting model) {
        basicDao.saveOrUpdate(model);
    }
}
