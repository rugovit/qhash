package com.rugovit.qhash;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    public LiveData<User> getUser(String userId) {
        final MutableLiveData<User> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            // Error handling will be explained in the next article …
        });

        return data;
    }
    public void signIn(){

        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            firebaseUser=user;
                            onLoginFirabaseSucces(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            onLoginFirabaseFaild(task);
                        }
                    }
                });
    }




/////////////////////////////////////////////////////////////////////////////////////////////////////
    private  User getUserConverter(FirebaseUser firebaseUser){
        User user =new User();
        if (firebaseUser.isAnonymous()) user.setDisplayName(firebaseUser.getUid());
        else user.setDisplayName(firebaseUser.getDisplayName());
        return  user;
    }
}
