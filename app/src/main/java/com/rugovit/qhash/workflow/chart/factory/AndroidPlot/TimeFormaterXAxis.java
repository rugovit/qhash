package com.rugovit.qhash.workflow.chart.factory.AndroidPlot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.util.Log;

import com.rugovit.qhash.workflow.chart.TimeStep;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ConcurrentModificationException;
import java.util.Date;

/**
 * Created by rugovit on 1/7/2018.
 */

public class TimeFormaterXAxis extends Format {
    private TimeStep timeStep;
    private Context context;
    Date d;
    public TimeFormaterXAxis(Context context,@NonNull TimeStep timeStep) {
        this.context=context;
        this.timeStep=timeStep;
    }

    @Override
    public StringBuffer format(Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        switch (timeStep) {
            case REAL_TIME:
                return getRealtime(o,stringBuffer,fieldPosition);
            case MINUTE:
                return getMinute(o,stringBuffer,fieldPosition);
            case HOUR:
                return getHour(o,stringBuffer,fieldPosition);
            case DAY:
                return getDay(o,stringBuffer,fieldPosition);
            case WEEK:
                return getWeek(o,stringBuffer,fieldPosition);
            case MONTH:
                return getMonth(o,stringBuffer,fieldPosition);
            case YEAR:
                return getYear(o,stringBuffer,fieldPosition);
            default:
                return stringBuffer;
        }
    }

    @Override
    public Object parseObject(String s, @NonNull ParsePosition parsePosition) {
        return null;
    }
    ////////////////////////////////PARSE METHODS//////////////////////////////////////////////////
    private StringBuffer getRealtime(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }

    private StringBuffer getMinute(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }

    private StringBuffer getHour(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }

    private StringBuffer getDay(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        d=new Date();
        d.setTime(((Number) o).longValue());
        Log.d("Time formater date",d.toString());
        Log.d("Time formater time",String.valueOf(((Number) o).longValue()));
        Log.d("Time formater fieldPos",fieldPosition.toString());
        return stringBuffer.append(DateUtils.formatDateTime(context, ((Number) o).longValue(), DateUtils.FORMAT_NO_MONTH_DAY));
    }

    private StringBuffer getWeek(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }

    private StringBuffer getMonth(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }

    private StringBuffer getYear(@NonNull Object o, @NonNull StringBuffer stringBuffer, @NonNull FieldPosition fieldPosition) {
        return stringBuffer;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
}
