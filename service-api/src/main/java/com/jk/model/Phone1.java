package com.jk.model;

import java.io.Serializable;

public class Phone1 implements Serializable {

    private String id;

    private String models;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }
}
