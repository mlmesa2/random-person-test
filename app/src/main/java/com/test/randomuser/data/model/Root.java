package com.test.randomuser.data.model;

import java.util.List;

public class Root{
        public List<RandomPerson> results;
        public Info info;

    public Root(List<RandomPerson> results, Info info) {
        this.results = results;
        this.info = info;
    }

    public Root() {
    }

    public List<RandomPerson> getResults() {
        return results;
    }

    public void setResults(List<RandomPerson> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
