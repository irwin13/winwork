package com.irwin13.winwork.basic.utilities;

import com.irwin13.winwork.basic.model.PagingModel;

/**
 * @author irwin Timestamp : 12/04/13 17:45
 */
public class PagingUtil {

    public static PagingModel getPagingModel(long totalRecord, int start, int pageSize) {

        PagingModel pagingModel = new PagingModel();
        pagingModel.setTotalRecord(totalRecord);

        totalRecord = totalRecord - 1;
        if (totalRecord < 0) totalRecord = 0;

        int totalPage = (int) (totalRecord + pageSize) / pageSize;
        int currentPage = (start + pageSize) / pageSize;

        pagingModel.setTotalPage(totalPage);
        pagingModel.setTotalPageMin1(totalPage - 1);
        pagingModel.setCurrentPage(currentPage);

        int previous = 0;
        if (start != 0) {
            previous = start - pageSize;
        }

        int next = start + pageSize;

        pagingModel.setStart(start);
        pagingModel.setNext(next);
        pagingModel.setPrevious(previous);

        return pagingModel;
    }

}
