package id.co.quadras.winwork.dao.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppSettingDao;
import id.co.quadras.winwork.dao.hibernate.BasicHibernateDao;
import id.co.quadras.winwork.model.entity.app.AppSetting;
import id.co.quadras.winwork.model.vo.SortParameter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 15/04/13 11:54
 */
public class AppSettingDaoImp implements AppSettingDao {

    private final SessionFactory sessionFactory;
    private final BasicHibernateDao<AppSetting, String> basicDao =
            new BasicHibernateDao<AppSetting, String>(AppSetting.class);

    @Inject
    public AppSettingDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        basicDao.setSessionFactory(sessionFactory);
    }

    @Override
    public Session openNewSession() {
        return basicDao.openNewSession();
    }

    @Override
    public void closeSession(Session session) {
        basicDao.closeSession(session);
    }

    @Override
    public List<AppSetting> select(AppSetting filter, SortParameter sortParameter) {
        return basicDao.select(filter, sortParameter);
    }

    @Override
    public List<AppSetting> selectPaged(AppSetting filter, SortParameter sortParameter, int start, int size) {
        return basicDao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppSetting filter) {
        return basicDao.selectCount(filter);
    }

    @Override
    public List<AppSetting> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                        SortParameter sortParameter) {
        return basicDao.selectSearch(searchKeyword, searchProperties, sortParameter);
    }

    @Override
    public List<AppSetting> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                             SortParameter sortParameter, int start, int size) {
        return basicDao.selectSearchPaged(searchKeyword, searchProperties, sortParameter, start, size);
    }

    @Override
    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties) {
        return basicDao.selectSearchCount(searchKeyword, searchProperties);
    }

    @Override
    public AppSetting getById(String id, boolean init) {
        return basicDao.getById(id, init);
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
    public void merge(AppSetting model) {
        basicDao.merge(model);
    }

    @Override
    public void delete(AppSetting model) {
        basicDao.delete(model);
    }

    @Override
    public void insertOrUpdate(AppSetting model) {
        basicDao.saveOrUpdate(model);
    }

    @Override
    public Class<AppSetting> getModelClass() {
        return basicDao.getModelClass();
    }
}
