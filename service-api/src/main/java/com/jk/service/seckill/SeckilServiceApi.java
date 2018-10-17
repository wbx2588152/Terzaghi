package com.jk.service.seckill;

import com.jk.model.CommodityBean;
import com.jk.model.SeckillTimeBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
public interface SeckilServiceApi {

    @RequestMapping(value="/querySeckillList",method = RequestMethod.POST)
    Map<String,Object> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody CommodityBean commodityBean);

    @RequestMapping(value="/querySeckilTime",method = RequestMethod.POST)
    Map<String,Object> querySeckilTime(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody SeckillTimeBean seckillTimeBean);
}
