package com.hhs.testproject.models;

import com.hhs.testproject.adapters.SteakType;

import java.util.Locale;

public class CardModel {
    private SteakType steak;
    private String rarity;
    private int timeInSeconds;
    private int currentTemp;
    private int targetTemp;
    private boolean isNotification;

    public CardModel() {
    }

    public CardModel(SteakType steak, String rarity, int timeInSeconds, int currentTemp, int targetTemp, boolean isNotification) {
        this.steak = steak;
        this.rarity = rarity;
        this.timeInSeconds = timeInSeconds;
        this.currentTemp = currentTemp;
        this.targetTemp = targetTemp;
        this.isNotification = isNotification;
    }

    public SteakType getSteak() {
        return steak;
    }

    public void setSteak(SteakType steak) {
        this.steak = steak;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(int targetTemp) {
        this.targetTemp = targetTemp;
    }

    // Only Show hours and minutes when they are not 0
    // TODO: Clean up code
    public String getTime() {
        if((timeInSeconds % 3600) / 60 == 0){
            return String.format(Locale.US, "%du", timeInSeconds / 3600);
        }else if (timeInSeconds / 3600 == 0){
            return String.format(Locale.US, "%dm", (timeInSeconds % 3600) / 60);
        }
        return String.format(Locale.US, "%du %dm", timeInSeconds / 3600, (timeInSeconds % 3600) / 60);
    }

    public int getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public boolean isNotification() {
        return isNotification;
    }

    public void setNotification(boolean notification) {
        isNotification = notification;
    }

    public String getTitle() {
        return steak.getName() + " " + rarity;
    }
}
