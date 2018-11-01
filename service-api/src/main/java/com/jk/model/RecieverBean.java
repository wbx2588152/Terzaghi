package com.jk.model;

import java.io.Serializable;

/*
收货信息表
 */
public class RecieverBean  implements Serializable {
    private String id; // 主键id

    private String receiver;// 收件人

    private String receiverphone;// 收件人电话

    private String receivermail;// 收件人 邮政编码

    private String receiveraddress;// 收件人地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone == null ? null : receiverphone.trim();
    }

    public String getReceivermail() {
        return receivermail;
    }

    public void setReceivermail(String receivermail) {
        this.receivermail = receivermail == null ? null : receivermail.trim();
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress == null ? null : receiveraddress.trim();
    }
}