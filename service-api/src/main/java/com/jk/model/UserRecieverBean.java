package com.jk.model;

import java.io.Serializable;

/**
 * 用户收货信息关联表
 */
public class UserRecieverBean implements Serializable {

    private String id; //表 主键id

    private String linkrecieverid; // 关联收货信息表

    private String linkuserid;// 关联用户表；

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkrecieverid() {
        return linkrecieverid;
    }

    public void setLinkrecieverid(String linkrecieverid) {
        this.linkrecieverid = linkrecieverid == null ? null : linkrecieverid.trim();
    }

    public String getLinkuserid() {
        return linkuserid;
    }

    public void setLinkuserid(String linkuserid) {
        this.linkuserid = linkuserid == null ? null : linkuserid.trim();
    }




}