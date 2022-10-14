package com.hhs.testproject.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.hhs.testproject.R;
import com.hhs.testproject.fragments.SecondFragment;
import com.hhs.testproject.models.CardModel;

import java.util.ArrayList;

public class PresetCardAdapter extends RecyclerView.Adapter<PresetCardAdapter.PresetsViewHolder> {

    private final Context context;
    public final ArrayList<CardModel> presets = new ArrayList<>();

    public PresetCardAdapter(Context context) {
        this.context = context;
    }


    public static class PresetsViewHolder extends RecyclerView.ViewHolder{

        public TextView steak, rarity, time, currentTemp, targetTemp;
        public ImageView notification;

        public PresetsViewHolder(@NonNull View itemView) {
            super(itemView);

            steak = itemView.findViewById(R.id.steak);
            rarity = itemView.findViewById(R.id.rarity);
            time = itemView.findViewById(R.id.time);
            currentTemp = itemView.findViewById(R.id.current_temp);
            targetTemp = itemView.findViewById(R.id.target_temp).findViewById(R.id.temp);
            notification = itemView.findViewById(R.id.notification_icon);
        }
    }

    @NonNull
    @Override
    public PresetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_preset, parent, false);
        return new PresetsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PresetsViewHolder holder, int position) {
        CardModel presetCardModel = presets.get(position);
        populateCard(holder, presetCardModel);

        // OnClickListener of the notification button on current card
        holder.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presetCardModel.isNotification())
                    setNotification(R.color.text_secondary, holder, presetCardModel, view);
                else
                    setNotification(R.color.color_primary, holder, presetCardModel, view);
            }
        });

        // OnClickListener of current card
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();

                // Create bundle and add title for the fragment
                Bundle bundle = new Bundle();
                bundle.putInt("position", holder.getBindingAdapterPosition());
                manager.setFragmentResult("data", bundle);

                // Start transaction to next fragment and add current fragment to the backstack
                // When the backstack is popped, the current fragment will be loaded in with the customAnimation
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.slide_in, R.anim.no_animation, 0, R.anim.slide_out)
                    .replace(R.id.nav_host_fragment_content_main, SecondFragment.class, null)
                    .addToBackStack("TAG")
                    .commit();
            }
        });
    }


    private void setNotification(int color, PresetsViewHolder holder, CardModel model, View view){
        holder.notification.setColorFilter(ContextCompat.getColor(context, color));
        model.setNotification(!model.isNotification());

        Snackbar.make(view, model.isNotification() ? "Notification set!" : "Notification unset!", Snackbar.LENGTH_SHORT)
                .setAction("Notification", null).show();
    }

    private void populateCard(PresetsViewHolder holder, CardModel model){
        holder.steak.setText(model.getSteak().getName());
        holder.rarity.setText(model.getRarity());
        holder.time.setText(model.getTime());
        holder.currentTemp.setText(context.getString(R.string.temperature_spaced, model.getCurrentTemp()));
        holder.targetTemp.setText(context.getString(R.string.temperature_spaced, model.getTargetTemp()));

        if(model.isNotification())
            holder.notification.setColorFilter(ContextCompat.getColor(context, R.color.color_primary));
        else
            holder.notification.setColorFilter(ContextCompat.getColor(context, R.color.text_secondary));
    }

    public ArrayList<CardModel> getPresets() {
        return presets;
    }

    @Override
    public int getItemCount() {
        return presets.size();
    }
}
