package id.co.quadras.winwork.service;

import id.co.quadras.winwork.model.entity.BaseEntity;
import id.co.quadras.winwork.model.vo.SortParameter;

import java.io.Serializable;
import java.util.List;

/**
 * @author irwin Timestamp : 18/03/13 11:23
 */
public interface BasicOperationService<M extends BaseEntity, I extends Serializable> {

    public List<M> select(M filter, SortParameter sortParameter);
    public List<M> selectPaged(M filter, SortParameter sortParameter, int start, int size);
    public long selectCount(M filter);

    public List<M> selectSearch(String searchKeyword, SortParameter sortParameter);
    public List<M> selectSearchPaged(String searchKeyword, SortParameter sortParameter, int start, int size);
    public long selectSearchCount(String searchKeyword);

    public M getById(I id, boolean init);

    public I insert(M model);
    public void update(M model);
    public void delete(M model);
    public void softDelete(M model);
    public void insertOrUpdate(M model);

}
