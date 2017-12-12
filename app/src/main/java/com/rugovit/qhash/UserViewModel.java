package com.rugovit.qhash;

import android.databinding.ObservableField;
import android.util.Log;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.login.User;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.rugovit.qhash.base_classes.data.ResourceStatus.ERROR;
import static com.rugovit.qhash.base_classes.data.ResourceStatus.SUCESS;

/**
 * Created by rugođvit on 11/28/2017.
 */

public class UserViewModel extends BaseViewModel {

    public static final String TAG = "UserViewModel";
    //////////////////////////////////////////////////////
    public final ObservableField<User> user = new ObservableField<>();
    /////////////////////////////////////////////////////
    private Observable<Resource<User>>  observable;
    public UserViewModel(UserRepository userRepository, Scheduler scheduler) {
        observable = userRepository.getUserObserver();
        observable.observeOn(scheduler)
                    .subscribeOn(scheduler);
        setObservable();

    }
    public UserViewModel(UserRepository userRepository) {
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
    private void setUser(Resource<User> userResource){
        user.set(userResource.getData());
    }
    private void onError(Resource<User> userResource){
        Log.e(TAG, userResource.getMessage());
    }

}
