package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {

    //优惠券Id
    private String id;

    //优惠券使用的固定天数
    private String fixday;

    //过期时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endDate;

    //优惠金额
    private String price;

    //满减规则
    private String  man;

    //剩余数量
    private Integer count;

    //业务字段


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getFixday() {
        return fixday;
    }

    public void setFixday(String fixday) {
        this.fixday = fixday;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
