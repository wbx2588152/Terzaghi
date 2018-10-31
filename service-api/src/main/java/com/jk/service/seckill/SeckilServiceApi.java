package com.jk.service.seckill;

import com.jk.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
public interface SeckilServiceApi {

    @RequestMapping(value="/querySeckillList",method = RequestMethod.POST)
    Map<String,Object> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody SeckilCommodity seckilCommodity);

    @RequestMapping(value="/querySeckilTime",method = RequestMethod.POST)
    Map<String,Object> querySeckilTime(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody SeckillTimeBean seckillTimeBean);

    @RequestMapping(value="/deleteSeckillTimeById",method = RequestMethod.POST)
    void deleteSeckillTimeById(@RequestParam(value = "id")String id);

    @RequestMapping(value="/addSeckillTime",method = RequestMethod.POST)
    void addSeckillTime(@RequestBody SeckillTimeBean seckillTimeBean);

    @RequestMapping(value="/updateSeckillTime",method = RequestMethod.POST)
    void updateSeckillTime(@RequestBody SeckillTimeBean seckillTimeBean);

    @RequestMapping(value="/deleteCommodityById",method = RequestMethod.POST)
    void deleteCommodityById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/querySeckillCommodityList",method = RequestMethod.POST)
    List<SeckilCommodity> querySeckillCommodityList();

    @RequestMapping(value = "/queryTimeLimitSeckillList",method = RequestMethod.POST)
    List<SeckilCommodity> queryTimeLimitSeckillList();

    @RequestMapping(value = "/queryTimeLimitLists",method = RequestMethod.POST)
    Map<String,Object> queryTimeLimitLists(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody TimeLimitSeckill timeLimitSeckill);

    @RequestMapping(value = "/queryDaoJiShi",method = RequestMethod.POST)
    TimeLimitSeckill queryDaoJiShi(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/queryRegionList",method = RequestMethod.POST)
    List<RegionBean> queryRegionList(@RequestParam(value = "userId")String userId);

    @RequestMapping(value = "/addRegion",method = RequestMethod.POST)
    void addRegion(@RequestBody RegionBean regionBean);

    @RequestMapping(value = "/deleteRegion",method = RequestMethod.POST)
    void deleteRegion(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/addCommmondityInfo",method = RequestMethod.POST)
    void addCommmondityInfo(@RequestParam(value = "id")String id, @RequestParam(value = "name")String name, @RequestParam(value = "artNo")String artNo, @RequestParam(value = "seckillPrice")String seckillPrice, @RequestParam(value = "commmondityImg")String commmondityImg);

    @RequestMapping(value = "/addOrderInfo",method = RequestMethod.POST)
    void addOrderInfo(@RequestBody OrderBean orderBean);

    @RequestMapping(value = "/queryOrderById",method = RequestMethod.POST)
    OrderBean queryOrderById(@RequestBody OrderBean orderBean);

    @RequestMapping(value = "/updateOrderStatus",method = RequestMethod.POST)
    void updateOrderStatus(@RequestBody OrderBean orderBean);

    @RequestMapping(value = "/deleteTimeLimit",method = RequestMethod.POST)
    void deleteTimeLimit(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/querySeckillComInfoById",method = RequestMethod.POST)
    SeckilCommodity querySeckillComInfoById(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/deleteComInfoById",method = RequestMethod.POST)
    void deleteComInfoById(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/updateCommInfo",method = RequestMethod.POST)
    void updateCommInfo(@RequestParam(value = "seckillId")String seckillId);

    @RequestMapping(value = "/updateTImeLimitById",method = RequestMethod.POST)
    void updateTImeLimitById(@RequestParam(value = "seckillId")String seckillId);

    @RequestMapping(value = "/queryByCarsByIds",method = RequestMethod.POST)
    List<BuyCar> queryByCarsByIds(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/queryBuyCarById",method = RequestMethod.POST)
    BuyCar queryBuyCarById(@RequestParam(value = "id")String id);

    @RequestMapping(value = "/queryCommById",method = RequestMethod.POST)
    Comm queryCommById(@RequestParam("id")String id);
}
