package com.rugovit.qhash.workflow.chart.factory.IkvStockChart;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.rugovit.qhash.R;
import com.rugovit.qhash.utils.DisplayUtils;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.Chart;
import com.rugovit.qhash.workflow.chart.factory.ChartType;
import com.wordplat.ikvstockchart.InteractiveKLineView;
import com.wordplat.ikvstockchart.entry.Entry;
import com.wordplat.ikvstockchart.entry.EntrySet;
import com.wordplat.ikvstockchart.entry.StockBOLLIndex;
import com.wordplat.ikvstockchart.marker.XAxisTextMarkerView;
import com.wordplat.ikvstockchart.marker.YAxisTextMarkerView;
import com.wordplat.ikvstockchart.render.IndependedRender;
import com.wordplat.ikvstockchart.render.KLineRender;

import java.util.Date;
import java.util.List;

/**
 * Created by rugovit on 1/8/2018.
 */

public class IkvStockChart implements Chart {

    private ChartType chartType;
    private LinearLayout chartView;
    private InteractiveKLineView interactiveKLineView;
    private List<Candle> candleList;
    private StockBOLLIndex bollIndex;
    private TimeStep timeStep;
    private Context context;
    private KLineRender kLineRender;
    public IkvStockChart(@NonNull Context context, @NonNull ChartType chartType, @NonNull TimeStep timeStep) {
        this.chartType=chartType;
        this.timeStep=timeStep;
        this.context=context;
        chartView= (LinearLayout) ((Activity)context).getLayoutInflater().inflate(R.layout.ikv_chart,null);
        interactiveKLineView=chartView.findViewById(R.id.kLineLayout);
        interactiveKLineView.setEnableLeftRefresh(false);
        interactiveKLineView.setEnableLeftRefresh(false);



        kLineRender = (KLineRender) interactiveKLineView.getRender();


        final int paddingTop = DisplayUtils.dpTopx(context, 10);
        final int stockMarkerViewHeight = DisplayUtils.dpTopx(context, 15);

        // 成交量
        /*StockKLineVolumeIndex kLineVolumeIndex = new StockKLineVolumeIndex(DisplayUtils.dpTopx(context, 100));
         kLineVolumeIndex.addDrawing(new KLineVolumeDrawing());
         kLineVolumeIndex.addDrawing(new KLineVolumeHighlightDrawing());
        kLineRender.addStockIndex(kLineVolumeIndex);*/
        // MACD
      /*  HighlightDrawing macdHighlightDrawing = new HighlightDrawing();
        macdHighlightDrawing.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));

        StockMACDIndex macdIndex = new StockMACDIndex();
        macdIndex.addDrawing(new MACDDrawing());
        macdIndex.addDrawing(new StockIndexYLabelDrawing());
        macdIndex.addDrawing(macdHighlightDrawing);
        macdIndex.setPaddingTop(paddingTop);
        kLineRender.addStockIndex(macdIndex);*/
        // BOLL
        /*HighlightDrawing bollHighlightDrawing = new HighlightDrawing();
        bollHighlightDrawing.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));

        bollIndex = new StockBOLLIndex(DisplayUtils.dpTopx(context, 100));
        bollIndex.addDrawing(new BOLLDrawing());
        bollIndex.addDrawing(new StockIndexYLabelDrawing());
        bollIndex.addDrawing(bollHighlightDrawing);
        bollIndex.setPaddingTop(DisplayUtils.dpTopx(context, 20));
        kLineRender.addStockIndex(bollIndex);*/
     /*   // RSI
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
        kLineRender.addStockIndex(kdjIndex);*/

        kLineRender.addMarkerView(new YAxisTextMarkerView(stockMarkerViewHeight));
        kLineRender.addMarkerView(new XAxisTextMarkerView(stockMarkerViewHeight));
    }


    /////////////////////////////////////CHART INTERFACE///////////////////////////////////////////
    @Override
    public void setCandles(List<Candle> candleList, @NonNull TimeStep timeStep) {
/*
        String kLineData = "";
        try {
            InputStream in = context.getResources().openRawResource(R.raw.kline1);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            kLineData = new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        EntrySet entrySet = StockDataTest.parseKLineData(kLineData);*/
        EntrySet entrySet=convertCandlesToCandlestickSeries( candleList);
        entrySet.computeStockIndex();
        interactiveKLineView.setEntrySet(entrySet);
        interactiveKLineView.addIndependedRender(new IndependedRender(context,entrySet));


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
