package com.jk.service;

import com.jk.mapper.SeckillMapper;
import com.jk.model.*;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public TimeLimitSeckill queryDaoJiShi(String id) {
        return seckillMapper.queryDaoJiShi(id);
    }

    @Override
    public List<RegionBean> queryRegionList(String userId) {
        return seckillMapper.queryRegionList(userId);
    }

    @Override
    public void addRegion(@RequestBody RegionBean regionBean) {
        seckillMapper.addRegion(regionBean);
    }

    @Override
    public void deleteRegion(String id) {
        seckillMapper.deleteRegion(id);
    }

    @Override
    public Map<String, Object> queryTimeLimitLists(int page, int rows, TimeLimitSeckill timeLimitSeckill) {
        int start = (page-1)*rows;
        int count = seckillMapper.queryTimeLimitCount(timeLimitSeckill);
        List<TimeLimitSeckill> seckillLists = seckillMapper.queryTimeLimitLists(start,rows,timeLimitSeckill);
        Map<String , Object> map = new HashMap<>();
        map.put("total",count);
        map.put("rows",seckillLists);
        return map;
    }

    @Override
    public void addCommmondityInfo(String id, String name, String artNo, String seckillPrice, String commmondityImg) {
        seckillMapper.addCommmondityInfo( id,  name,  artNo,  seckillPrice,  commmondityImg);
    }

    @Override
    public void addOrderInfo(@RequestBody OrderBean orderBean) {
        seckillMapper.addOrderInfo(orderBean);
    }

    @Override
    public OrderBean queryOrderById(@RequestBody OrderBean orderBean) {
        return seckillMapper.queryOrderById(orderBean);
    }

    @Override
    public void updateOrderStatus(@RequestBody OrderBean orderBean) {
        seckillMapper.updateOrderStatus(orderBean);
    }

    @Override
    public void deleteTimeLimit(String id) {
        seckillMapper.deleteTimeLimit(id);
    }

    @Override
    public SeckilCommodity querySeckillComInfoById(String id) {
        return seckillMapper.querySeckillComInfoById(id);
    }

    @Override
    public void deleteComInfoById(String id) {
        seckillMapper.deleteComInfoById(id);
    }

    @Override
    public void updateCommInfo(String seckillId) {
        seckillMapper.updateCommInfo(seckillId);
    }

    @Override
    public void updateTImeLimitById(String seckillId) {
        seckillMapper.updateTImeLimitById(seckillId);
    }

    @Override
    public List<BuyCar> queryByCarsByIds(String ids) {
        String[] split = ids.split(",");
        ArrayList<BuyCar> arrayList = new ArrayList<>();
        for (int i =0;i<split.length;i++){
            String id = split[i];
            BuyCar buyCar = seckillMapper.queryBuyCarById(id);
            arrayList.add(buyCar);
        }
        return arrayList;
    }

    @Override
    public BuyCar queryBuyCarById(String id) {
        return seckillMapper.queryBuyCarById(id);
    }

    @Override
    public Comm queryCommById(String id) {
        return seckillMapper.queryCommById(id);
    }


}