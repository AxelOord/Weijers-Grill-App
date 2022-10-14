package com.hhs.testproject.chart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.hhs.testproject.R;

public class TemperatureChartMarker extends MarkerView {

    private final Context context;
    private final TextView chartTemp;

    public TemperatureChartMarker(Context context) {
        // The super will take care of displaying the layout
        super(context, R.layout.layout_dot);
        this.context = context;
        chartTemp = findViewById(R.id.chart_temp);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        // Set the entry-value as the display text
        chartTemp.setText(context.getString(R.string.temperature, (int) e.getY()));
    }

    @Override
    public MPPointF getOffset() {
        // Center the ChartMarker on the Y Axis line
        return new MPPointF(-(getWidth() / 2f), -(getHeight() / 2f));
    }
}
