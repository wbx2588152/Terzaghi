package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王超杰111
 * @date 2018/10/16
 * @Description:
 */
public interface SeckillMapper  {
    int querySeckillCount(@Param("c") SeckilCommodity seckilCommodity);

    List<SeckilCommodity> querySeckillList(@Param("start")int start, @Param("rows")int rows, @Param("c")SeckilCommodity seckilCommodity);

    int querySeckilTimeCount(@Param("s")SeckillTimeBean seckillTimeBean);

    List<SeckillTimeBean> querySeckilTimeList(@Param("start")int start, @Param("rows")int rows, @Param("s")SeckillTimeBean seckillTimeBean);

    void deleteSeckillTimeById(@Param("id")String id);

    void addSeckillTime(@Param("s")SeckillTimeBean seckillTimeBean);

    void updateSeckillTime(@Param("s")SeckillTimeBean seckillTimeBean);

    void deleteCommodityById(@Param("id")String id);

    List<SeckilCommodity> querySeckillCommodityList();

    List<SeckilCommodity> queryTimeLimitSeckillList();

    int queryTimeLimitCount(@Param("t")TimeLimitSeckill timeLimitSeckill);

    List<TimeLimitSeckill> queryTimeLimitLists(@Param("start")int start, @Param("rows")int rows, @Param("t")TimeLimitSeckill timeLimitSeckill);

    TimeLimitSeckill queryDaoJiShi(@Param("id")String id);

    List<RegionBean> queryRegionList(@Param("userId")String userId);

    void addRegion(@Param("r")RegionBean regionBean);

    void deleteRegion(@Param("id")String id);

    void addCommmondityInfo(@Param("id")String id, @Param("name")String name, @Param("artNo")String artNo, @Param("seckillPrice")String seckillPrice, @Param("commmondityImg")String commmondityImg);

    void addOrderInfo(@Param("o")OrderBean orderBean);

    OrderBean queryOrderById(@Param("o")OrderBean orderBean);

    void updateOrderStatus(@Param("o")OrderBean orders);

    void deleteTimeLimit(@Param("id")String id);

    SeckilCommodity querySeckillComInfoById(@Param("id")String id);

    void deleteComInfoById(@Param("id")String id);

    void updateCommInfo(@Param("id")String seckillId);

    void updateTImeLimitById(@Param("id")String seckillId);
}