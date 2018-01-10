package com.rugovit.qhash.workflow.chart.factory.IkvStockChart;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;

import com.androidplot.xy.CandlestickSeries;
import com.rugovit.qhash.R;
import com.rugovit.qhash.utils.DisplayUtils;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.Chart;
import com.rugovit.qhash.workflow.chart.factory.ChartType;
import com.wordplat.ikvstockchart.InteractiveKLineLayout;
import com.wordplat.ikvstockchart.InteractiveKLineView;
import com.wordplat.ikvstockchart.KLineHandler;
import com.wordplat.ikvstockchart.drawing.HighlightDrawing;
import com.wordplat.ikvstockchart.drawing.KDJDrawing;
import com.wordplat.ikvstockchart.drawing.MACDDrawing;
import com.wordplat.ikvstockchart.drawing.RSIDrawing;
import com.wordplat.ikvstockchart.drawing.StockIndexYLabelDrawing;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;
import com.wordplat.ikvstockchart.entry.SizeColor;
import com.wordplat.ikvstockchart.entry.StockKDJIndex;
import com.wordplat.ikvstockchart.entry.StockMACDIndex;
import com.wordplat.ikvstockchart.entry.StockRSIIndex;
import com.wordplat.ikvstockchart.marker.XAxisTextMarkerView;
import com.wordplat.ikvstockchart.marker.YAxisTextMarkerView;
import com.wordplat.ikvstockchart.render.KLineRender;

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
    private KLineRender kLineRender;
    public IkvStockChart(@NonNull Context context, @NonNull ChartType chartType, @NonNull TimeStep timeStep) {
        this.chartType=chartType;
        this.timeStep=timeStep;
        this.context=context;
        chartView= (InteractiveKLineView) ((Activity)context).getLayoutInflater().inflate(R.layout.ikv_chart,null);
        chartView.setEnableLeftRefresh(false);
        chartView.setEnableLeftRefresh(false);
        kLineRender = (KLineRender) chartView.getRender();
        final int paddingTop = DisplayUtils.dpTopx(context, 10);
        final int stockMarkerViewHeight = DisplayUtils.dpTopx(context, 15);

        // MACD
        HighlightDrawing macdHighlightDrawing = new HighlightDrawing();
        macdHighlightDrawing.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));

        StockMACDIndex macdIndex = new StockMACDIndex();
        macdIndex.addDrawing(new MACDDrawing());
        macdIndex.addDrawing(new StockIndexYLabelDrawing());
        macdIndex.addDrawing(macdHighlightDrawing);
        macdIndex.setPaddingTop(paddingTop);
        kLineRender.addStockIndex(macdIndex);

        // RSI
        HighlightDrawing rsiHighlightDrawing = new HighlightDrawing();
        rsiHighlightDrawing.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));

        StockRSIIndex rsiIndex = new StockRSIIndex();
        rsiIndex.addDrawing(new RSIDrawing());
        rsiIndex.addDrawing(new StockIndexYLabelDrawing());
        rsiIndex.addDrawing(rsiHighlightDrawing);
        rsiIndex.setPaddingTop(paddingTop);
        kLineRender.addStockIndex(rsiIndex);

        // KDJ
        HighlightDrawing kdjHighlightDrawing = new HighlightDrawing();
        kdjHighlightDrawing.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));

        StockKDJIndex kdjIndex = new StockKDJIndex();
        kdjIndex.addDrawing(new KDJDrawing());
        kdjIndex.addDrawing(new StockIndexYLabelDrawing());
        kdjIndex.addDrawing(kdjHighlightDrawing);
        kdjIndex.setPaddingTop(paddingTop);
        kLineRender.addStockIndex(kdjIndex);

        kLineRender.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));
        kLineRender.addMarkerView(new XAxisTextMarkerView(stockMarkerViewHeight));
    }


    /////////////////////////////////////CHART INTERFACE///////////////////////////////////////////
    @Override
    public void setCandles(List<Candle> candleList, @NonNull TimeStep timeStep) {
        EntrySet entrySet=convertCandlesToCandlestickSeries( candleList);
        entrySet.computeStockIndex();
        chartView.setEntrySet(entrySet);
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
