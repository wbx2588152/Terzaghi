package com.jk.model;

import java.io.Serializable;
import java.util.Date;

public class StockBean implements Serializable {
    private String stockid;

    private String stockimg;

    private String stockname;

    private String specifications;

    private String stockcolor;

    private String stockinventory;

    private String newquantity;

    private String stocktype;

    private String operationtype;

    private Date operationdate;

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    public String getStockimg() {
        return stockimg;
    }

    public void setStockimg(String stockimg) {
        this.stockimg = stockimg;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getStockcolor() {
        return stockcolor;
    }

    public void setStockcolor(String stockcolor) {
        this.stockcolor = stockcolor;
    }

    public String getStockinventory() {
        return stockinventory;
    }

    public void setStockinventory(String stockinventory) {
        this.stockinventory = stockinventory;
    }

    public String getNewquantity() {
        return newquantity;
    }

    public void setNewquantity(String newquantity) {
        this.newquantity = newquantity;
    }

    public String getStocktype() {
        return stocktype;
    }

    public void setStocktype(String stocktype) {
        this.stocktype = stocktype;
    }

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    @Override
    public String toString() {
        return "StockBean{" +
                "stockid='" + stockid + '\'' +
                ", stockimg='" + stockimg + '\'' +
                ", stockname='" + stockname + '\'' +
                ", specifications='" + specifications + '\'' +
                ", stockcolor='" + stockcolor + '\'' +
                ", stockinventory='" + stockinventory + '\'' +
                ", newquantity='" + newquantity + '\'' +
                ", stocktype='" + stocktype + '\'' +
                ", operationtype='" + operationtype + '\'' +
                ", operationdate=" + operationdate +
                '}';
    }
}