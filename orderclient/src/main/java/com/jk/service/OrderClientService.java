package com.jk.service;


import com.jk.model.OrderBean;

import java.util.Map;

public interface OrderClientService {


    Map<String, Object> queryorderlist(Integer page, Integer limit, OrderBean orderBean);

    void delorderbyid(String orderid);

    void generateOrdeBean(OrderBean orderBean);


    Map<String, Object> queryorderalldata(String orderid);


}
