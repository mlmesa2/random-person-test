package com.test.randomuser.ui.state;

import com.test.randomuser.data.model.Info;
import com.test.randomuser.data.model.Result;

import java.util.List;

public class ListState {
    private final List<Result> resultList;
    private final String errorMessage;
    private final Boolean isloading;
    private final Info info;

    public ListState(List<Result> resultList, String errorMessage, Boolean isloading, Info info) {
        this.resultList = resultList;
        this.errorMessage = errorMessage;
        this.isloading = isloading;
        this.info = info;
    }

    public List<Result> getResultList() {
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
