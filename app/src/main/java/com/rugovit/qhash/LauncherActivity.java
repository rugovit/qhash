package com.rugovit.qhash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;
import com.rugovit.qhash.databinding.ActivityLauncherBinding;

public class LauncherActivity extends BaseActivity {
    ActivityLauncherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_launcher);
        if(firebaseUser!=null){
            binding.setUser(UserRepository.getUserPOJO(firebaseUser));
        }
    }
    @Override
    protected void onLoginFirabaseSucces( FirebaseUser user){
        binding.setUser(UserRepository.getUserPOJO(user));
    }

}
