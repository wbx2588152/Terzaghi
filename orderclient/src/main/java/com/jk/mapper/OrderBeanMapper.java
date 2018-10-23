package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderBeanMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(OrderBean record);

    int insertSelective(OrderBean record);

    OrderBean selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(OrderBean record);

    int updateByPrimaryKey(OrderBean record);


    long queryordercount(@Param("orderBean") OrderBean orderBean);


    List<OrderBean> queryorderlist(@Param("st") int start, @Param("ed") Integer limit, @Param("orderBean") OrderBean orderBean);


    void delorderbyid(String orderid);


    void generateOrdeBean(OrderBean orderBean);

    OrderBean queryorder(String orderid);


    UserRecieverBean queryuserreciever(String linkbothid);

    RecieverBean queryreciever(String linkrecieverid);

    Comm queryComm(String linkcommodifyid);


    ExpressBean queryexpress(String orderid);


}
