package com.jk.model;

import java.io.Serializable;

public class Color implements Serializable {

    private String id;

    private String colour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
