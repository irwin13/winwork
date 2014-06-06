package com.irwin13.winwork.basic.dao;

import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;

import java.io.Serializable;
import java.util.List;

/**
 * @author irwin Timestamp : 17/04/2014 19:04
 */
public interface WinWorkDao<M extends Serializable, I extends Serializable> {

    public List<M> select(M filter, SortParameter sortParameter);
    public List<M> select(M filter, SortParameter sortParameter, int fetchStart, int fetchSize);
    public long selectCount(M filter);

    public List<M> selectSearch(SearchParameter searchParameter);
    public List<M> selectSearch(SearchParameter searchParameter,
                                int fetchStart, int fetchSize);
    public long selectSearchCount(SearchParameter searchParameter);

    public M getById(I id, boolean fetchChild);

    public I insert(M model);
    public void update(M model);
    public void delete(M model);
    public Class<M> getModelClass();

    public void batchInsert(List<M> modelList);
    public void batchUpdate(List<M> modelList);
    public void batchDelete(List<M> modelList);

}
