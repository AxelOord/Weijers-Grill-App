package com.hhs.testproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.mikephil.charting.charts.LineChart;
import com.hhs.testproject.MainApplication;
import com.hhs.testproject.chart.TemperatureChart;
import com.hhs.testproject.databinding.FragmentSecondBinding;
import com.hhs.testproject.models.CardModel;
import com.hhs.testproject.models.PresetsModel;

import java.util.Objects;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private PresetsModel presetsModel;
    private Context context;
    private TemperatureChart chart;
    private CardModel card;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = (MainApplication) requireActivity().getApplication();

        // Get PresetModel from Dependency Manager
        presetsModel = ((MainApplication) requireActivity().getApplication()).getPresetsModel();

        // Get Data of current card from fragment manager and set title
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        manager.setFragmentResultListener("data", getViewLifecycleOwner(), (requestKey, bundle)-> {
            card = presetsModel.getCardByPosition(bundle.getInt("position"));
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(card.getTitle());
        });

        // Bind fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        chart = new TemperatureChart(context, binding.chart);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Go to previous fragment on backstack also triggers animation
//                requireActivity().getSupportFragmentManager()
//                        .popBackStack();
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}