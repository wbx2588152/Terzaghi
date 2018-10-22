package com.jk.model;

import java.io.Serializable;

/**
 * @author 王超杰
 * @date 2018/10/18
 * @Description:秒杀限量 用来记录用户购买了多少个这件商品
 */
public class RestrictSeckillBean implements Serializable {

    private String id;        //主键

    private String userId;      //用户id

    private String commodityId;     //商品id

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCommodityId() {
        return commodityId;
    }
}
