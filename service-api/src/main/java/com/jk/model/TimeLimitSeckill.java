package com.jk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王超杰
 * @date 2018/10/19
 * @Description:限时秒杀表
 */
public class TimeLimitSeckill implements Serializable {

    private String id;

    private String name;    //商品名称  标题

    private String artNo;   //商品描述

    private String price;   //价格

    private String seckillPrice;    //秒杀价格

    private String limitationCount;    //已经抢了多少数量

    private String commmondityImg;  //商品封面  秒杀封面

    private Date addTime; //添加时间 按时间进行排序

    private Date endTime;   //活动结束时间

    private String timeLimit;   //限时 分钟

    private String status;   //状态 上架和下架

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getAddTime() {

        return addTime;
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

    public void setLimitationCount(String limitationCount) {
        this.limitationCount = limitationCount;
    }

    public void setCommmondityImg(String commmondityImg) {
        this.commmondityImg = commmondityImg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
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

    public String getLimitationCount() {
        return limitationCount;
    }

    public String getCommmondityImg() {
        return commmondityImg;
    }

    public String getStatus() {
        return status;
    }

    public String getTimeLimit() {
        return timeLimit;
    }
}
