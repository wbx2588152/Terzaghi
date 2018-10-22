package com.jk.controller.seckill;

import com.jk.model.SeckilCommodity;
import com.jk.model.TimeLimitSeckill;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author 王超杰
 * @date 2018/10/18
 * @Description:
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private SeckilServiceApi seckilServiceApi;

    /**
     * 查询限时秒杀列表的距离秒杀时间
     * */
    @RequestMapping("queryDaoJiShi")
    @ResponseBody
    public long queryDaoJiShi(String id) {
        TimeLimitSeckill timeLimitSeckill = seckilServiceApi.queryDaoJiShi(id);
        long addTime1 = timeLimitSeckill.getEndTime().getTime();
        Date date = new Date();
        long time = date.getTime();
        long time1 = (addTime1 - time) / 1000;
        return time1;
    }

    /**
     * 查询限量秒杀列表
     * */
    @RequestMapping("querySeckillCommodityList")
    @ResponseBody
    public List<SeckilCommodity> querySeckillCommodityList(){
        List<SeckilCommodity> list = seckilServiceApi.querySeckillCommodityList();
        return list;
    }

    /**
    /**
     * 查询限时秒杀列表
     * */
    @RequestMapping("queryTimeLimitSeckillList")
    @ResponseBody
    public List<SeckilCommodity> queryTimeLimitSeckillList(){
        List<SeckilCommodity> list = seckilServiceApi.queryTimeLimitSeckillList();
        return list;
    }

    /**
     * 跳转到秒杀页
     * */
    @RequestMapping("toSeckillList")
    public String toSeckillList(){
        return "seckillList";
    }


    /**
     * 查询秒杀列表
     * */
    @RequestMapping("querySeckillList")
    @ResponseBody
    public List<SeckilCommodity> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, SeckilCommodity seckilCommodity){
        Map<String , Object> map = seckilServiceApi.querySeckillList(page,rows, seckilCommodity);
        List<SeckilCommodity> seckilCommodities = (List<SeckilCommodity>) map.get("rows");
        System.out.println(seckilCommodities);
        return seckilCommodities;
    }


}
