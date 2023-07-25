package com.test.randomuser.data.model;

import java.util.List;

public class NetwokResult {
    public Data data;
    public String errorMessage;

    public NetwokResult(Data data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
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
        public List<RandomPerson> result;
        public Info info;

        public Data(List<RandomPerson> result, Info info) {
            this.result = result;
            this.info = info;
        }

        public List<RandomPerson> getListResult() {
            return result;
        }

        public void setListResult(List<RandomPerson> result) {
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
