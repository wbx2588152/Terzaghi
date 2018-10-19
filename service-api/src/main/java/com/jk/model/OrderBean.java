package com.jk.model;

import java.util.Date;

/**
 * 订单表
 */
public class OrderBean {
    private String orderid; // 主键 订单编号

    private Date linkuserid; // 关联用户id

    private String linkcommodifyid;// 关联商品id

    private String linkbothid; // 关联 用户收货关联表 的 id

    private String paystatus; // 支付状态  1：支付 2:未支付

    private String orderstatus ;
                                /*
       订单状态：默认生成订单是为0；代表为 待付款； 1 待发货  2 已发货   3 已完成   4  已关闭
                              对应操作  关闭订单   订单发货  订单跟踪    订单跟踪    删除订单
                                       订单详情   订单详情  订单详情    订单详情   查看详情
                                    */
    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Date getLinkuserid() {
        return linkuserid;
    }

    public void setLinkuserid(Date linkuserid) {
        this.linkuserid = linkuserid;
    }

    public String getLinkcommodifyid() {
        return linkcommodifyid;
    }

    public void setLinkcommodifyid(String linkcommodifyid) {
        this.linkcommodifyid = linkcommodifyid == null ? null : linkcommodifyid.trim();
    }

    public String getLinkbothid() {
        return linkbothid;
    }

    public void setLinkbothid(String linkbothid) {
        this.linkbothid = linkbothid == null ? null : linkbothid.trim();
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }
}