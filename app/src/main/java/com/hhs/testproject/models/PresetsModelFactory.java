package com.hhs.testproject.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hhs.testproject.adapters.PresetCardAdapter;

public class PresetsModelFactory implements ViewModelProvider.Factory{
    private final Application application;
    private final PresetCardAdapter adapter;


    public PresetsModelFactory(Application application, PresetCardAdapter adapter) {
        this.application = application;
        this.adapter = adapter;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PresetsModel(application, adapter);
    }
}
