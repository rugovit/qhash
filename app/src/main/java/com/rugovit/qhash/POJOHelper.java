package com.rugovit.qhash;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rugovit on 11/28/2017.
 */

public class POJOHelper {
    public static UserPOJO getUserPOJO(FirebaseUser firebaseUser){
        UserPOJO user =new UserPOJO();
        if (firebaseUser.isAnonymous()) user.setDisplayName(firebaseUser.getUid());
        else user.setDisplayName(firebaseUser.getDisplayName());
        return  user;
    }
}
