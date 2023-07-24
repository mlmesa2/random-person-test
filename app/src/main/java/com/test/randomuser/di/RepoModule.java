package com.test.randomuser.di;

import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.network.NetworkCalls;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Call;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {

    @Provides
    public RandomPersonRepositoryImpl provideRepo(NetworkCalls networkCalls) {
        return new RandomPersonRepositoryImpl(networkCalls);
    }
}
