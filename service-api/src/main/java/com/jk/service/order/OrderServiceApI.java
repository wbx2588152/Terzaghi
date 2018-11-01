package com.jk.service.order;

import com.jk.model.OrderBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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

    @RequestMapping(value = "oneclient/savedeliverdata",method = RequestMethod.POST)
    void savedeliverdata(@RequestParam(value = "consignnum") String consignnum, @RequestParam(value = "expresscompany") String expresscompany, @RequestParam(value = "orderid") String orderid);

    @RequestMapping(value = "oneclient/initselone",method = RequestMethod.POST)
    List<OrderBean> initselone();


   // @RequestMapping(value = "oneclient/mergerorders",method = RequestMethod.POST)
    //void mergerorders(@RequestParam(value = "mainorder") String mainorder, @RequestParam("subarr") String subarr);

    @RequestMapping(value = "oneclient/automergerorder",method = RequestMethod.POST)
    void automergerorder();


    //shopping 里 的方法

    @RequestMapping(value = "oneclient/queryuserorder",method = RequestMethod.POST)
    Map<String, Object> queryuserorder(@RequestParam(value = "id") String id);
}
