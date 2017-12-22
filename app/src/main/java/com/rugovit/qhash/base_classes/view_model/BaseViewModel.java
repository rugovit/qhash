package com.rugovit.qhash.base_classes.view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rugovit.qhash.base_classes.data.Resource;

/**
 * Created by rugovit on 12/4/2017.
 */

public class BaseViewModel extends AndroidViewModel {
    Application application;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }

    protected  <T> void  onError(@NonNull Resource<T> resorces) {

    }

}
