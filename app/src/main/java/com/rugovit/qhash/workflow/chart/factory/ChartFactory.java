package com.rugovit.qhash.workflow.chart.factory;

import android.content.Context;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.AndroidPlot.AndroidPlotChart;
import com.rugovit.qhash.workflow.chart.factory.ChartMPAndroid.ChartMPAndroidChart;
import com.rugovit.qhash.workflow.chart.factory.IkvStockChart.IkvStockChart;

import java.util.List;

/**
 * Created by rugovit on 12/26/2017.
 */

public class ChartFactory {
    public static Chart getChart(Context context,List<Candle> candleList,ChartType chartType,TimeStep timeStep){
        IkvStockChart chart=new IkvStockChart(context,chartType,timeStep);
        chart.setCandles(candleList,timeStep);
       /* ChartMPAndroidChart chart=new ChartMPAndroidChart(context,chartType,timeStep);
        chart.setCandles(candleList,timeStep);*/
        /*AndroidPlotChart chart=new AndroidPlotChart(context,chartType,timeStep);
        chart.setCandles(candleList,timeStep);*/
        return chart;
    }

}
