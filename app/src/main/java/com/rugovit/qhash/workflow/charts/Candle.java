package com.rugovit.qhash.workflow.charts;

import java.math.BigDecimal;

/**
 * Created by rugovit on 12/13/2017.
 */

public class Candle {
    public String time;
    public BigDecimal highPrice;
    public BigDecimal lowPrice;
    public BigDecimal openPrice;
    public BigDecimal volumeFrom;
    public BigDecimal volumeTo;
    public BigDecimal closePrice;
    public BigDecimal avragePrice;
/////////////////////////////////////////////////////////////////////////
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getVolumeFrom() {
        return volumeFrom;
    }

    public void setVolumeFrom(BigDecimal volumeFrom) {
        this.volumeFrom = volumeFrom;
    }

    public BigDecimal getVolumeTo() {
        return volumeTo;
    }

    public void setVolumeTo(BigDecimal volumeTo) {
        this.volumeTo = volumeTo;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getAvragePrice() {
        return avragePrice;
    }

    public void setAvragePrice(BigDecimal avragePrice) {
        this.avragePrice = avragePrice;
    }
}
