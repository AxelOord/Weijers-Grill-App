package com.hhs.testproject.adapters;

public enum SteakType {
    RIBEYE("Ribeye"),
    BEEF("Beef"),
    CHICKEN("Chicken");

    private final String name;

    SteakType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
