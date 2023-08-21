package com.example.case_study_md3.model;

import java.util.Date;

public class Category {
    private int id;
    private String name;
    private Date deleteAt;

    public Category() {
    }

    public Category(int id, String name, Date deleteAt) {
        this.id = id;
        this.name = name;
        this.deleteAt = deleteAt;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}
