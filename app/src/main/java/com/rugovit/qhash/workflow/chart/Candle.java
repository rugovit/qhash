package com.rugovit.qhash.workflow.chart;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rugovit on 12/13/2017.
 */

public class Candle {
    @NonNull
    public Date date;
    @NonNull
    public BigDecimal highPrice;
    @NonNull
    public BigDecimal lowPrice;
    @NonNull
    public BigDecimal openPrice;
    @NonNull
    public BigDecimal volumeFrom;
    @NonNull
    public BigDecimal volumeTo;
    @NonNull
    public BigDecimal closePrice;
    @NonNull
    public BigDecimal avragePrice;
/////////////////////////////////////////////////////////////////////////
    @NonNull
    public Date getDate() {
        return date;
    }
    public void setDate(@NonNull Date time) {
        this.date = time;
    }
    @NonNull
    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(@NonNull BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    @NonNull
    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(@NonNull BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    @NonNull
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(@NonNull BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    @NonNull
    public BigDecimal getVolumeFrom() {
        return volumeFrom;
    }

    public void setVolumeFrom(@NonNull BigDecimal volumeFrom) {
        this.volumeFrom = volumeFrom;
    }

    @NonNull
    public BigDecimal getVolumeTo() {
        return volumeTo;
    }

    public void setVolumeTo(@NonNull BigDecimal volumeTo) {
        this.volumeTo = volumeTo;
    }

    @NonNull
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(@NonNull BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    @NonNull
    public BigDecimal getAvragePrice() {
        return avragePrice;
    }

    public void setAvragePrice(@NonNull BigDecimal avragePrice) {
        this.avragePrice = avragePrice;
    }
}
