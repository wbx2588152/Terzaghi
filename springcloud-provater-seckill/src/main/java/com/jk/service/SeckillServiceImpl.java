package com.jk.service;

import com.jk.mapper.SeckillMapper;
import com.jk.model.CommodityBean;
import com.jk.model.SeckillTimeBean;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
@RestController
public class SeckillServiceImpl implements SeckilServiceApi {

    @Autowired
    private SeckillMapper seckillMapper;


    @Override
    public Map<String, Object> querySeckillList(int page, int rows, CommodityBean commodityBean) {
        int start = (page-1)*rows;
        int count = seckillMapper.querySeckillCount(commodityBean);
        List<CommodityBean> commodityBeans = seckillMapper.querySeckillList(start,rows,commodityBean);
        Map<String , Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",commodityBeans);
        return map;
    }

    @Override
    public Map<String, Object> querySeckilTime(int page, int rows, SeckillTimeBean seckillTimeBean) {
        int start = (page-1)*rows;
        int count = seckillMapper.querySeckilTimeCount(seckillTimeBean);
        List<SeckillTimeBean> seckillLists = seckillMapper.querySeckilTimeList(start,rows,seckillTimeBean);
        Map<String , Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",seckillLists);
        return map;
    }
}
