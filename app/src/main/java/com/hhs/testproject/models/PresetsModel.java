package com.hhs.testproject.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.hhs.testproject.adapters.PresetCardAdapter;
import com.hhs.testproject.adapters.SteakType;

import java.util.ArrayList;

public class PresetsModel extends AndroidViewModel {
    private final PresetCardAdapter adapter;

    public PresetsModel(Application application, PresetCardAdapter adapter){
        super(application);
        this.adapter = adapter;
        insertDataIntoCards();
    }

    public ArrayList<CardModel> getList() {
        return adapter.getPresetsList();
    }

    public PresetCardAdapter getAdapter() {
        return adapter;
    }

    public void changeDataOfCard() {
        getList().get(0).setCurrentTemp(getList().get(0).getCurrentTemp() + 1);
        getList().get(0).setTimeInSeconds(getList().get(0).getTimeInSeconds() - 1800);
        System.out.println(adapter.getPresetsList());

        adapter.notifyDataSetChanged();
    }

    private void insertDataIntoCards() {
        CardModel p = new CardModel(SteakType.BEEF, "Rare", 5400, 18, 51, false);
        CardModel p1 = new CardModel(SteakType.BEEF, "Well Done", 5400, 0, 75, false);
        CardModel p2 = new CardModel(SteakType.CHICKEN, "Medium Rare", 5400, 0, 75, false);

        adapter.getPresetsList().add(p);
        adapter.getPresetsList().add(p1);
        adapter.getPresetsList().add(p2);

       adapter.notifyDataSetChanged();
    }
}
