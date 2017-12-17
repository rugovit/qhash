package com.rugovit.qhash.base_classes.view_model;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.rugovit.qhash.base_classes.data.Resource;

/**
 * Created by rugovit on 12/4/2017.
 */

public class BaseViewModel extends ViewModel {
     protected  <T> void  onError(@NonNull Resource<T> resorces) {

    }

}
