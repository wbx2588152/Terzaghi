package com.jk.model;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    private String name;

    private String mycode;

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

    public String getMycode() {
        return mycode;
    }

    public void setMycode(String mycode) {
        this.mycode = mycode;
    }





}
