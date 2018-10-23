package com.jk.controller.seckill;

import com.jk.model.SeckilCommodity;
import com.jk.model.SeckillTimeBean;
import com.jk.model.TimeLimitSeckill;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

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
     * 查询限量秒杀列表
     * */
    @RequestMapping("querySeckillCommodityList")
    @ResponseBody
    public Map<String , Object> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, SeckilCommodity seckilCommodity){
        Map<String , Object> map = seckilService.querySeckillList(page,rows, seckilCommodity);
        return map;
    }

    /**
     * 查询时间段列表
     * */
    @RequestMapping("queryTimeLimitSeckillList")
    @ResponseBody
    public Map<String , Object> queryTimeLimitLists(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, TimeLimitSeckill timeLimitSeckill){
        Map<String , Object> map = seckilService.queryTimeLimitLists(page,rows,timeLimitSeckill);
        return map;
    }

    /**
     * 删除限量秒杀数据
     * */
    @RequestMapping("deleteCommodityById")
    @ResponseBody
    public String deleteCommodityById(@RequestParam(value = "id")String id){
        seckilService.deleteCommodityById(id);
        return "{}";
    }

    /**
     * 删除限时秒杀数据
     * */
    @RequestMapping("deleteSeckillTimeById")
    @ResponseBody
    public String deleteSeckillTimeById(@RequestParam(value = "id")String id){
        seckilService.deleteSeckillTimeById(id);
        return "{}";
    }


    /**
     * 跳转到限时秒杀列表
     * */
    @RequestMapping("toSeckillList")
    public String toSeckillList(){
        return "seckill/seckillList";
    }



    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * 跳转到秒杀活动段设置   设置商品
     * */
    @RequestMapping("toSeckillTime")
    public String toSeckillTime(){
        return "seckill/seckillTime";
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


    /**
     * 新增时间段表数据
     * */
    @RequestMapping("addSeckillTime")
    @ResponseBody
    public String addSeckillTime(SeckillTimeBean seckillTimeBean){
        seckillTimeBean.setId(UUID.randomUUID().toString().replace("-",""));
        System.out.println(seckillTimeBean.getId()+","+seckillTimeBean.getEndTime());
        seckilService.addSeckillTime(seckillTimeBean);
        return "{}";
    }

    /**
     * 修改时间段表数据
     * */
    @RequestMapping("updateSeckillTime")
    @ResponseBody
    public String updateSeckillTime(SeckillTimeBean seckillTimeBean){
        seckilService.updateSeckillTime(seckillTimeBean);
        return "{}";
    }




}
