package com.hhs.testproject.fragments;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hhs.testproject.MainApplication;
import com.hhs.testproject.R;
import com.hhs.testproject.models.PresetsModel;
import com.hhs.testproject.utils.GridSpacingItemDecoration;
import com.hhs.testproject.utils.SwipeController;
import com.hhs.testproject.utils.SwipeControllerActions;

import java.util.Objects;

public class PresetFragment extends Fragment {

    private PresetsModel presetsModel;

    private RecyclerView recyclerView;
    private View view;

    SwipeController swipeController = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set title of ActionBar
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle("Presets");

        // Get PresetModel from Dependency Manager
        presetsModel = ((MainApplication) requireActivity().getApplication()).getPresetsModel();

        // Inflate RecyclerView and initialize
        view = inflater.inflate(R.layout.fragment_presets, container, false);
        InitRecyclerView();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void InitRecyclerView(){
        recyclerView = view.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(presetsModel.getAdapter());

        swipeController = new SwipeController((MainApplication) requireActivity().getApplication(), new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                presetsModel.removeCard(position);
            }

            @Override
            public void onLeftClicked(int position) {
                presetsModel.changeDataOfCard(position);
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}
