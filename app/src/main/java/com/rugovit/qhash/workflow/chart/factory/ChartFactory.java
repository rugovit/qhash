package com.rugovit.qhash.workflow.chart.factory;

import android.content.Context;
import com.rugovit.qhash.workflow.chart.Candle;
import java.util.List;

/**
 * Created by rugovit on 12/26/2017.
 */

public class ChartFactory {
    public static Chart getChart(Context context,List<Candle> candleList,ChartType chartType){
        ChartMPAndroidChart chart=new ChartMPAndroidChart(context,chartType);
        chart.setCandles(candleList);
        return chart;
    }

}
