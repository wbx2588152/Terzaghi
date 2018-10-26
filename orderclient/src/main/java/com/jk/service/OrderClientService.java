package com.jk.service;


import com.jk.model.OrderBean;

import java.util.List;
import java.util.Map;

public interface OrderClientService {


    Map<String, Object> queryorderlist(Integer page, Integer limit, OrderBean orderBean);

    void delorderbyid(String orderid);

    void generateOrdeBean(OrderBean orderBean);


    Map<String, Object> queryorderalldata(String orderid);


    void savedeliverdata(String consignnum, String expresscompany, String orderid);

    List<OrderBean> inselone();


    void mergerorders(String mainorder, String subarr);


}
