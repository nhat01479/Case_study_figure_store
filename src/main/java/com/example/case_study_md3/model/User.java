package com.example.case_study_md3.model;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private Date dob;
    private String address;
    private String phone;
    private String email;
    private String password;
    private Date createAt;
    private ERole eRole;
    private Date deleteAt;

    public User() {
    }

    public User(int id, String name, Date dob, String address, String phone, String email, String password, Date createAt, ERole eRole, Date deleteAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createAt = createAt;
        this.eRole = eRole;
        this.deleteAt = deleteAt;
    }

    public User(String name, Date dob, String address, String phone, String email, String password, Date createAt, ERole eRole) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createAt = createAt;
        this.eRole = eRole;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
}
