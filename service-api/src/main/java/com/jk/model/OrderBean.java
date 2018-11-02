package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 订单表
 */
public class OrderBean implements Serializable {

    private String orderid; // 主键 订单编号

    private String linkuserid; // 关联用户id



    private String linkbothid; // 关联 收货信息表 的 id

    private String paystatus; // 支付状态  1：支付 2:未支付

    private String orderstatus ;
                                /*
                     订单状态：是为    1 待付款；  2待发货   3 已发货    4已完成    5 已关闭
          未支付为1，已支付为2 对应操作  关闭订单   订单发货  订单跟踪    订单跟踪    删除订单
                                       订单详情   订单详情  订单详情    订单详情   查看详情
                                    */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date subtime;// 订单提交时间

    /**
     * 展示字段
     * @return
     */
    private String showuseraccount;//展示用户账号

    private String showprice;// 展示商品价格

    private String subdate;// 时间搜索字段；

    private String consignnum;//发货单号； 默认为 0； 然后发货后生成发货单号；


    private String showuser; // 展示 收货人信息

    private String showimg; // 展示 商品 图片

    private String showcount;//展示 商品数量

    private String showname; // 展示商品名称

    private String showphone;//展示联系电话；

    private String showsheng;//展示省级地址

    private String showdetail;//展示详细的收货地址；

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getLinkuserid() {
        return linkuserid;
    }

    public void setLinkuserid(String linkuserid) {
        this.linkuserid = linkuserid;
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

    public Date getSubtime() {
        return subtime;
    }

    public void setSubtime(Date subtime) {
        this.subtime = subtime;
    }

    public String getShowuseraccount() {
        return showuseraccount;
    }

    public void setShowuseraccount(String showuseraccount) {
        this.showuseraccount = showuseraccount;
    }

    public String getShowprice() {
        return showprice;
    }

    public void setShowprice(String showprice) {
        this.showprice = showprice;
    }

    public String getSubdate() {
        return subdate;
    }

    public void setSubdate(String subdate) {
        this.subdate = subdate;
    }


    public String getConsignnum() {
        return consignnum;
    }

    public void setConsignnum(String consignnum) {
        this.consignnum = consignnum;
    }

    public String getShowuser() {
        return showuser;
    }

    public void setShowuser(String showuser) {
        this.showuser = showuser;
    }

    public String getShowimg() {
        return showimg;
    }

    public void setShowimg(String showimg) {
        this.showimg = showimg;
    }

    public String getShowcount() {
        return showcount;
    }

    public void setShowcount(String showcount) {
        this.showcount = showcount;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getShowphone() {
        return showphone;
    }

    public void setShowphone(String showphone) {
        this.showphone = showphone;
    }

    public String getShowsheng() {
        return showsheng;
    }

    public void setShowsheng(String showsheng) {
        this.showsheng = showsheng;
    }

    public String getShowdetail() {
        return showdetail;
    }

    public void setShowdetail(String showdetail) {
        this.showdetail = showdetail;
    }
}