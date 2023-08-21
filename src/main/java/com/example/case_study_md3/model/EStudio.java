package com.example.case_study_md3.model;

public enum EStudio {
    STU1(1,"AniMester"),STU2(2,"Jimei Palace"),STU3(3,"Bear Panda"),STU4(4,"Iron Kite Studio"),STU5(5,"KITSUNE STATUE"),STU6(6,"miHoYo");
    private int id;
    private String name;

    EStudio(int id, String name) {
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
    public static EStudio getStudio(String name){
        for (EStudio eStudio : EStudio.values()){
            if (eStudio.getName().equals(name)){
                return eStudio;
            }
        }
        return null;
    }
}
