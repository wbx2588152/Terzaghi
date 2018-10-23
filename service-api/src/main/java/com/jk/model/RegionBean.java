package com.jk.model;

import java.io.Serializable;

public class RegionBean implements Serializable {

    private String id;      //主键

    private String name;    //收件人姓名

    private String phone;   //收件人手机号

    private String sheng;   //省明称

    private String inDetail;    //详细地址

    private String userId;  //关联用户表id

    public void setInDetail(String inDetail) {
        this.inDetail = inDetail;
    }

    public String getInDetail() {
        return inDetail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSheng() {
        return sheng;
    }

}
