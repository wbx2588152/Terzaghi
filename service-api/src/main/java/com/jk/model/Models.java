package com.jk.model;

import java.io.Serializable;

public class Models implements Serializable {

    private String id;

    private String name;

    private String modelsid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelsid() {
        return modelsid;
    }

    public void setModelsid(String modelsid) {
        this.modelsid = modelsid;
    }
}
