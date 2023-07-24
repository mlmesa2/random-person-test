package com.test.randomuser.di;

import com.test.randomuser.Util;
import com.test.randomuser.data.network.NetworkCalls;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;

import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Util.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public NetworkCalls provideApiService(Retrofit retrofit){
        return retrofit.create(NetworkCalls.class);
    }


//    @Singleton
//    @Provides
//    public RandomPersonRepositoryImpl providesRepository(NetworkCalls networkCalls){
//        return new RandomPersonRepositoryImpl(networkCalls);
//    }


   /* @Singleton
    @Provides
    public NetworkCalls getRetrofitInterface(Retrofit retrofit) {
        return retrofit.create(NetworkCalls.class);
    }*/

}
