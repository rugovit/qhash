package com.rugovit.qhash;

import android.databinding.ObservableField;

/**
 * Created by rugovit on 11/28/2017.
 */

public class UserViewModel extends BaseViewModel {


    public final ObservableField<User> user = new ObservableField<>();


    private String displayName;
///////////////////////////////////////////////////
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
