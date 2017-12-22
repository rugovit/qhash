package com.rugovit.qhash.workflow.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.rugovit.qhash.R;
import com.rugovit.qhash.base_classes.view.BaseActivity;
import com.rugovit.qhash.databinding.ActivityLauncherBinding;

public class LauncherActivity extends BaseActivity {
    ActivityLauncherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_launcher);
        binding.setUser(new UserViewModel(UserRepository.getInstance(),getApplication()));
    }

}
