package com.jk.service;

import com.jk.mapper.OrderBeanMapper;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderClientServiceImpl implements OrderClientService {


    @Autowired
    private OrderBeanMapper orderDao;


    @Override
    public Map<String, Object> queryorderlist(Integer page, Integer limit, OrderBean orderBean) {

        Map<String,Object> map=new HashMap<>();
        long count=orderDao.queryordercount(orderBean);
        int start=(page-1)*limit;
        List<OrderBean> list=orderDao.queryorderlist(start,limit,orderBean);
        map.put("data",list);
        map.put("count",count);
        return map;
    }

    @Override
    public void delorderbyid(String orderid) {
        orderDao.delorderbyid(orderid);
    }

    @Override
    public void generateOrdeBean(OrderBean orderBean) {
        orderDao.generateOrdeBean(orderBean);
    }

    @Override
    public Map<String, Object> queryorderalldata(String orderid) {
        OrderBean orderBean = orderDao.queryorder(orderid);
        RegionBean regionBean=orderDao.queryregion(orderBean.getLinkbothid());

        List<Comm> commList = orderDao.queryComm(orderid);

        ExpressBean expressBean= orderDao.queryexpress(orderid);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderBean",orderBean);
        map.put("regionBean",regionBean);
        map.put("commList",commList);
        map.put("expressBean",expressBean);
        return map;
    }

    @Override
    public void savedeliverdata(String consignnum, String expresscompany, String orderid) {
        Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
        if (uuidHashCode < 0) {
            uuidHashCode = uuidHashCode * (-1);
        }
        int node= 1000+ (int)(Math.random()*9000);

        ExpressBean expressBean = new ExpressBean();
        expressBean.setExpressid(UUID.randomUUID().toString().replaceAll("-",""));
        expressBean.setExpresscompany(expresscompany);
        expressBean.setLinkorderid(orderid);
        expressBean.setExpressqueue(String.valueOf(uuidHashCode+node));
        orderDao.insertexpress(expressBean);
        //订单更改状态
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderid(orderid);
        orderBean.setSubtime(new Date());
        orderBean.setConsignnum(consignnum);
        orderBean.setOrderstatus("3");
        orderDao.updateorderBean(orderBean);
    }

    @Override
    public List<OrderBean> inselone() {


        return  orderDao.inselone();

    }




    @Override
    public void automergerorder() {
        /* List<RegionBean> list= orderDao.automergerorder();*/
        List<String> arrayList = new ArrayList<>();
        List<OrderBean> list1 = orderDao.queryallorder();
        for (int l = 0; l < list1.size()-1; l++) {
            for (int m = 1+l; m < list1.size(); m++) {
                OrderBean orderBean = list1.get(l);
                OrderBean orderBean1 = list1.get(m);
                if(orderBean.getOrderstatus().equals("2") && orderBean1.getOrderstatus().equals("2")){
                    Date subtime = orderBean.getSubtime();
                    Date subtime1 = orderBean1.getSubtime();

                    System.out.println(subtime);
                    System.out.println(subtime1);
                    if (subtime.equals(subtime1)){

                        arrayList = submethod(orderBean, orderBean1);
                    }
                   /* System.out.println(subtime.compareTo(subtime1));
                    if (subtime.compareTo(subtime1)==0){//相等返回0，大于返回1，小于返回-1.
                        System.out.println();

                    }*/
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {

            /*//删除对应的物流信息
            orderDao.deleteExpressBean(arrayList.get(i));*/
            //删除订单信息
            orderDao.deleteSubOrder(arrayList.get(i));

            orderDao.deleteCommOder(arrayList.get(i));
        }


    }




    public   List<String>  submethod(OrderBean orderBean, OrderBean orderBean1){

        List<String> arrayList = new ArrayList<>();

        RegionBean regionBean1=orderDao.queryregion1(orderBean.getLinkbothid());
        RegionBean regionBean2=orderDao.queryregion2(orderBean.getLinkbothid());

        String reciever1=regionBean1.getName();
        String reciever2=regionBean2.getName();
        String address1=regionBean1.getInDetail();
        String address2=regionBean2.getInDetail();
        if(reciever1.equals(reciever2) &&  address1.equals(address2)){
            List<String> comArr= orderDao.queryCommOrder(orderBean1.getOrderid());
            for (int k = 0; k < comArr.size() ; k++) {
                CommOrderBean commOrderBean = new CommOrderBean();
                commOrderBean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                commOrderBean.setCommid(comArr.get(k));
                commOrderBean.setOrderid(orderBean.getOrderid());
                orderDao.insertCommOrder(commOrderBean);
            }
            if(arrayList.contains(orderBean1.getOrderid())){


            }else{
                arrayList.add(orderBean1.getOrderid());
            }
        }
        return arrayList;
    }



    @Override
    public Map<String, Object> queryuserorder(String id) {
        List<OrderBean> list=orderDao.queryuserorder(id);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("list",list);
        return map;
    }

    @Override
    public OrderBean queryRegionAgain(String id, String orderid) {
        return   orderDao.queryRegionAgain(id,orderid);
    }

    @Override
    public void updateorderstatus(String orderid) {
        orderDao.updateorderstatus(orderid);
    }


}
