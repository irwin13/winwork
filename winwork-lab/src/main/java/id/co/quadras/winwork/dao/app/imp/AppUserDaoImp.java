package id.co.quadras.winwork.dao.app.imp;

import com.google.inject.Inject;
import id.co.quadras.winwork.dao.app.AppUserDao;
import id.co.quadras.winwork.dao.hibernate.BasicHibernateDao;
import id.co.quadras.winwork.model.entity.app.AppUser;
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
public class AppUserDaoImp implements AppUserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDaoImp.class);

    private final SessionFactory sessionFactory;
    private final BasicHibernateDao<AppUser, String> basicDao =
            new BasicHibernateDao<AppUser, String>(AppUser.class);

    @Inject
    public AppUserDaoImp(SessionFactory sessionFactory) {
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
    public List<AppUser> select(AppUser filter, SortParameter sortParameter) {
        return basicDao.select(filter, sortParameter);
    }

    @Override
    public List<AppUser> selectPaged(AppUser filter, SortParameter sortParameter, int start, int size) {
        return basicDao.selectPaged(filter, sortParameter, start, size);
    }

    @Override
    public long selectCount(AppUser filter) {
        return basicDao.selectCount(filter);
    }

    @Override
    public List<AppUser> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                        SortParameter sortParameter) {
        return basicDao.selectSearch(searchKeyword, searchProperties, sortParameter);
    }

    @Override
    public List<AppUser> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                             SortParameter sortParameter, int start, int size) {
        return basicDao.selectSearchPaged(searchKeyword, searchProperties, sortParameter, start, size);
    }

    @Override
    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties) {
        return basicDao.selectSearchCount(searchKeyword, searchProperties);
    }

    @Override
    public AppUser getById(String id, boolean init) {
        Session session = null;
        AppUser result = null;

        try {
            session = openNewSession();
            result = (AppUser) session.get(getModelClass(), id);
            if (result != null && init) {
                Hibernate.initialize(result.getAppRoleList());
            }
        } catch (RuntimeException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            closeSession(session);
        }

        return result;

    }

    @Override
    public String insert(AppUser model) {
        return basicDao.insert(model);
    }

    @Override
    public void update(AppUser model) {
        basicDao.update(model);
    }

    @Override
    public void merge(AppUser model) {
        basicDao.merge(model);
    }

    @Override
    public void delete(AppUser model) {
        basicDao.delete(model);
    }

    @Override
    public void insertOrUpdate(AppUser model) {
        basicDao.saveOrUpdate(model);
    }

    @Override
    public Class<AppUser> getModelClass() {
        return basicDao.getModelClass();
    }
}
