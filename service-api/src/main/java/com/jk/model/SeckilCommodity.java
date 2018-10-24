package com.jk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:限量秒杀表
 */
public class SeckilCommodity implements Serializable {

    private String id;      //商品主键id

    private String name;    //商品名称  标题

    private String artNo;   //商品描述

    private String price;   //价格

    private String seckillPrice;    //秒杀价格

    private String seckillCount;    //秒杀数量

    private String limitationCount;    //限购数量

    private String surplusCount;    //剩余数量

    private String commmondityImg;  //商品封面  秒杀封面

    private Date addTime; //添加时间 按时间进行排序

    private String status;   //状态 上架和下架

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getAddTime() {

        return addTime;
    }

    public void setCommmondityImg(String commmondityImg) {
        this.commmondityImg = commmondityImg;
    }

    public String getCommmondityImg() {

        return commmondityImg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSeckillPrice(String seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public void setSeckillCount(String seckillCount) {
        this.seckillCount = seckillCount;
    }

    public void setLimitationCount(String limitationCount) {
        this.limitationCount = limitationCount;
    }

    public void setSurplusCount(String surplusCount) {
        this.surplusCount = surplusCount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtNo() {
        return artNo;
    }

    public String getPrice() {
        return price;
    }

    public String getSeckillPrice() {
        return seckillPrice;
    }

    public String getSeckillCount() {
        return seckillCount;
    }

    public String getLimitationCount() {
        return limitationCount;
    }

    public String getSurplusCount() {
        return surplusCount;
    }

}
