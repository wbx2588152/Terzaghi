package com.jk.controller.order;


import com.alibaba.fastjson.JSONObject;
import com.jk.model.*;
import com.jk.service.order.OrderService;
import com.jk.utils.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="order")
public class OrderController {


    @Autowired
    OrderService orderService;


    //生成订单
   @RequestMapping(value="tograduateorder")
    private  String tograduateorder(UserRecieverBean userRecieverBean, Comm commBean,OrderBean orderBean){
      /*  Session session = SecurityUtils.getSubject().getSession();
        User userBean = (User) session.getAttribute(session.getId());
        orderBean.setLinkuserid(userBean.getId());*/
        orderBean.setLinkbothid(userRecieverBean.getId());
        orderBean.setLinkcommodifyid(commBean.getId());
        orderBean.setSubtime(new Date());

        IdWorker idWorker = new IdWorker(1, 0);
        long id = idWorker.nextId();
        orderBean.setOrderid(String.valueOf(id));
        orderService.generateOrdeBean(orderBean);
        return "../";
    }



    @RequestMapping(value = "orderpage")
    public  String orderpage(){
        return "/order/orderlist";
    }



    @RequestMapping(value = "queryorderlist")
    @ResponseBody
    public String queryorderlist(Integer page, Integer limit, OrderBean orderBean){
        Map<String,Object> map=orderService.queryorderlist(page,limit,orderBean);
        List<OrderBean> list = (List<OrderBean>) map.get("data");
        int count= (int) map.get("count");
        JSONObject obj=new JSONObject();
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);
        return obj.toString();
    }

    @RequestMapping(value = "delorderbyid")
    @ResponseBody
    public void delorderbyid(String orderid){
        orderService.delorderbyid(orderid);
    }


    @RequestMapping("toaddpage")
    public String toaddpage(){
        return "/order/addpage";
    }


    @RequestMapping("toshowdetail")
    public String toshowdetail(String orderid, ModelMap modelMap){
        System.out.println(orderid);
        Map<String,Object> map=orderService.queryorderalldata(orderid);
        modelMap.put("orderBean",map.get("orderBean"));
        modelMap.put("expressBean",map.get("expressBean"));
        modelMap.put("recieverBean",map.get("recieverBean"));
        modelMap.put("commBean",map.get("commBean"));
        return "/order/showdetail";
    }






}
