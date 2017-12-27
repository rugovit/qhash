package com.rugovit.qhash.workflow.chart.factory;

import android.view.View;

import com.rugovit.qhash.workflow.chart.Candle;

import java.util.List;

/**
 * Created by rugovit on 12/26/2017.
 */

public interface Chart {
    public void  setCandles(List<Candle> candleList);
    public List<Candle> getCandles();
    public View getChartView();
}
