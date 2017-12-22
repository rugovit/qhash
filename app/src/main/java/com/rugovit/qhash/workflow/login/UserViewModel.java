package com.rugovit.qhash.workflow.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rugovit.qhash.base_classes.view_model.BaseViewModel;
import com.rugovit.qhash.base_classes.data.Resource;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.rugovit.qhash.base_classes.data.ResourceStatus.ERROR;
import static com.rugovit.qhash.base_classes.data.ResourceStatus.SUCESS;

/**
 * Created by rugovit on 11/28/2017.
 */

public class UserViewModel extends BaseViewModel {

    public static final String TAG = "UserViewModel";
    //////////////////////////////////////////////////////
    public final ObservableField<User> user = new ObservableField<>();
    /////////////////////////////////////////////////////
    private Observable<Resource<User>>  observable;
    public UserViewModel(@NonNull UserRepository userRepository,@NonNull Scheduler scheduler,@NonNull Application application) {
        super(application);
        observable = userRepository.getUserObserver();
        observable.observeOn(scheduler)
                    .subscribeOn(scheduler);
        setObservable();
    }
    public UserViewModel(@NonNull UserRepository userRepository,@NonNull Application application) {
        super(application);
        observable=userRepository.getUserObserver();
        observable.observeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread());
        setObservable();
    }
    private void setObservable(){
        observable.subscribe(userResource -> {
                    if (userResource.getStatus() == SUCESS) {
                        setUser(userResource);
                    } else if (userResource.getStatus() == ERROR) {
                        onError(userResource);
                    }
                });
    }
    private void setUser(@NonNull Resource<User> userResource){
        user.set(userResource.getData());
    }
    protected <T> void onError(@NonNull Resource<T> resorces) {
        super.onError(resorces);
        Log.e(TAG, resorces.getMessage());
    }

}
