package com.rugovit.qhash.workflow.chart;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by rugovit on 12/22/2017.
 */

public class TimeFormaterToDate implements IValueFormatter {
    TimeStep timeStep;
    Application application;
    public TimeFormaterToDate(@NonNull TimeStep timeStep,@NonNull Application application) {
        this.timeStep = timeStep;
        this.application=application;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        switch (timeStep) {
            case REAL_TIME:
                return getRealtime(value, entry, dataSetIndex, viewPortHandler);
            case MINUTE:
                return getMinute(value, entry, dataSetIndex, viewPortHandler);
            case HOUR:
                return getHour(value, entry, dataSetIndex, viewPortHandler);
            case DAY:
                return getDay(value, entry, dataSetIndex, viewPortHandler);
            case WEEK:
                return getWeek(value, entry, dataSetIndex, viewPortHandler);
            case MONTH:
                return getMonth(value, entry, dataSetIndex, viewPortHandler);
            case YEAR:
                return getYear(value, entry, dataSetIndex, viewPortHandler);
            default:
                return String.valueOf(value);

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private String getRealtime(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
       return "";
    }

    private String getMinute(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        return "";
    }

    private String getHour(@NonNull float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return "";
    }

    private String getDay(@NonNull float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
      return DateUtils.formatDateTime(application, (long)value*1000, DateUtils.FORMAT_SHOW_DATE);
    }

    private String getWeek(@NonNull float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return "";
    }

    private String getMonth(@NonNull float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return "";
    }

    private String getYear(@NonNull float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
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
