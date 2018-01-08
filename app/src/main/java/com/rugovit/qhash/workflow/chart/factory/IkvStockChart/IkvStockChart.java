package com.rugovit.qhash.workflow.chart.factory.IkvStockChart;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;

import com.androidplot.xy.CandlestickSeries;
import com.rugovit.qhash.R;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.Chart;
import com.rugovit.qhash.workflow.chart.factory.ChartType;
import com.wordplat.ikvstockchart.InteractiveKLineLayout;
import com.wordplat.ikvstockchart.InteractiveKLineView;
import com.wordplat.ikvstockchart.KLineHandler;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;

import java.util.Date;
import java.util.List;

/**
 * Created by rugovit on 1/8/2018.
 */

public class IkvStockChart implements Chart {

    private ChartType chartType;
    private InteractiveKLineView chartView;
    private List<Candle> candleList;
    private TimeStep timeStep;
    private Context context;
    public IkvStockChart(@NonNull Context context, @NonNull ChartType chartType, @NonNull TimeStep timeStep) {
        this.chartType=chartType;
        this.timeStep=timeStep;
        this.context=context;
        chartView= (InteractiveKLineView) ((Activity)context).getLayoutInflater().inflate(R.layout.ikv_chart,null);

    }


    /////////////////////////////////////CHART INTERFACE///////////////////////////////////////////
    @Override
    public void setCandles(List<Candle> candleList, @NonNull TimeStep timeStep) {

        chartView.setEntrySet(convertCandlesToCandlestickSeries( candleList));
        chartView.notifyDataSetChanged();
        chartView.setKLineHandler(new KLineHandler() {
            @Override
            public void onLeftRefresh() {
                chartView.refreshComplete();
            }

            @Override
            public void onRightRefresh() {
                chartView.refreshComplete();
            }

            @Override
            public void onSingleTap(MotionEvent e, float x, float y) {

            }

            @Override
            public void onDoubleTap(MotionEvent e, float x, float y) {

            }

            @Override
            public void onHighlight(Entry entry, int entryIndex, float x, float y) {

            }

            @Override
            public void onCancelHighlight() {

            }
        });
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
    public TimeStep getTimeStep() {
        return timeStep;
    }

    @Override
    public void setTimeStep(@NonNull TimeStep timeStep) {

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////WORKER METHODS////////////////////////////////////////////////////
    private EntrySet convertCandlesToCandlestickSeries(@NonNull  List<Candle> candles) {
        EntrySet entrySet = new EntrySet();
        int i=0;
        for (Candle candle : candles) {
            Entry item=convertToCandleEntry(candle);
            entrySet.addEntry(item);
            i++;
        }
        return  entrySet;
    }
    private Entry convertToCandleEntry(Candle candle){
        return  new  Entry(candle.getOpenPrice().floatValue(), candle.getHighPrice().floatValue(),
                candle.getLowPrice().floatValue(),candle.getClosePrice().floatValue(),candle.getVolume().intValue(),getStringDateRepresentation(candle.getDate() , timeStep ));
    }
    private String getStringDateRepresentation(Date date ,TimeStep timeStep ){
        switch (timeStep) {
            case REAL_TIME:
                return getRealtime(date);
            case MINUTE:
                return getMinute(date);
            case HOUR:
                return getHour(date);
            case DAY:
                return getDay(date);
            case WEEK:
                return getWeek(date);
            case MONTH:
                return getMonth(date);
            case YEAR:
                return getYear(date);
            default:
                return "error";
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////PARSE METHODS//////////////////////////////////////////////////
    private String getRealtime(@NonNull Date date) {
        return "";
    }

    private String getMinute(@NonNull Date date) {
        return "";
    }

    private String getHour(@NonNull Date date) {
        return "";
    }

    private String getDay(@NonNull Date date) {

        return  DateUtils.formatDateTime(context, date.getTime(), DateUtils.FORMAT_NO_MONTH_DAY);
    }

    private String getWeek(@NonNull Date date) {
        return "";
    }

    private String getMonth(@NonNull Date date) {
        return "";
    }

    private String getYear(@NonNull Date date) {
        return "";
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
