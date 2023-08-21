package com.example.case_study_md3.model;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private float price;
    private int leftQuantity;
    private Date createAt;
    private EScale eScale;
    private int idCategory;
    private String description;
    private String imgLink;
    private Date deleteAt;
    private EStudio eStudio;

    public Product() {
    }

    public Product(int id, String name, float price, int leftQuantity, Date createAt, EScale eScale, int idCategory, String description, String imgLink, Date deleteAt, EStudio eStudio) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.leftQuantity = leftQuantity;
        this.createAt = createAt;
        this.eScale = eScale;
        this.idCategory = idCategory;
        this.description = description;
        this.imgLink = imgLink;
        this.deleteAt = deleteAt;
        this.eStudio = eStudio;
    }

    public Product(String name, float price, int leftQuantity, Date createAt, EScale eScale, int idCategory, String description, String imgLink, EStudio eStudio) {
        this.name = name;
        this.price = price;
        this.leftQuantity = leftQuantity;
        this.createAt = createAt;
        this.eScale = eScale;
        this.idCategory = idCategory;
        this.description = description;
        this.imgLink = imgLink;
        this.eStudio = eStudio;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getLeftQuantity() {
        return leftQuantity;
    }

    public void setLeftQuantity(int leftQuantity) {
        this.leftQuantity = leftQuantity;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public EScale geteScale() {
        return eScale;
    }

    public void seteScale(EScale eScale) {
        this.eScale = eScale;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public EStudio geteStudio() {
        return eStudio;
    }

    public void seteStudio(EStudio eStudio) {
        this.eStudio = eStudio;
    }
}
