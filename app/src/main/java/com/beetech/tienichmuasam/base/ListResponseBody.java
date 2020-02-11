package com.beetech.tienichmuasam.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponseBody<T> {
    @SerializedName("totalPage")
    private int totalPage;
    private int pageIndex;
    private int totalItem;
    private int pageSize;
    private List<T> results;

    public ListResponseBody(List<T> results) {
        this.results = results;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
