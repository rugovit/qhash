package com.rugovit.qhash;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rugovit.qhash.databinding.ActivityLauncherBinding;

public class LauncherActivity extends BaseActivity {
    ActivityLauncherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_launcher);
        if(firebaseUser!=null){
            binding.setUser(POJOHelper.getUserPOJO(firebaseUser));
        }
    }
    @Override
    protected void onLoginFirabaseSucces( FirebaseUser user){
        binding.setUser(POJOHelper.getUserPOJO(user));
    }

}
