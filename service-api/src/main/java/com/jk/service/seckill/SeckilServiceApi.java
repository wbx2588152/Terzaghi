package com.jk.service.seckill;

import com.jk.model.SeckilCommodity;
import com.jk.model.SeckillTimeBean;
import com.jk.model.TimeLimitSeckill;
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
}
