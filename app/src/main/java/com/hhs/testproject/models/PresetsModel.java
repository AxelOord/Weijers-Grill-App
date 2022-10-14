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
        return adapter.getPresets();
    }

    public PresetCardAdapter getAdapter() {
        return adapter;
    }

    public CardModel getCardByPosition(int position) {
        return getList().get(position);
    }

    public void changeDataOfCard(int position) {
        getCardByPosition(position).setCurrentTemp(getCardByPosition(position).getCurrentTemp() + 1);
        getCardByPosition(position).setTimeInSeconds(getCardByPosition(position).getTimeInSeconds() - 1800);
        System.out.println(adapter.getPresets());

        adapter.notifyItemChanged(position);
    }

    private void insertDataIntoCards() {
        CardModel p = new CardModel(SteakType.BEEF, "Rare", 5400, 18, 51, false);
        CardModel p1 = new CardModel(SteakType.BEEF, "Well Done", 5400, 0, 75, false);
        CardModel p2 = new CardModel(SteakType.CHICKEN, "Medium Rare", 5400, 0, 75, false);

        adapter.getPresets().add(p);
        adapter.getPresets().add(p1);
        adapter.getPresets().add(p2);

       adapter.notifyDataSetChanged();
    }

    public void addCard(){
        CardModel p = new CardModel(SteakType.BEEF, "Rare", 5400, 18, 51, false);

        adapter.getPresets().add(p);

        adapter.notifyItemInserted(adapter.getItemCount());
    }

    public void removeCard(int position){
        adapter.getPresets().remove(position);

        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
