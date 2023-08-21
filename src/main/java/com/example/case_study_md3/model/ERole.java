package com.example.case_study_md3.model;

public enum ERole {
    ADMIN(1,"ADMIN"),USER(2,"USER");
    private int id;
    private String name;

    ERole(int id, String name) {
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
    public static ERole getERoleByRole(String role){
        for (ERole eRole: ERole.values()
        ) {
            if (eRole.getName().equals(role)){
                return eRole;
            }
        }
        return null;
    }
}
