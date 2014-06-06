package com.irwin13.winwork.mybatis.dao;

import com.google.inject.Inject;
import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.model.entity.app.AppSetting;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author irwin Timestamp : 17/04/2014 21:16
 */
public class AppSettingDaoImp implements AppSettingDao {

    private final SqlSessionFactory sqlSessionFactory;
    private final BasicMyBatisDao<AppSetting, String> basicDao;

    @Inject
    public AppSettingDaoImp(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.basicDao = new BasicMyBatisDao<AppSetting, String>(AppSetting.class, sqlSessionFactory);
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
        return basicDao.selectSearch(searchParameter);
    }

    @Override
    public List<AppSetting> selectSearch(SearchParameter searchParameter,
                                         int fetchStart, int fetchSize) {
        return basicDao.selectSearch(searchParameter, fetchStart, fetchSize);
    }

    @Override
    public long selectSearchCount(SearchParameter searchParameter) {
        return basicDao.selectSearchCount(searchParameter);
    }

    @Override
    public AppSetting getById(String id, boolean fetchChild) {
        return basicDao.selectById(id, fetchChild);
    }

    @Override
    public String insert(AppSetting model) {
        basicDao.insert(model);
        return model.getId();
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
    public Class<AppSetting> getModelClass() {
        return basicDao.getModelClass();
    }

    @Override
    public void batchInsert(List<AppSetting> modelList) {
        basicDao.batchInsert(modelList);
    }

    @Override
    public void batchUpdate(List<AppSetting> modelList) {
        basicDao.batchUpdate(modelList);
    }

    @Override
    public void batchDelete(List<AppSetting> modelList) {
        basicDao.batchDelete(modelList);
    }
}
