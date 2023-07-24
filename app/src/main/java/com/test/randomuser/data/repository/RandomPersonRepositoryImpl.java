package com.test.randomuser.data.repository;

import static com.test.randomuser.Util.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.test.randomuser.data.model.NetwokResult;
import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.network.NetworkCalls;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomPersonRepositoryImpl extends RandomPersonRepository {

  private NetworkCalls mNetworkCalls;


  /* @Inject
   NetworkCalls networkCalls;*/
   @Inject
   public RandomPersonRepositoryImpl(NetworkCalls networkCalls){
       this.mNetworkCalls = networkCalls;
   }
    @Override
    public NetwokResult getRamdomUserList(Integer page,
                                          Integer results,
                                          String seed) {
        Call<Root> call = mNetworkCalls.getAllRandomPerson(page, results, seed);
        NetwokResult result = new NetwokResult();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    result.data.setListResult(response.body().results);
                    result.data.setInfo(response.body().getInfo());
                    Log.d(TAG, "onResponse: THIS IS THE RESPONSE" + response.body().results);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
                 result.setErrorMessage("Failure");
                Log.d(TAG, "onFailure: JUST ERROR");
            }
        });
        return result;
    }
}
