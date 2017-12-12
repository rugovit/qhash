package com.rugovit.qhash;

import com.rugovit.qhash.base_classes.data.Resource;
import com.rugovit.qhash.workflow.login.User;
import com.rugovit.qhash.workflow.login.UserRepository;
import com.rugovit.qhash.workflow.login.UserViewModel;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

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
        TestScheduler scheduler = new TestScheduler();
        when(repository.getUserObserver()).thenReturn(userObservable);
        UserViewModel userViewModel=new UserViewModel(repository,scheduler);
        assertEquals(userName, userViewModel.user.get().getDisplayName());
    }
}
