package com.rugovit.qhash.workflow.chart;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;
import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.base_classes.view_model.BaseViewModel;

import java.util.ArrayList;
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
    public final MutableLiveData<List<CandleEntry>> entries = new MutableLiveData<>();
    public Date from = null;
    public Date to = null;
    public TimeStep timeStep = null;
    //////////////////////////////////////////////////////
    private Observable<Resource<List<Candle>>> observable;
    private ChartRepository chartRepository;

    public ChartViewModel(@NonNull ChartRepository chartRepository,@NonNull Application application) {
        super(application);
        this.chartRepository = chartRepository;
         Date from =new Date();
         from.setTime(System.currentTimeMillis());
         Date to=new Date();
         to.setTime(from.getTime()+24*60*60*100);
         setObservers(from,to);
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
        if (timeStep == null) {
            timeStep = TimeStep.REAL_TIME;
        }
        setObservers(timeStep, from, to);
    }

    public void setObservers(@NonNull TimeStep timeStep, @NonNull Date from, @NonNull Date to) {
        this.from = from;
        this.to = to;
        this.timeStep = timeStep;
        observable = chartRepository.getCandelListObserver(timeStep, from, to);
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
        ArrayList<CandleEntry> temList=new  ArrayList<>();
        for (Candle candle : candlesResource.getData()) {
            CandleEntry entry=convertToCandleEntry(candle);
            temList.add(entry);
        }
        entries.setValue(temList);
    }

    protected <T> void onError(@NonNull Resource<T> resorces) {
        super.onError(resorces);
        Log.e(TAG, resorces.getMessage());
    }
    private CandleEntry convertToCandleEntry(Candle candle){
        //TODO pogledati kako prikazati  vemensku liniju u oisnosti o  što prikazuje  minute, sekunde sate itd
        CandleEntry entry=new CandleEntry(candle.getDate().getTime()/1000,
        candle.getHighPrice().floatValue(),
        candle.getLowPrice().floatValue(),
        candle.getOpenPrice().floatValue(),
        candle.getClosePrice().floatValue());
        return entry;
    }
}
