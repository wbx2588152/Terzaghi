package com.jk.model;

import java.io.Serializable;

public class UserRole  implements Serializable {
    private String id; //用户角色id

    private String userid; //用户id

    private String roleid; //角色id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}