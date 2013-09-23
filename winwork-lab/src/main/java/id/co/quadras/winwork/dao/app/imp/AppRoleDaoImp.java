package id.co.quadras.winwork.dao.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppRoleDao;
import id.co.quadras.winwork.dao.hibernate.BasicHibernateDao;
import id.co.quadras.winwork.model.entity.app.AppRole;
import id.co.quadras.winwork.model.vo.SortParameter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 15/04/13 11:54
 */
public class AppRoleDaoImp implements AppRoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRoleDaoImp.class);

    private final SessionFactory sessionFactory;
    private final BasicHibernateDao<AppRole, String> basicDao =
            new BasicHibernateDao<AppRole, String>(AppRole.class);

    @Inject
    public AppRoleDaoImp(SessionFactory sessionFactory) {
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
    public List<AppRole> select(AppRole filter, SortParameter sortParameter) {
        return basicDao.select(filter, sortParameter);
    }

    @Override
    public List<AppRole> selectPaged(AppRole filter, SortParameter sortParameter, int start, int size) {
        return basicDao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppRole filter) {
        return basicDao.selectCount(filter);
    }

    @Override
    public List<AppRole> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                        SortParameter sortParameter) {
        return basicDao.selectSearch(searchKeyword, searchProperties, sortParameter);
    }

    @Override
    public List<AppRole> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                             SortParameter sortParameter, int start, int size) {
        return basicDao.selectSearchPaged(searchKeyword, searchProperties, sortParameter, start, size);
    }

    @Override
    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties) {
        return basicDao.selectSearchCount(searchKeyword, searchProperties);
    }

    @Override
    public AppRole getById(String id, boolean init) {
        Session session = null;
        AppRole result = null;

        try {
            session = openNewSession();
            result = (AppRole) session.get(getModelClass(), id);
            if (result != null && init) {
                Hibernate.initialize(result.getAppUserList());
                Hibernate.initialize(result.getAppPermissionList());
            }
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    @Override
    public String insert(AppRole model) {
        return basicDao.insert(model);
    }

    @Override
    public void update(AppRole model) {
        basicDao.update(model);
    }

    @Override
    public void merge(AppRole model) {
        basicDao.merge(model);
    }

    @Override
    public void delete(AppRole model) {
        basicDao.delete(model);
    }

    @Override
    public void insertOrUpdate(AppRole model) {
        basicDao.saveOrUpdate(model);
    }

    @Override
    public Class<AppRole> getModelClass() {
        return basicDao.getModelClass();
    }
}
