package com.example.case_study_md3.model;

public class ProductPageable {
    private int page = 1;
    private int limit = 6;
    private String kw = "";
    private String sortField = "id";
    private String order = "asc";
    private int idCategory = -1;
    private int totalPage;
    private String scale = "all";

    public ProductPageable() {
    }

    public ProductPageable(int page, int limit, String kw, String sortField, String order, int idCategory, int totalPage, String scale) {
        this.page = page;
        this.limit = limit;
        this.kw = kw;
        this.sortField = sortField;
        this.order = order;
        this.idCategory = idCategory;
        this.totalPage = totalPage;
        this.scale = scale;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
