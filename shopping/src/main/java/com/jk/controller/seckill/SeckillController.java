package com.jk.controller.seckill;

import com.jk.model.SeckilCommodity;
import com.jk.service.seckill.SeckilServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("aa")
    @ResponseBody
    public String aa() throws ParseException {
        Date x= new Date();
        SimpleDateFormat dft = new SimpleDateFormat("MMM d HH:mm:ss yyyy", Locale.ENGLISH);
        String c = dft.format(x);
        System.out.println(c);
        return c;
    }

    /**
     * 查询限量秒杀列表
     * */
    @RequestMapping("querySeckillCommodityList")
    @ResponseBody
    public List<SeckilCommodity> querySeckillCommodityList(){
        List<SeckilCommodity> list = seckilServiceApi.querySeckillCommodityList();
        System.out.println(list);
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
        System.out.println(list);
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
