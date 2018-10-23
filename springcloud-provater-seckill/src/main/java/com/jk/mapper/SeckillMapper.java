package com.jk.mapper;

import com.jk.model.RegionBean;
import com.jk.model.SeckilCommodity;
import com.jk.model.SeckillTimeBean;
import com.jk.model.TimeLimitSeckill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王超杰111
 * @date 2018/10/16
 * @Description:
 */
public interface SeckillMapper {
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
}