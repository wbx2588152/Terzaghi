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

    private Integer price;   //价格

    private Integer seckillPrice;    //秒杀价格

    private Integer seckillCount;    //秒杀数量

    private Integer limitationCount;    //已经抢了多少数量

    private Integer surplusCount;    //剩余数量

    private String commmondityImg;  //商品封面  秒杀封面

    private Date addTime; //添加时间 按时间进行排序

    private String status;   //状态 上架和下架

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setSeckillPrice(Integer seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public void setSeckillCount(Integer seckillCount) {
        this.seckillCount = seckillCount;
    }

    public void setLimitationCount(Integer limitationCount) {
        this.limitationCount = limitationCount;
    }

    public void setSurplusCount(Integer surplusCount) {
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

    public Integer getPrice() {
        return price;
    }

    public Integer getSeckillPrice() {
        return seckillPrice;
    }

    public Integer getSeckillCount() {
        return seckillCount;
    }

    public Integer getLimitationCount() {
        return limitationCount;
    }

    public Integer getSurplusCount() {
        return surplusCount;
    }

}
