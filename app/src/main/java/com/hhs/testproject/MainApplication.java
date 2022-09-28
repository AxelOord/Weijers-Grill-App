package com.hhs.testproject;

import android.app.Application;

import com.hhs.testproject.models.PresetsModel;

public class MainApplication extends Application {
    // Custom dependency manager
    // If Application needs to be modified, this should be in a separate container
    private PresetsModel presetsModel;

    public void setPresetsModel(PresetsModel presetsModel) {
        this.presetsModel = presetsModel;
    }

    public PresetsModel getPresetsModel() {
        return presetsModel;
    }
}
