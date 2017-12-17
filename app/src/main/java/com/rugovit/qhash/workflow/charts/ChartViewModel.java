package com.rugovit.qhash.workflow.charts;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.base_classes.view_model.BaseViewModel;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.rugovit.qhash.base_classes.data.ResourceStatus.ERROR;
import static com.rugovit.qhash.base_classes.data.ResourceStatus.SUCESS;

/**
 * Created by rugovit on 12/13/2017.
 */

public class ChartViewModel extends BaseViewModel{

    public static final String TAG = "ChartViewModel";
    //////////////////////////////////////////////////////
    public final ObservableArrayList<Candle> candles = new ObservableArrayList<>();
    public Date from = null;
    public Date to = null;
    public CandleTime candleTime = null;
    //////////////////////////////////////////////////////
    private Observable<Resource<List<Candle>>> observable;
    private ChartRepository chartRepository;

    public ChartViewModel(@NonNull ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    public void getNext() {
        Date from=new Date(this.to.getTime());
        Date to=new Date(this.to.getTime()+(this.to.getTime()-this.from.getTime()));
        setObservers( from, to);
    }

    public void getPriv() {
        Date from=new Date(this.from.getTime()-(this.to.getTime()-this.from.getTime()));
        Date to=new Date(this.from.getTime());
        setObservers(from, to);
    }

    public void setObservers(@NonNull Date from, @NonNull Date to) {
        if (candleTime == null) {
            candleTime = CandleTime.REAL_TIME;
        }
        setObservers(candleTime, from, to);
    }

    public void setObservers(@NonNull CandleTime candleTime, @NonNull Date from, @NonNull Date to) {
        this.from = from;
        this.to = to;
        this.candleTime = candleTime;
        observable = chartRepository.getCandelListObserver(candleTime, from, to);
        observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
        setObservable();
    }

    private void setObservable() {
        observable.subscribe(candlesResource -> {
            if (candlesResource.getStatus() == SUCESS) {
                setCandles(candlesResource);
            } else if (candlesResource.getStatus() == ERROR) {
                onError(candlesResource);
            }
        });
    }
    private void setCandles(@NonNull Resource<List<Candle>> candlesResource) {
        //TODO pogledati kao napraviti da se updeta realtime graf,   dali uopce  ima mogućnost prikaza dodatnih candela na gotov graf bez da se updeta cijeli, ako ne  onda cu samo realtime updetati cijeli graf
        candles.clear();
        candles.addAll(candlesResource.getData());
    }

    protected <T> void onError(@NonNull Resource<T> resorces) {
        super.onError(resorces);
        Log.e(TAG, resorces.getMessage());
    }
}
