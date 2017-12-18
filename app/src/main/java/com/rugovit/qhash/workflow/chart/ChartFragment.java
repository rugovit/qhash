package com.rugovit.qhash.workflow.chart;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.rugovit.qhash.R;
import com.rugovit.qhash.base_classes.view.BaseFragment;
import com.rugovit.qhash.databinding.ChartFragmentLayoutBinding;

import java.util.List;

/**
 * Created by rugovit on 12/13/2017.
 */

public class ChartFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       ChartFragmentLayoutBinding binding =ChartFragmentLayoutBinding.inflate(
                inflater, container, false);
        binding.setChart(new ChartViewModel(ChartRepository.getInstance()));
        View layout=binding.getRoot();
        CandleStickChart candleStickChart= layout.findViewById(R.id.chart);
        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = candleStickChart.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = candleStickChart.getAxisRight();
        rightAxis.setEnabled(false);
//        rightAxis.setStartAtZero(false);



        candleStickChart.getLegend().setEnabled(false);
        binding.getChart().entries.observe(this, candleEntries -> {
            CandleDataSet set = new CandleDataSet(candleEntries, "Data Set");
            set.setDrawIcons(false);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            //  set1.setColor(Color.rgb(80, 80, 80));
            set.setShadowColor(Color.DKGRAY);
            set.setShadowWidth(0.7f);
            set.setDecreasingColor(Color.RED);
            set.setDecreasingPaintStyle(Paint.Style.FILL);
            set.setIncreasingColor(Color.rgb(122, 242, 84));
            set.setIncreasingPaintStyle(Paint.Style.STROKE);
            set.setNeutralColor(Color.BLUE);
            //set1.setHighlightLineWidth(1f);
            CandleData data = new CandleData(set);
            candleStickChart.setMaxVisibleValueCount(50);

            candleStickChart.setData(data);
            candleStickChart.invalidate();
        });

        return  layout;
    }
}
