package com.jk.model;

import java.io.Serializable;

public class Spec implements Serializable {

    private String id;

    private String spec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}
