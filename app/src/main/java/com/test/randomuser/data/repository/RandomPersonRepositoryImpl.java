package com.test.randomuser.data.repository;

import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.network.Api;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RandomPersonRepositoryImpl extends RandomPersonRepository {
  private Api mApi;

   @Inject
   public RandomPersonRepositoryImpl(Api api){
       this.mApi = api;
   }

    @Override
    public Observable<Root> getRamdomUserList(int page, int results, String seed) {
        return mApi.getAllRandomPerson(page, results, seed)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
