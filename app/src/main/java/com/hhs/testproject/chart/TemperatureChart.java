package com.hhs.testproject.chart;

import android.content.Context;
import android.graphics.Color;

import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hhs.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class TemperatureChart extends LineChart {
    private Context context;
    private LineChart chart;

    public TemperatureChart(Context context, LineChart chart){
        super(context);
        this.context = context;
        this.chart = chart;
        styleChart();
    }

    public void styleChart(){
        TemperatureChartMarker elevationMarker = new TemperatureChartMarker(context);
        chart.setMarker(elevationMarker);

        LineDataSet lineDataSet = styleDataSet();
        LineData lineData = new LineData(lineDataSet);

        chart.getDescription().setTextSize(16);
        chart.getDescription().setEnabled(false);
        chart.animateY(500);
        chart.setData(lineData);
        chart.setTouchEnabled(true);
        chart.setDoubleTapToZoomEnabled(false);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);

        // Setup X Axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(6.0f);
        xAxis.setXOffset(1f);
        xAxis.setLabelCount(35);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(35);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        // Setup Y Axis
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setEnabled(false);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(27);
        yAxis.setGranularity(1f);

        chart.getAxisLeft().setCenterAxisLabels(false);

        chart.highlightValue(2, 0);
        chart.getAxisRight().setEnabled(false);
        chart.invalidate();
    }

    private LineDataSet styleDataSet(){
        List<Entry> lineEntries = initDataSet();
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Temperature");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setLineWidth(3);
        lineDataSet.setDrawValues(false);
        lineDataSet.setColor(context.getResources().getColor(R.color.color_primary));
        lineDataSet.setCircleRadius(18);
        lineDataSet.setCircleHoleRadius(6);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleColor(context.getResources().getColor(R.color.color_primary_7));
        lineDataSet.setCircleHoleColor(context.getResources().getColor(R.color.color_primary));
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighLightColor(Color.CYAN);
        lineDataSet.setValueTextSize(20);
        lineDataSet.setValueTextColor(Color.DKGRAY);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.bg_line_data, null));

        return lineDataSet;
    }

    private List<Entry> initDataSet() {
        List<Entry> lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(0, 10));
        lineEntries.add(new Entry(1, 10));
        lineEntries.add(new Entry(2, 14));
        lineEntries.add(new Entry(3, 13));
        lineEntries.add(new Entry(4, 14));
        lineEntries.add(new Entry(5, 15));

        lineEntries.add(new Entry(6, 17));
        lineEntries.add(new Entry(7, 17));
        lineEntries.add(new Entry(8, 19));
        lineEntries.add(new Entry(9, 18));
        lineEntries.add(new Entry(10, 17));

        lineEntries.add(new Entry(11, 20));
        lineEntries.add(new Entry(12, 20));
        lineEntries.add(new Entry(13, 23));
        lineEntries.add(new Entry(14, 23));
        lineEntries.add(new Entry(15, 20));

        lineEntries.add(new Entry(16, 19));
        lineEntries.add(new Entry(17, 19));
        lineEntries.add(new Entry(18, 18));
        lineEntries.add(new Entry(19, 17));
        lineEntries.add(new Entry(20, 18));
        lineEntries.add(new Entry(35, 18));

        return lineEntries;
    }
}
