package com.jk.service;

import com.jk.mapper.OrderBeanMapper;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        UserRecieverBean userRecieverBean=orderDao.queryuserreciever(orderBean.getLinkbothid());
        RecieverBean recieverBean= orderDao.queryreciever(userRecieverBean.getLinkrecieverid());
        Comm commBean = orderDao.queryComm(orderBean.getLinkcommodifyid());
        ExpressBean expressBean= orderDao.queryexpress(orderid);
        Map<String,Object> map = new HashMap<>();
        map.put("orderBean",orderBean);
        map.put("recieverBean",recieverBean);
        map.put("commBean",commBean);
        map.put("expressBean",expressBean);
        return map;
    }


}
