package com.example.case_study_md3.model;

import java.util.Date;

public class Cart {
    private int id;
    private int idUser;
    private float subTotal;
    private float discount;

    public Cart() {
    }

    public Cart(int id, int idUser, float subTotal, float discount) {
        this.id = id;
        this.idUser = idUser;
        this.subTotal = subTotal;
        this.discount = discount;
    }

    public Cart(int idUser, float subTotal, float discount) {
        this.idUser = idUser;
        this.subTotal = subTotal;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
