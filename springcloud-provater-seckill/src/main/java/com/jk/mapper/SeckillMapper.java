package com.jk.mapper;

import com.jk.model.CommodityBean;
import com.jk.model.SeckillTimeBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
public interface SeckillMapper {
    int querySeckillCount(@Param("c") CommodityBean commodityBean);

    List<CommodityBean> querySeckillList(@Param("start")int start, @Param("rows")int rows, @Param("c")CommodityBean commodityBean);

    int querySeckilTimeCount(@Param("s")SeckillTimeBean seckillTimeBean);

    List<SeckillTimeBean> querySeckilTimeList(@Param("start")int start, @Param("rows")int rows, @Param("s")SeckillTimeBean seckillTimeBean);
}
