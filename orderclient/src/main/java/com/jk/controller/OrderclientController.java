package com.jk.controller;

import com.jk.model.Comm;
import com.jk.model.OrderBean;
import com.jk.model.RegionBean;
import com.jk.service.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "oneclient")
public class OrderclientController {


    @Autowired
    private OrderClientService orderClientService;


    @RequestMapping(value = "queryorderlist",method = RequestMethod.POST)
    public Map<String,Object> queryorderlist(Integer page, Integer limit, @RequestBody OrderBean orderBean){
        return orderClientService.queryorderlist(page,limit,orderBean);

    }


    @RequestMapping(value = "delorderbyid",method=RequestMethod.POST)
    public void delorderbyid(String orderid){
        orderClientService.delorderbyid(orderid);
    }


    @RequestMapping(value = "generateOrdeBean",method = RequestMethod.POST)
    public void generateOrdeBean(@RequestBody OrderBean orderBean){
        orderClientService.generateOrdeBean(orderBean);
    }



    @RequestMapping(value = "queryorderalldata",method = RequestMethod.POST)
    public Map<String,Object> queryorderalldata(String orderid){
       return orderClientService.queryorderalldata(orderid);
    }


    //发货
    @RequestMapping(value = "savedeliverdata",method = RequestMethod.POST)
    public void savedeliverdata(String consignnum,String expresscompany,String orderid){
        orderClientService.savedeliverdata(consignnum,expresscompany,orderid);
    }


    @RequestMapping(value = "initselone",method = RequestMethod.POST)
    public List<OrderBean> inselone(){
        return orderClientService.inselone();
    }

    //合并订单
   /* @RequestMapping(value = "mergerorders",method = RequestMethod.POST)
    public void mergerorders(String mainorder,String subarr){
        orderClientService.mergerorders(mainorder,subarr);
    }*/


   //自动合单
    @RequestMapping(value = "automergerorder",method = RequestMethod.POST)
    public void automergerorder(){
        orderClientService.automergerorder();
    }



    //shopping 项目的调用方法
    @RequestMapping( value = "queryuserorder",method = RequestMethod.POST)
    public Map<String,Object> queryuserorder(String id){
        Map<String, Object> map = orderClientService.queryuserorder(id);
        return map;
    }

    @RequestMapping(value = "queryRegionAgain",method = RequestMethod.POST)
    public OrderBean queryRegionAgain(String id,String orderid){
        return orderClientService.queryRegionAgain(id,orderid);
    }

    @RequestMapping(value = "updateorderstatus",method = RequestMethod.POST)
    public void updateorderstatus(String orderid){
        orderClientService.updateorderstatus(orderid);
    }



}
