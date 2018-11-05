package com.jk.controller.seckill;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.Models;
import com.jk.model.SeckilCommodity;
import com.jk.model.SeckillTimeBean;
import com.jk.model.TimeLimitSeckill;
import com.jk.service.seckill.SeckilServiceApi;
import com.jk.util.AliyunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

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

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * OSS上传
     */
    @RequestMapping("uploadOneFileFood")
    @ResponseBody
    public Map<String, Object>  uploadFood(HttpServletRequest servletRequest,
                                           @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "seckill/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;
        } else {
            return null;
        }
    }

    /**
     * 新增限时秒杀数据
     * */
    @RequestMapping("saveTimeLimit")
    @ResponseBody
    public String saveTimeLimit(TimeLimitSeckill timeLimitSeckill){
        timeLimitSeckill.setId(UUID.randomUUID().toString().replace("-",""));
        timeLimitSeckill.setAddTime(new Date());
        timeLimitSeckill.setStatus("1");
        timeLimitSeckill.setLimitationCount(0);
        //在现在的时间后加前台传来的多少分钟
        Date date = new Date(System.currentTimeMillis() + (timeLimitSeckill.getTimeLimit() * 60 * 1000));
        timeLimitSeckill.setEndTime(date);
        seckilService.saveTimeLimit(timeLimitSeckill);
        redisTemplate.boundHashOps("seckillCommodityList").delete("1");
        redisTemplate.boundHashOps("timeLimitSeckillList").delete("1");
        return "{}";
    }


    /**
     *  跳转到新增限时秒杀页面
     * */
    @RequestMapping("toAddTimeLimit")
    public String toAddTimeLimit(){
        return "seckill/addTimeLimit";
    }

    /**
     *  跳转到限量秒杀页面
     * */
    @RequestMapping("toAddLimitSeckill")
    public String toAddLimitSeckill(){
        return "seckill/addLimitSeckill";
    }

    /**
     * 查询限量秒杀列表
     * */
    @RequestMapping("querySeckillCommodityList")
    @ResponseBody
    public String querySeckillList(int page, int limit, SeckilCommodity seckilCommodity){

        Map<String,Object> map=new HashMap<>();
        map = seckilService.queryLimitSeckills(page,limit,seckilCommodity);
        List<Models> list = (List<Models>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
        // JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        // 前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);
        return obj.toString();
    }

    /**
     * 查询限时秒杀列表
     * */
    @RequestMapping("queryTimeLimitSeckills")
    @ResponseBody
    public String queryTimeLimitSeckills(int page, int limit, TimeLimitSeckill timeLimitSeckill){

        Map<String,Object> map=new HashMap<>();
        map = seckilService.queryTimeLimitSeckills(page,limit,timeLimitSeckill);
        List<Models> list = (List<Models>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
        // JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        // 前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);
        return obj.toString();
    }

    /**
     *------
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
        redisTemplate.boundHashOps("seckillCommodityList").delete("1");
        redisTemplate.boundHashOps("timeLimitSeckillList").delete("1");
        return "{}";
    }

    /**
     * 删除限时秒杀数据
     * */
    @RequestMapping("deleteSeckillTimeById")
    @ResponseBody
    public String deleteSeckillTimeById(@RequestParam(value = "id")String id){
        seckilService.deleteSeckillTimeById(id);
        redisTemplate.boundHashOps("seckillCommodityList").delete("1");
        redisTemplate.boundHashOps("timeLimitSeckillList").delete("1");
        return "{}";
    }


    /**
     * 跳转到限时秒杀列表
     * */
    @RequestMapping("toTimeLimitList")
    public String toTimeLimitList(){
        return "seckill/timeLimitList";
    }

    /**
     *  跳转到限量秒杀列表
     * */
    @RequestMapping("toLimitSeckillList")
    public String toLimitSeckillList(){
        return "seckill/limitSeckillList";
    }


    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



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
        redisTemplate.boundHashOps("seckillCommodityList").delete("1");
        redisTemplate.boundHashOps("timeLimitSeckillList").delete("1");
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
