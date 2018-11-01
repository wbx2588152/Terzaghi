package com.jk.model;

import java.io.Serializable;

public class CommOrderBean implements Serializable {
    private String id;

    private String commid;  //关联商品id

    private String orderid; // 关联订单id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCommid() {
        return commid;
    }

    public void setCommid(String commid) {
        this.commid = commid == null ? null : commid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }
}