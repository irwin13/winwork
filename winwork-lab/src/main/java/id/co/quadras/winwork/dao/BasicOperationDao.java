package id.co.quadras.winwork.dao;

import id.co.quadras.winwork.model.vo.SortParameter;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author irwin Timestamp : 19/03/13 14:19
 */
public interface BasicOperationDao<M extends Serializable, I extends Serializable> {

    public Session openNewSession();
    public void closeSession(Session session);

    public List<M> select(M filter, SortParameter sortParameter);
    public List<M> selectPaged(M filter, SortParameter sortParameter, int start, int size);
    public long selectCount(M filter);

    public List<M> selectSearch(String searchKeyword, Map<String, Class<?>> searchProperties,
                                SortParameter sortParameter);
    public List<M> selectSearchPaged(String searchKeyword, Map<String, Class<?>> searchProperties,
                                     SortParameter sortParameter, int start, int size);
    public long selectSearchCount(String searchKeyword, Map<String, Class<?>> searchProperties);

    public M getById(I id, boolean init);

    public I insert(M model);
    public void update(M model);
    public void merge(M model);
    public void delete(M model);

    public void insertOrUpdate(M model);

    public Class<M> getModelClass();
}
