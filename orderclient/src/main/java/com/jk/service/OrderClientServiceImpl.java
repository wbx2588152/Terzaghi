package com.jk.service;

import com.jk.mapper.OrderBeanMapper;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void mergerorders(String mainorder, String subarr) {
        String[] split = subarr.split(",");
        for (int i = 0; i <split.length ; i++) {
           List<String> comArr= orderDao.queryCommOrder(split[i]);
            for (int j = 0; j < comArr.size() ; j++) {
                CommOrderBean commOrderBean = new CommOrderBean();
                commOrderBean.setId(UUID.randomUUID().toString().replaceAll("-",""));
                commOrderBean.setCommid(comArr.get(j));
                commOrderBean.setOrderid(mainorder);
                orderDao.insertCommOrder(commOrderBean);
            }

            //删除对应的物流信息
           orderDao.deleteExpressBean(split[i]);
           //删除订单信息
            orderDao.deleteSubOrder(split[i]);
        }
        //orderDao.mergerorders(mainorder,subarr);
    }


}
