package com.irwin13.winwork.basic.service;

import com.irwin13.winwork.basic.model.SearchParameter;
import com.irwin13.winwork.basic.model.SortParameter;
import com.irwin13.winwork.basic.model.entity.WinWorkBasicEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author irwin Timestamp : 17/04/2014 19:21
 */
public interface WinWorkService<M extends WinWorkBasicEntity, I extends Serializable> {

    public List<M> select(M filter, SortParameter sortParameter);
    public List<M> select(M filter, SortParameter sortParameter, int fetchStart, int fetchSize);
    public long selectCount(M filter);

    public List<M> selectSearch(SearchParameter searchParameter);
    public List<M> selectSearch(SearchParameter searchParameter, int fetchStart, int fetchSize);
    public long selectSearchCount(String searchKeyword);

    public M getById(I id, boolean fetchChild);

    public I insert(M model);
    public void update(M model);
    public void softDelete(M model);
    public void insertOrUpdate(M model);
}
