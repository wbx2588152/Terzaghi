package com.jk.controller.seckill;

import com.jk.model.CommodityBean;
import com.jk.model.SeckillTimeBean;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:秒杀后台
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private SeckilServiceApi seckilService;


    /**
     * 跳转到秒杀活动列表
     * */
    @RequestMapping("toSeckillList")
    public String toSeckillList(){
        return "seckill/seckillList";
    }

    /**
     * 跳转到秒杀活动段设置   设置商品
     * */
    @RequestMapping("toSeckillTime")
    public String toSeckillTime(){
        return "seckill/seckillTime";
    }

    /**
     * 查询秒杀列表
     * */
    @RequestMapping("querySeckillList")
    @ResponseBody
    public Map<String , Object> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, CommodityBean commodityBean){
        Map<String , Object> map = seckilService.querySeckillList(page,rows,commodityBean);
        return map;
    }

    /**
     * 查询时间段列表
     * */
    @RequestMapping("querySeckilTime")
    @ResponseBody
    public Map<String , Object> querySeckilTime(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, SeckillTimeBean seckillTimeBean){
        Map<String , Object> map = seckilService.querySeckilTime(page,rows,seckillTimeBean);
        return map;
    }

}
