package com.jk.model;

import java.io.Serializable;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:秒杀商品表
 */
public class CommodityBean implements Serializable {

    private String id;      //商品主键id

    private String name;    //商品名称

    private String artNo;   //货号

    private String price;   //价格

    private String seckillPrice;    //秒杀价格

    private String seckillCount;    //秒杀数量

    private String limitationCount;    //限购数量

    private String commodityId;      //关联商品id

    private String seckillTimeId;   //关联秒杀时间表的id

    private String surplusCount;    //剩余数量

    public String getSeckillTimeId() {
        return seckillTimeId;
    }

    public void setSeckillTimeId(String seckillTimeId) {
        this.seckillTimeId = seckillTimeId;
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

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityId() {

        return commodityId;
    }

}
