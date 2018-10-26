package com.jk.controller.order;


import com.alibaba.fastjson.JSONObject;
import com.jk.model.*;
import com.jk.service.order.OrderService;
import com.jk.utils.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

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
        orderBean.setLinkuserid(userBean.getId());
        orderBean.setLinkbothid(userRecieverBean.getId());
        orderBean.setLinkcommodifyid(commBean.getId());
        orderBean.setSubtime(new Date());
     */
        IdWorker idWorker = new IdWorker(1, 0);
        for (int i=0;i<50;i++){
            long id = idWorker.nextId();
           /* orderBean.setOrderid(String.valueOf(id));*/
            System.out.println(id);
        }

       // orderService.generateOrdeBean(orderBean);
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


    @RequestMapping("tomergepage")
    public String toaddpage(){
        return "/order/mergepage";
    }


    @RequestMapping("toshowdetail")
    public String toshowdetail(String orderid, ModelMap modelMap){
        System.out.println(orderid);
        Map<String,Object> map= new HashMap<String,Object>();
        map=orderService.queryorderalldata(orderid);
        modelMap.put("orderBean",map.get("orderBean"));
        modelMap.put("expressBean",map.get("expressBean"));
        modelMap.put("regionBean",map.get("regionBean"));
        int priceAll=0;
        List<Comm>  commList = (List<Comm>) map.get("commList");
        /*for (int i = 0; i <commList.size() ; i++) {
            Comm comm = (Comm)commList.get(i);
              priceAll+= Integer.parseInt(comm.getPrice());
        }*/
        modelMap.put("commList",commList);
        modelMap.put("priceAll",priceAll);

        return "order/showdetail";
    }

        /**
         * 订单发货
         */
        @RequestMapping("todelivergood")
        public String todelivergood(String orderid){

            return "/order/deliverpage";
        }

        //生成发货单号
        @RequestMapping("generategoodnum")
        @ResponseBody
        public String generategoodnum(){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmddHHmmss");
           /* Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
            if (uuidHashCode < 0) {
                uuidHashCode = uuidHashCode * (-1);
            }*/
            String date = simpleDateFormat.format(new Date());
             int node= 1000+ (int)(Math.random()*9000);
            return date+node;
        }


        @RequestMapping("savedeliverdata")
        @ResponseBody
        public String savedeliverdata(String consignnum,String expresscompany,String orderid){
            orderService. savedeliverdata(consignnum,expresscompany,orderid);

            return "success";
        }


        @RequestMapping("initselone")
        @ResponseBody
        public List<OrderBean> initselone(){
            List<OrderBean> list=orderService.initselone();
            return list;
        }


        @RequestMapping("initseltwo")
        public String initseltwo(String orderid, ModelMap map){
            List<OrderBean> list=orderService.initselone();
            List<OrderBean> list2 = new ArrayList<>();
            for (int i = 0; i <list.size() ; i++) {
                OrderBean orderBean = list.get(i);
                if (!orderBean.getOrderid().equals(orderid)){
                    list2.add(orderBean);
                }
            }
            map.put("list",list2);
            return "order/checkboxpage";
        }


       /* @RequestMapping("mergerorders")
        @ResponseBody
        public void mergerorders(String mainorder,String subarr){
            orderService.mergerorders(mainorder,subarr);
        }

*/

        @RequestMapping("automergerorder")
        @ResponseBody
        public String  automergerorder(){
            orderService.automergerorder();


            return "success";
        }

}
