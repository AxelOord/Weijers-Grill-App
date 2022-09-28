package com.hhs.testproject.adapters;

public enum RarityType {
    RARE("Ribeye", 53),
    MEDIUM_RARE("Biefstuk", 55 ),
    MEDIUM("Biefstuk", 55 ),
    MEDIUM_WELL("Biefstuk", 55 ),
    WELL("Biefstuk", 55 );

    private final String name;
    private final Integer mediumRareTemp;

    RarityType(String name, Integer mediumRareTemp){
        this.name = name;
        this.mediumRareTemp = mediumRareTemp;
    }

    public String getName() {
        return name;
    }

    public Integer getMediumRareTemp() {
        return mediumRareTemp;
    }
}