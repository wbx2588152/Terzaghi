package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Models implements Serializable {

    private String id;

    private String name;

    private String modelsid;
    private String showmodles;

    private Integer sort;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modelsdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelsid() {
        return modelsid;
    }

    public void setModelsid(String modelsid) {
        this.modelsid = modelsid;
    }

    public String getShowmodles() {
        return showmodles;
    }

    public void setShowmodles(String showmodles) {
        this.showmodles = showmodles;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getModelsdate() {
        return modelsdate;
    }

    public void setModelsdate(Date modelsdate) {
        this.modelsdate = modelsdate;
    }
}