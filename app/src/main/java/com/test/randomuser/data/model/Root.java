package com.test.randomuser.data.model;

import java.util.List;

public class Root{
        public List<Result> results;
        public Info info;

    public Root() {
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
