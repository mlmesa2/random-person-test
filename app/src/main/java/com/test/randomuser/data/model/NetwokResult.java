package com.test.randomuser.data.model;

import java.util.List;

public class NetwokResult {
    public Data data;
    public String errorMessage;

    public NetwokResult(Data data) {
        this.data = data;
    }
    public NetwokResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public NetwokResult(){}

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class Data{
        public List<Result> result;
        public Info info;

        public Data(List<Result> result, Info info) {
            this.result = result;
            this.info = info;
        }

        public List<Result> getListResult() {
            return result;
        }

        public void setListResult(List<Result> result) {
            this.result = result;
        }

        public Info getInfo() {
            return info;
        }

        public void setInfo(Info info) {
            this.info = info;
        }
    }






}
