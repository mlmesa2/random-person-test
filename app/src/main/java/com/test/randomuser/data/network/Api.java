package com.test.randomuser.data.network;


import com.test.randomuser.data.model.Root;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("api/")
    Observable<Root> getAllRandomPerson(
            @Query("page") int page,
            @Query("results") int results,
            @Query("seed") String seed);
}
