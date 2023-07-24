package com.test.randomuser.data.network;

import com.test.randomuser.data.model.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkCalls {

    @GET("api/")
    Call <Root> getAllRandomPerson(
            @Query("page") Integer page,
            @Query("results") Integer results,
            @Query("seed") String seed);
}
