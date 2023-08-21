package com.example.case_study_md3.model;

public enum EScale {
    S1(1,"1:4"),S2(2,"1:6"),S3(3,"1:9"),S4(4,"1:10"),S5(5,"1:18"),S6(6,"1:12"),S7(7,"1:48");
    private int id;
    private String scale;

    EScale(int id, String scale) {
        this.id = id;
        this.scale = scale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
    public static EScale getScaleByScale(String scale){
        for (EScale eScale: EScale.values()
        ) {
            if (eScale.getScale().equals(scale)){
                return eScale;
            }
        }
        return null;
    }
}
