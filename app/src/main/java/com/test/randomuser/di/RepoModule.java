package com.test.randomuser.di;

import com.test.randomuser.data.network.Api;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {

    @Provides
    public RandomPersonRepositoryImpl provideRepo(Api api) {
        return new RandomPersonRepositoryImpl(api);
    }
}
