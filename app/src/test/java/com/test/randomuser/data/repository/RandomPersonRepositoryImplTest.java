package com.test.randomuser.data.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.network.Api;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;

public class RandomPersonRepositoryImplTest {

    @Mock
    private Api mockApi;

    private RandomPersonRepositoryImpl randomPersonRepository;

    private TestScheduler testScheduler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // Set up RxJava schedulers for testing
        testScheduler = new TestScheduler();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> testScheduler);
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> testScheduler);
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> testScheduler);

        randomPersonRepository = new RandomPersonRepositoryImpl(mockApi);
    }

    @Test
    public void testGetRandomUserList() {
        // Arrange
        int page = 1;
        int results = 10;
        String seed = "test";
        Root root = new Root();

        when(mockApi.getAllRandomPerson(page, results, seed))
                .thenReturn(Observable.just(root));

        // Act
        Observable<Root> observable = randomPersonRepository.getRamdomUserList(page, results, seed);

        // Assert
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(result -> {
                    Assert.assertSame(root, result);
                });

        verify(mockApi).getAllRandomPerson(page, results, seed);
    }

}