package com.test.randomuser.ui.state;

import com.test.randomuser.data.model.Info;
import com.test.randomuser.data.model.RandomPerson;

import java.util.List;

public class ListState {
    private final List<RandomPerson> resultList;
    private final String errorMessage;
    private final Boolean isloading;
    private final Info info;

    public ListState(List<RandomPerson> resultList, String errorMessage, Boolean isloading, Info info) {
        this.resultList = resultList;
        this.errorMessage = errorMessage;
        this.isloading = isloading;
        this.info = info;
    }

    public List<RandomPerson> getResultList() {
        return resultList;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Boolean getIsloading() {
        return isloading;
    }

    public Info getInfo() {
        return info;
    }
}
