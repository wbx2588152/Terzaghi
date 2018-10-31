package com.jk.model;

import java.io.Serializable;

public class FoodUnit implements Serializable {

    private String id;

    private String unit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
