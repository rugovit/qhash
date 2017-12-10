package com.rugovit.qhash;

import com.google.firebase.auth.FirebaseAuth;
import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.login.User;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by rugovit on 12/10/2017.
 */

public class LoginTest {
    @Test
    public void testRepository() throws Exception {

        final String userName="testUser";
        UserRepository repository=mock(UserRepository.class);
        Observable<Resource<User>> userObservable = Observable.create(emitter -> {
            User user =new User();
            user.setDisplayName(userName);
            Resource<User> resource= Resource.success(user);
            emitter.onNext(resource);
            emitter.onComplete();
        });
        when(repository.getUser()).thenReturn(userObservable);
        UserViewModel userViewModel=new UserViewModel(repository);
        assertEquals(userName, userViewModel.user.get().getDisplayName());
    }
}
