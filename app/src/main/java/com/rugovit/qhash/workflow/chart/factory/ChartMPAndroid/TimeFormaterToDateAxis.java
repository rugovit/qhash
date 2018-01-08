package com.rugovit.qhash.workflow.chart.factory.ChartMPAndroid;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.rugovit.qhash.workflow.chart.TimeStep;

/**
 * Created by rugovit on 12/22/2017.
 */

public class TimeFormaterToDateAxis implements IAxisValueFormatter {
    TimeStep timeStep;
    Context context;
    public TimeFormaterToDateAxis(@NonNull TimeStep timeStep, @NonNull Context context) {
        this.timeStep = timeStep;
        this.context=context;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis)  {
        switch (timeStep) {
            case REAL_TIME:
                return getRealtime(value,axis);
            case MINUTE:
                return getMinute(value,axis);
            case HOUR:
                return getHour(value,axis);
            case DAY:
                return getDay(value,axis);
            case WEEK:
                return getWeek(value,axis);
            case MONTH:
                return getMonth(value,axis);
            case YEAR:
                return getYear(value,axis);
            default:
                return String.valueOf(value);

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private String getRealtime(@NonNull float value, AxisBase axis) {
       return "";
    }

    private String getMinute(@NonNull float value, AxisBase axis) {
        return "";
    }

    private String getHour(@NonNull float value, AxisBase axis) {
        return "";
    }

    private String getDay(@NonNull float value, AxisBase axis) {
      return DateUtils.formatDateTime(context, (long)value, DateUtils.FORMAT_NO_YEAR);
    }

    private String getWeek(@NonNull float value, AxisBase axis) {
        return "";
    }

    private String getMonth(@NonNull float value, AxisBase axis) {
        return "";
    }

    private String getYear(@NonNull float value, AxisBase axis) {
        return "";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public TimeStep getTimeStep() {
        return timeStep;
    }

    public void setTimeStep(TimeStep timeStep) {
        this.timeStep = timeStep;
    }

}
