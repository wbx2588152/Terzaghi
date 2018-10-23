package com.jk.model;

import java.io.Serializable;

public class RegionBean implements Serializable {

    private String id;      //主键

    private String name;    //收件人姓名

    private String phone;   //收件人手机号

    private String sheng;   //省明称

    private String shi;     //市名称

    private String inDetail;    //详细地址

    private String userid;  //关联用户表id

    public void setInDetail(String inDetail) {
        this.inDetail = inDetail;
    }

    public String getInDetail() {
        return inDetail;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSheng() {
        return sheng;
    }

    public String getShi() {
        return shi;
    }
}
