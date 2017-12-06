package com.rugovit.qhash;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.login.User;

import java.util.concurrent.Executor;

import io.reactivex.Observable;


/**
 * Created by rugovit on 11/28/2017.
 */

public class UserRepository {

    private volatile static UserRepository INSTANCE = null;
    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link UserRepository} instance
     */
    public static UserRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepository();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance()} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////


    public Observable<Resource<User>> getUser(){

        Observable<Resource<User>> userObservable = Observable.create(emitter -> {

             if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                 User user = getUserConverter(FirebaseAuth.getInstance().getCurrentUser());
                 Resource<User> resource= Resource.success(user);
                 emitter.onNext(resource);
                 emitter.onComplete();
             }

             FirebaseAuth.getInstance().signInAnonymously()
                     .addOnCompleteListener((Executor) this, task -> {
                         if (task.isSuccessful()) {
                             // Sign in success, update UI with the signed-in user's information
                             User user = getUserConverter(FirebaseAuth.getInstance().getCurrentUser());
                             Resource<User> resource= Resource.success(user);
                             emitter.onNext(resource);
                             emitter.onComplete();
                         } else {
                             // If sign in fails, display a message to the user.
                             Resource<User> resource= Resource.error(task.getException().getMessage(),null);
                             emitter.onNext(resource);
                             emitter.onComplete();
                         }
                     });


        });
        return userObservable;
    }




/////////////////////////////////////////////////////////////////////////////////////////////////////
    private  User getUserConverter(FirebaseUser firebaseUser){
        User user =new User();
        if (firebaseUser.isAnonymous()) user.setDisplayName(firebaseUser.getUid());
        else user.setDisplayName(firebaseUser.getDisplayName());
        return  user;
    }
}
