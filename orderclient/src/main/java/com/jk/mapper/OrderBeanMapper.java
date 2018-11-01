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


    List<Comm> queryComm(String orderid);

    ExpressBean queryexpress(String orderid);


    RegionBean queryregion(String linkbothid);


    void insertexpress(ExpressBean expressBean);


    void updateorderBean(OrderBean orderBean);

    List<OrderBean> inselone();


    void insertCommOrder(CommOrderBean commOrderBean);


    void deleteExpressBean(String s);

    void deleteSubOrder(String s);


    List<String> queryCommOrder(String s);

    List<RegionBean> automergerorder();

    List<Comm> queryuserorder(String id);



}
