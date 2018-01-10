package com.rugovit.qhash.workflow.chart;

import android.support.annotation.NonNull;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.base_classes.model.BaseRepository;
import com.rugovit.qhash.utils.TimeUtilsHelper;
import com.rugovit.qhash.workflow.login.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public Observable<Resource<List<Candle>>> getCandelListObserver(@NonNull TimeStep timeStep, @NonNull Date from, @NonNull Date to){

        Observable<Resource<List<Candle>>>  candlesObservable = Observable.create(emitter -> {
            emitter.onNext(getMockData());
            emitter.onComplete();

        });
        return candlesObservable;
    }
    private Resource<List<Candle>> getMockData( ){
        ArrayList<Candle> list=new ArrayList<>();
        Date currentDate=new Date();
        int basePrice=100;
        int randomBase=20;
        currentDate.setTime(System.currentTimeMillis());
        Random randomGenerator = new Random();
         for(long i=0;i<1050;i++){
             Candle candle=new Candle();
             Date tempDate=new Date();
             tempDate.setTime(currentDate.getTime()+ TimeUtilsHelper.DAY_MILISECUNDS*i);
             candle.setDate(tempDate);
             candle.setOpenPrice(new BigDecimal(getRandomInteger(basePrice-randomBase,basePrice+randomBase,randomGenerator)));
             candle.setClosePrice(new BigDecimal(getRandomInteger(basePrice-randomBase,basePrice+randomBase,randomGenerator)));

             if(candle.getOpenPrice().intValue()>candle.getClosePrice().intValue()){
                 candle.setHighPrice(new BigDecimal(candle.getOpenPrice().intValue()+randomGenerator.nextInt(randomBase)));
                 candle.setLowPrice(new BigDecimal(candle.getClosePrice().intValue()-randomGenerator.nextInt(randomBase)));
             }
             {
                 candle.setHighPrice(new BigDecimal(candle.getClosePrice().intValue()+randomGenerator.nextInt(randomBase)));
                 candle.setLowPrice(new BigDecimal(candle.getOpenPrice().intValue()-randomGenerator.nextInt(randomBase)));
             }
             candle.setVolumeFrom(new BigDecimal(0));
             candle.setVolumeTo(new BigDecimal(0));
             candle.setVolume(new BigDecimal(getRandomInteger(basePrice-randomBase,basePrice+randomBase,randomGenerator)));
             candle.setAvragePrice(new BigDecimal(0));
             list.add(candle);

         }
        return  Resource.success(list);
    }

    private  int getRandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);
        return randomNumber;
    }

}
