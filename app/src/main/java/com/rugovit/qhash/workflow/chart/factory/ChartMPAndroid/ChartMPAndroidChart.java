package com.rugovit.qhash.workflow.chart.factory.ChartMPAndroid;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.Chart;
import com.rugovit.qhash.workflow.chart.factory.ChartType;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by rugovit on 12/26/2017.
 */

public class ChartMPAndroidChart implements Chart {
    private ChartType chartType;
    private BarLineChartBase chartView;
    private List<Candle> candleList;
    private TimeStep timeStep;
    private Context context;
    public ChartMPAndroidChart(@NonNull Context context,@NonNull ChartType chartType,@NonNull TimeStep timeStep) {
        this.chartType=chartType;
        this.timeStep=timeStep;
        this.context=context;
        switch (chartType){
            case CANDLE_STICK_CHART:
                 createCandleStickView(context);
                break;
            case BAR_CHART:
                 createBarChartView(context);
                break;
            case LINE_CHART:
                 createLineCharttView(context);
                break;
            case SCATTER_CHART:
                 createScatterCharttView(context);
                break;
        }
    }
    /////////////////////////////////////CHART INTERFACE///////////////////////////////////////////
    @Override
    public void setCandles(@NonNull List<Candle> candleList,@NonNull TimeStep timeStep) {
        this.candleList=candleList;
        this.timeStep=timeStep;
        switch (chartType){
            case CANDLE_STICK_CHART:
                CandleDataSet set = new CandleDataSet(convertCandlesToCandelEntrys(candleList), "Data Set");
                setCandleStickDataSetVisuals(set);
                CandleData data= new CandleData(set);
                chartView.setData(data);
                break;
            case BAR_CHART:

                break;
            case LINE_CHART:

                break;
            case SCATTER_CHART:

                break;
        }

    }

    @Override
    public List<Candle> getCandles() {
        return candleList;
    }

    @Override
    public View getChartView() {
        return chartView;
    }
    @Override
    public TimeStep getTimeStep(){
        return timeStep;
    }
    @Override
    public void setTimeStep(@NonNull TimeStep timeStep){
        this.timeStep=timeStep;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////WORKER METHODS////////////////////////////////////////////////////

    private ArrayList<CandleEntry> convertCandlesToCandelEntrys(@NonNull  List<Candle> candles) {
        //TODO pogledati kao napraviti da se updeta realtime graf,   dali uopce  ima mogućnost prikaza dodatnih candela na gotov graf bez da se updeta cijeli, ako ne  onda cu samo realtime updetati cijeli graf
        ArrayList<CandleEntry> temList=new  ArrayList<>();
        int i=0;
        for (Candle candle : candles) {
            CandleEntry entry=convertToCandleEntry(candle,i);
            temList.add(entry);
            i++;
        }
        return  temList;
    }
    private CandleEntry convertToCandleEntry(Candle candle,int i){
        CandleEntry entry=new CandleEntry(i,
                candle.getHighPrice().floatValue(),
                candle.getLowPrice().floatValue(),
                candle.getOpenPrice().floatValue(),
                candle.getClosePrice().floatValue());
        return entry;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////DIFFRENT CHART SUPPORT///////////////////////////////////////////////
    //////////////CANDLE STICK
    private void createCandleStickView(@NonNull Context context){
        chartView=new CandleStickChart(context);
        XAxis xAxis = chartView.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chartView.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(7, false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        YAxis rightAxis = chartView.getAxisRight();
        rightAxis.setEnabled(false);
//      rightAxis.setStartAtZero(false);

        chartView.getLegend().setEnabled(false);
        chartView.setMaxVisibleValueCount(50);
        chartView.invalidate();
    }
    private void setCandleStickDataSetVisuals(CandleDataSet set ){
        set.setDrawIcons(false);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        chartView.setHardwareAccelerationEnabled(true);
        //  set1.setColor(Color.rgb(80, 80, 80));
        set.setShadowColor(Color.DKGRAY);
        set.setShadowWidth(0.7f);
        set.setDecreasingColor(Color.RED);
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(Color.rgb(122, 242, 84));
        set.setIncreasingPaintStyle(Paint.Style.STROKE);
        set.setNeutralColor(Color.BLUE);
      //  XAxis xAxis = chartView.getXAxis();
       // xAxis.setValueFormatter(new TimeFormaterToDateAxis(timeStep,context));
       // set.setBarSpace(0.45f);
        //set1.setHighlightLineWidth(1f);
    }
    //////////////BAR_CHART
    private View createBarChartView(@NonNull Context context){
           return null;
    }
    private void setBarChartDataSetVisuals(CandleDataSet set ) {
    }
    //////////////LINE_CHART
    private View createLineCharttView(@NonNull Context context){
        return null;
    }
    private void setLineChartDataSetVisuals(CandleDataSet set ) {
    }
    //////////////SCATTER_CHART
    private View createScatterCharttView(@NonNull Context context){
        return null;
    }
    private void setScatterChartDataSetVisuals(CandleDataSet set ) {
    }

}
