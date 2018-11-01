package com.jk.model;

import java.io.Serializable;

public class Comm implements Serializable {

    private String id;

    private String number;  //编号名称

    private String nameid;    //商品名称
    private String showname;

    private String photo;   //图片

    private String price;  //价格

    private Integer store = 0;  //库存

    private Integer audit = 2;  //审核状态

    private Integer added = 2;            //上下架

    private Integer label = 2;		  //标签（2.新品，1.推荐,3.热销）

    private String modelsid;		  //型号
    private String showmodels;

    private String colourid;          // 颜色
    private String showcolour;

    private String specid;              //规格
    private String showspec;

    private Integer edition;	  //版本（1.移动4G，2.联通4G，3.电信4G，4.全网通）

    private String detail;		  //详情


    //用户订单暂存字段
    private Long comnum; //用户所买的商品数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public Integer getAdded() {
        return added;
    }

    public void setAdded(Integer added) {
        this.added = added;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getModelsid() {
        return modelsid;
    }

    public void setModelsid(String modelsid) {
        this.modelsid = modelsid;
    }

    public String getShowmodels() {
        return showmodels;
    }

    public void setShowmodels(String showmodels) {
        this.showmodels = showmodels;
    }

    public String getColourid() {
        return colourid;
    }

    public void setColourid(String colourid) {
        this.colourid = colourid;
    }

    public String getShowcolour() {
        return showcolour;
    }

    public void setShowcolour(String showcolour) {
        this.showcolour = showcolour;
    }

    public String getSpecid() {
        return specid;
    }

    public void setSpecid(String specid) {
        this.specid = specid;
    }

    public String getShowspec() {
        return showspec;
    }

    public void setShowspec(String showspec) {
        this.showspec = showspec;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getComnum() {
        return comnum;
    }

    public void setComnum(Long comnum) {
        this.comnum = comnum;
    }
}
