package com.rugovit.qhash.workflow.chart.factory;

import android.support.annotation.NonNull;
import android.view.View;

import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;

import java.util.List;

/**
 * Created by rugovit on 12/26/2017.
 */

public interface Chart {
     void  setCandles(List<Candle> candleList,@NonNull TimeStep timeStep);
     List<Candle> getCandles();
     View getChartView();
     TimeStep getTimeStep();
     void setTimeStep(@NonNull TimeStep timeStep);
}
