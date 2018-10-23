package com.jk.service.order;

import com.jk.model.OrderBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface OrderServiceApI {


    @RequestMapping(value = "oneclient/queryorderlist",method = RequestMethod.POST)
    Map<String, Object> queryorderlist(@RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit, @RequestBody OrderBean orderBean);


    @RequestMapping(value = "oneclient/delorderbyid",method = RequestMethod.POST)
    void delorderbyid(@RequestParam(value = "orderid") String orderid);


    @RequestMapping(value = "oneclient/generateOrdeBean",method = RequestMethod.POST)
    void generateOrdeBean(@RequestBody OrderBean orderBean);



    @RequestMapping(value = "oneclient/queryorderalldata",method = RequestMethod.POST)
    Map<String, Object> queryorderalldata(@RequestParam(value = "orderid") String orderid);




}
