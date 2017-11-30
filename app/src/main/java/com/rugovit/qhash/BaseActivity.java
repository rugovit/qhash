package com.rugovit.qhash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rugovit on 11/28/2017.
 */

public class BaseActivity  extends AppCompatActivity{
    protected FirebaseAuth firebaseAuth;
    protected FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser==null){
            signIn();
        }
    }
    protected void signIn(){
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            firebaseUser=user;
                            onLoginFirabaseSucces(user);
                        } else {
                            // If sign in fails, display a message to the user.
                             onLoginFirabaseFaild(task);
                        }
                    }
                });
    }
    protected   void onLoginFirabaseSucces( FirebaseUser user){};
    protected   void onLoginFirabaseFaild(Task<AuthResult> task){
        Toast.makeText(BaseActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
    };
}
