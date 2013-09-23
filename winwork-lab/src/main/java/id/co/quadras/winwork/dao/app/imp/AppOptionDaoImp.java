package id.co.quadras.winwork.dao.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppOptionDao;
import id.co.quadras.winwork.dao.hibernate.BasicHibernateDao;
import id.co.quadras.winwork.model.entity.app.AppOption;
import id.co.quadras.winwork.model.vo.SortParameter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 12/04/13 17:29
 */
public class AppOptionDaoImp implements AppOptionDao {

    private final SessionFactory sessionFactory;
    private final BasicHibernateDao<AppOption, String> basicDao =
            new BasicHibernateDao<AppOption, String>(AppOption.class);

    @Inject
    public AppOptionDaoImp(SessionFactory sessionFactory) {
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
    public List<AppOption> select(AppOption filter, SortParameter sortParameter) {
        return basicDao.select(filter, sortParameter);
    }

    @Override
    public List<AppOption> selectPaged(AppOption filter, SortParameter sortParameter, int start, int size) {
        return basicDao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppOption filter) {
        return basicDao.selectCount(filter);
    }

    @Override
    public List<AppOption> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                          SortParameter sortParameter) {
        return basicDao.selectSearch(searchKeyword, searchProperties, sortParameter);
    }

    @Override
    public List<AppOption> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                               SortParameter sortParameter, int start, int size) {
        return basicDao.selectSearchPaged(searchKeyword, searchProperties, sortParameter, start, size);
    }

    @Override
    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties) {
        return basicDao.selectSearchCount(searchKeyword, searchProperties);
    }

    @Override
    public AppOption getById(String id, boolean init) {
        return basicDao.getById(id, init);
    }

    @Override
    public String insert(AppOption model) {
        return basicDao.insert(model);
    }

    @Override
    public void update(AppOption model) {
        basicDao.update(model);
    }

    @Override
    public void merge(AppOption model) {
        basicDao.merge(model);
    }

    @Override
    public void delete(AppOption model) {
        basicDao.delete(model);
    }

    @Override
    public void insertOrUpdate(AppOption model) {
        basicDao.saveOrUpdate(model);
    }

    @Override
    public Class<AppOption> getModelClass() {
        return basicDao.getModelClass();
    }
}