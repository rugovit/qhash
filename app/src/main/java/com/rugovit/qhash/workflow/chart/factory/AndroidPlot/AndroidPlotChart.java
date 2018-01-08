package com.rugovit.qhash.workflow.chart.factory.AndroidPlot;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.annotation.NonNull;
import android.view.View;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CandlestickFormatter;
import com.androidplot.xy.CandlestickMaker;
import com.androidplot.xy.CandlestickSeries;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.data.CandleEntry;
import com.rugovit.qhash.R;
import com.rugovit.qhash.utils.TimeUtilsHelper;
import com.rugovit.qhash.workflow.chart.Candle;
import com.rugovit.qhash.workflow.chart.TimeStep;
import com.rugovit.qhash.workflow.chart.factory.Chart;
import com.rugovit.qhash.workflow.chart.factory.ChartType;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rugovit on 12/29/2017.
 */

public class AndroidPlotChart implements Chart {

    private ChartType chartType;
    private XYPlot chartView;
    private List<Candle> candleList;
    private TimeStep timeStep;
    private Context context;
    public AndroidPlotChart(@NonNull Context context, @NonNull ChartType chartType, @NonNull TimeStep timeStep) {
        this.chartType=chartType;
        this.timeStep=timeStep;
        this.context=context;
        chartView= (XYPlot) ((Activity)context).getLayoutInflater().inflate(R.layout.androidplot_graph,null);
        /*switch (chartType){
            case CANDLE_STICK_CHART:
                break;
            case BAR_CHART:

                break;
            case LINE_CHART:

                break;
            case SCATTER_CHART:

                break;
        }*/
    }

    /////////////////////////////////////CHART INTERFACE///////////////////////////////////////////

    @Override
    public void setCandles(List<Candle> candleList, @NonNull TimeStep timeStep) {
        this.candleList=candleList;
        this.timeStep=timeStep;
        switch (chartType){
            case CANDLE_STICK_CHART:
                createCandleStickView();
                break;
            case BAR_CHART:
                createBarChartView();
                break;
            case LINE_CHART:
                createLineCharttView();
                break;
            case SCATTER_CHART:
                createScatterCharttView();
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
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////DIFFRENT CHART SUPPORT///////////////////////////////////////////////
    //////////////CANDLE STICK
    private void createCandleStickView(){
        // draw a simple line plot of the close vals:
        LineAndPointFormatter lpf = new LineAndPointFormatter(Color.BLACK, Color.BLACK, null, null);
        lpf.getLinePaint().setPathEffect(
                new DashPathEffect(
                        new float[] {PixelUtils.dpToPix(5), PixelUtils.dpToPix(5)}, 0));
        lpf.setInterpolationParams(
                new CatmullRomInterpolator.Params(20, CatmullRomInterpolator.Type.Centripetal));
        CandlestickSeries candlestickSeries= convertCandlesToCandlestickSeries(candleList);
        chartView.addSeries(candlestickSeries.getCloseSeries(), lpf);
        CandlestickFormatter formatter = new CandlestickFormatter(context, R.xml.candlestick_formatter);
        // draw candlestick bodies as triangles instead of squares:
        // triangles will point up for items that closed higher than they opened
        // and down for those that closed lower:
        formatter.setBodyStyle(CandlestickFormatter.BodyStyle.SQUARE);

        // add the candlestick series data to the plot:
        CandlestickMaker.make(chartView, formatter, candlestickSeries);
        chartView.setDomainBoundaries(candleList.get(0).getDate().getTime(),candleList.get(candleList.size()-1).getDate().getTime(), BoundaryMode.FIXED);
        chartView.setDomainStep(StepMode.INCREMENT_BY_VAL, TimeUtilsHelper.getTimeStepMilisecunds( timeStep) );
        PanZoom.attach(chartView, PanZoom.Pan.BOTH, PanZoom.Zoom.STRETCH_BOTH, PanZoom.ZoomLimit.MIN_TICKS);
        chartView.setUserDomainOrigin(0);
        chartView.setUserRangeOrigin(0);
        formatGraphXAxis(chartView,timeStep);
    }
    //////////////BAR_CHART
    private void createBarChartView( ){

    }

    //////////////LINE_CHART
    private void createLineCharttView( ){

    }

    //////////////SCATTER_CHART
    private void createScatterCharttView( ){

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////WORKER METHODS////////////////////////////////////////////////////
    private CandlestickSeries convertCandlesToCandlestickSeries(@NonNull  List<Candle> candles) {
       ArrayList <CandlestickSeries.Item> temList=new  ArrayList<>();
        int i=0;
        for (Candle candle : candles) {
            CandlestickSeries.Item item=convertToCandleItem(candle);
            temList.add(item);
            i++;
        }
        return  new CandlestickSeries(temList);
    }
    private CandlestickSeries.Item convertToCandleItem(Candle candle){
        return  new CandlestickSeries.Item(candle.getLowPrice().floatValue(), candle.getHighPrice().floatValue(),
                candle.getOpenPrice().floatValue(),candle.getClosePrice().floatValue());
    }
    private void formatGraphXAxis(XYPlot plot,TimeStep step){
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new TimeFormaterXAxis(context,step));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}
