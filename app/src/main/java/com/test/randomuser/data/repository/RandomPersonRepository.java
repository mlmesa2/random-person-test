package com.test.randomuser.data.repository;

import com.test.randomuser.data.model.NetwokResult;
import com.test.randomuser.data.model.Root;

abstract class RandomPersonRepository {

    public abstract NetwokResult getRamdomUserList(Integer page,
                                                   Integer results,
                                                   String seed);
}
