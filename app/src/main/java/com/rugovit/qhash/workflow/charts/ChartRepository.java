package com.rugovit.qhash.workflow.charts;

import android.support.annotation.NonNull;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.base_classes.model.BaseRepository;
import com.rugovit.qhash.workflow.login.UserRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by rugovit on 12/13/2017.
 */

public class ChartRepository extends BaseRepository {
    private volatile static ChartRepository INSTANCE = null;
    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link UserRepository} instance
     */
    public static ChartRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ChartRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ChartRepository();
                }
            }
        }
        return INSTANCE;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Observable<Resource<List<Candle>>> getCandelListObserver(@NonNull CandleTime candleTime,@NonNull Date from,@NonNull Date to){

        Observable<Resource<List<Candle>>>  candlesObservable = Observable.create(emitter -> {


        });
        return candlesObservable;
    }
}
