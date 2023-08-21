package com.example.case_study_md3.model;

public class Pageable {
    private int page = 1;
    private int limit = 3;
    private String kw = "";
    private String sortField = "id";
    private String order = "asc";
    private int type = -1;
    private int totalPage;

    public Pageable() {
    }

    public Pageable(int page, int limit, String kw, String sortField, String order, int type, int totalPage) {
        this.page = page;
        this.limit = limit;
        this.kw = kw;
        this.sortField = sortField;
        this.order = order;
        this.type = type;
        this.totalPage = totalPage;
    }

    public Pageable(int page, int limit, String kw, String sortField, String order, int totalPage) {
        this.page = page;
        this.limit = limit;
        this.kw = kw;
        this.sortField = sortField;
        this.order = order;
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
