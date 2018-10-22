package com.jk.service;

import com.jk.mapper.SeckillMapper;
import com.jk.model.SeckilCommodity;
import com.jk.model.SeckillTimeBean;
import com.jk.model.TimeLimitSeckill;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Object> querySeckillList(int page, int rows, SeckilCommodity seckilCommodity) {
        int start = (page-1)*rows;
        int count = seckillMapper.querySeckillCount(seckilCommodity);
        List<SeckilCommodity> seckilCommodities = seckillMapper.querySeckillList(start,rows, seckilCommodity);
        Map<String , Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows", seckilCommodities);
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

    @Override
    public void deleteSeckillTimeById(String id) {
        seckillMapper.deleteSeckillTimeById(id);
    }

    @Override
    public void addSeckillTime(SeckillTimeBean seckillTimeBean) {
        seckillMapper.addSeckillTime(seckillTimeBean);
    }

    @Override
    public void updateSeckillTime(SeckillTimeBean seckillTimeBean) {
        seckillMapper.updateSeckillTime(seckillTimeBean);
    }

    @Override
    public void deleteCommodityById(String id) {
        seckillMapper.deleteCommodityById(id);
    }

    @Override
    public List<SeckilCommodity> querySeckillCommodityList() {
        return seckillMapper.querySeckillCommodityList();
    }

    @Override
    public List<SeckilCommodity> queryTimeLimitSeckillList() {
        return seckillMapper.queryTimeLimitSeckillList();
    }

    @Override
    public Map<String, Object> queryTimeLimitLists(int page, int rows, TimeLimitSeckill timeLimitSeckill) {
        int start = (page-1)*rows;
        int count = seckillMapper.queryTimeLimitCount(timeLimitSeckill);
        List<SeckillTimeBean> seckillLists = seckillMapper.queryTimeLimitLists(start,rows,timeLimitSeckill);
        Map<String , Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",seckillLists);
        return map;
    }


}
