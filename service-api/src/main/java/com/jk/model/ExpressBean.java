package com.jk.model;

import java.io.Serializable;

/**
 * 物流管理表
 */
public class ExpressBean implements Serializable {

    private String expressid;// 主键id

    private String expresscompany; // 物流公司

    private String expressnumber; // 物流单号

    private String linkorderid; // 关联订单号

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public String getExpresscompany() {
        return expresscompany;
    }

    public void setExpresscompany(String expresscompany) {
        this.expresscompany = expresscompany == null ? null : expresscompany.trim();
    }

    public String getExperssnumber() {
        return expressnumber;
    }

    public void setExperssnumber(String experssnumber) {
        this.expressnumber = experssnumber == null ? null : expressnumber.trim();
    }

    public String getLinkorderid() {
        return linkorderid;
    }

    public void setLinkorderid(String linkorderid) {
        this.linkorderid = linkorderid == null ? null : linkorderid.trim();
    }
}