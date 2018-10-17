package com.jk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:秒杀时间段
 */
public class SeckillTimeBean implements Serializable {

    private String id;      //主键

    private String timeName;    //秒杀时段名称

    private Date startTime;     //每日开始时间

    private Date endTime;       //每日结束时间

    private String status;      //状态


    /**
     * 以下临时字段
     * */
    private String commodityCount;  //商品数量


    public void setId(String id) {
        this.id = id;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setCommodityCount(String commodityCount) {
        this.commodityCount = commodityCount;
    }

    public String getId() {
        return id;
    }

    public String getTimeName() {
        return timeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getCommodityCount() {
        return commodityCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {

        return status;
    }
}
