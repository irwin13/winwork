package com.irwin13.winwork.basic.model;

import com.irwin13.winwork.basic.utilities.PojoUtil;

/**
 * @author irwin Timestamp : 12/04/13 17:44
 */
public class PaginationModel {

    private long totalRecord;
    private int totalPage;
    private int totalPageMin1;
    private int currentPage;
    private int previous;
    private int next;
    private int start;

    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPageMin1() {
        return totalPageMin1;
    }
    public void setTotalPageMin1(int totalPageMin1) {
        this.totalPageMin1 = totalPageMin1;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPrevious() {
        return previous;
    }
    public void setPrevious(int previous) {
        this.previous = previous;
    }
    public int getNext() {
        return next;
    }
    public void setNext(int next) {
        this.next = next;
    }
    public long getTotalRecord() {
        return totalRecord;
    }
    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return PojoUtil.beanToString(this, false);
    }
}
