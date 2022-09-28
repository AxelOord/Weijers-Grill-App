package com.hhs.testproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hhs.testproject.databinding.FragmentSecondBinding;
import com.hhs.testproject.models.PresetsModel;

import java.util.Objects;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private PresetsModel presetsModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get Data of current card from fragment manager and set title
        FragmentManager manager = requireActivity().getSupportFragmentManager();
        manager.setFragmentResultListener("data", getViewLifecycleOwner(), (requestKey, bundle)-> {
            String result = bundle.getString("title");
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(result);
        });

        // Bind fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to previous fragment on backstack also triggers animation
                requireActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}