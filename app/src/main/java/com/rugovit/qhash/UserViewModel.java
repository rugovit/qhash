package com.rugovit.qhash;

import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.login.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.rugovit.qhash.base_classes.data.ResourceStatus.ERROR;
import static com.rugovit.qhash.base_classes.data.ResourceStatus.SUCESS;

/**
 * Created by rugođvit on 11/28/2017.
 */

public class UserViewModel extends BaseViewModel {

    public static final String TAG="UserViewModel";
    public final ObservableField<User> user = new ObservableField<>();
///////////////////////////////////////////////////

    public UserViewModel() {
         UserRepository.getInstance().getUser()//TODO replace UserRepository.getInstance() with dependency injection in the future
                 .observeOn(Schedulers.io())
                 .subscribeOn(Schedulers.io())
                 .subscribe(userResource -> {
             if (userResource.getStatus() == SUCESS) {
                 user.set(userResource.getData());
             } else if (userResource.getStatus() == ERROR) {
                 Log.e(TAG,userResource.getMessage());
             }
         });

    }
}
