package com.rugovit.qhash.workflow.chart;


import android.content.Context;
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
import com.rugovit.qhash.R;
import com.rugovit.qhash.base_classes.view.BaseFragment;
import com.rugovit.qhash.databinding.ChartFragmentLayoutBinding;
import com.rugovit.qhash.workflow.chart.factory.ChartFactory;
import com.rugovit.qhash.workflow.chart.factory.ChartType;

/**
 * Created by rugovit on 12/13/2017.
 */

public class ChartFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       ChartFragmentLayoutBinding binding =ChartFragmentLayoutBinding.inflate(
                inflater, container, false);

        binding.setChartViewModel(new ChartViewModel(ChartRepository.getInstance(),this.getActivity().getApplication()));
        binding.getChartViewModel().candleList.observe(this, candles -> binding.chartHolder.addView(ChartFactory.getChart(getActivity(),candles, ChartType.CANDLE_STICK_CHART).getChartView())
        );

        return  binding.getRoot();
    }
}
