package com.example.case_study_md3.model;

public class CartItem {
    private int id;
    private int idCart;
    private int idProduct;
    private int quantity;
    private float total;

    public CartItem() {
    }

    public CartItem(int id, int idCart, int idProduct, int quantity, float total) {
        this.id = id;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.total = total;
    }

    public CartItem(int idCart, int idProduct, int quantity, float total) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
