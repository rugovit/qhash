package com.rugovit.qhash.utils;

import android.support.annotation.NonNull;

import com.rugovit.qhash.workflow.chart.TimeStep;

/**
 * Created by rugovit on 12/27/2017.
 */

public class TimeUtilsHelper {
    public final static int  SECUND_MILISECUNDS=1000;
    public final static int  MINUTE_MILISECUNDS=SECUND_MILISECUNDS*60;
    public final static int  HOUR_MILISECUNDS=60*MINUTE_MILISECUNDS;
    public final static int  DAY_MILISECUNDS=24 *HOUR_MILISECUNDS;
    public final static int  WEEK_MILISECUNDS=7*DAY_MILISECUNDS;
    public static long getTimeStepMilisecunds(@NonNull TimeStep timeStep){
        switch (timeStep) {
            case REAL_TIME:
                return 1;
            case MINUTE:
                return MINUTE_MILISECUNDS;
            case HOUR:
                return HOUR_MILISECUNDS;
            case DAY:
                return DAY_MILISECUNDS;
            case WEEK:
                return WEEK_MILISECUNDS;
            case MONTH:
                return DAY_MILISECUNDS*31;
            case YEAR:
                return DAY_MILISECUNDS*365;
            default:
                return 1;
        }
    }
}
