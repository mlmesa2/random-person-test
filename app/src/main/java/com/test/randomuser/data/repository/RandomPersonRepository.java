package com.test.randomuser.data.repository;


import com.test.randomuser.data.model.Root;

import io.reactivex.Observable;

public abstract class RandomPersonRepository {

    public abstract Observable<Root> getRamdomUserList(int page, int results, String seed);

}
