package com.jk.controller.adve;

import com.jk.model.Adve;
import com.jk.model.Coupon;
import com.jk.model.Resore;
import com.jk.service.adve.AdveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("adve")
public class AdveController {

    @Value("${server.port}")
    String port;

    @Autowired
    private AdveService adveService;

    @RequestMapping(value = "queryDvre",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryDvrelist (@RequestParam(value="page")  int page, @RequestParam("rows") int limit, @RequestBody Adve adve) {
        Map<String, Object> map = new HashMap<>();
        map = adveService.queryDvrelist(page, limit, adve);
        return map;

    }

    @RequestMapping(value = "delAdve",method = RequestMethod.GET)
    @ResponseBody
    public void delAdve(@RequestParam(value = "id")String id){
        adveService.delAdve(id);

    }

    //新增优惠券
    @RequestMapping(value = "saveAdve",method = RequestMethod.POST)
    @ResponseBody
    public void  saveAdve(@RequestBody Adve adve){
        adve.setId(UUID.randomUUID().toString().replaceAll("-",""));
        adveService.saveAdve(adve);

    }

    @RequestMapping(value = "queryAdveById",method = RequestMethod.GET)
    @ResponseBody
    public Adve  queryAdveById(@RequestParam(value="id")String id){
        Adve adve = adveService.queryAdveById(id);
        return adve;
    }

    @RequestMapping(value = "updateAdve",method = RequestMethod.POST)
    @ResponseBody
    public void  updateAdve(@RequestBody Adve adve){
        adveService.updateAdve(adve);
    }


    @RequestMapping(value = "queryAdveList",method = RequestMethod.POST)
    @ResponseBody
    public List<Adve> queryAdveList(Adve adve){
        List<Adve> adveList = adveService.queryAdveList(adve);
        return adveList;
    }

    @RequestMapping(value = "queryReslist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryReslist (@RequestParam(value="page")  int page, @RequestParam("rows") int limit, @RequestBody Resore res) {
        Map<String, Object> map = new HashMap<>();
        map = adveService.queryReslist(page, limit, res);
        return map;

    }


    @RequestMapping(value = "saveRes",method = RequestMethod.POST)
    @ResponseBody
    public void  saveRes(@RequestBody Resore res){
        res.setId(UUID.randomUUID().toString().replaceAll("-",""));
        adveService.saveRes(res);

    }

    @RequestMapping(value = "delRes",method = RequestMethod.GET)
    @ResponseBody
    public void delRes(@RequestParam(value = "id")String id){
        adveService.delRes(id);

    }

    @RequestMapping(value = "queryResById",method = RequestMethod.GET)
    @ResponseBody
    public Resore  queryResById(@RequestParam(value="id")String id){
        Resore res = adveService.queryResById(id);
        return res;
    }

    @RequestMapping(value = "updateRes",method = RequestMethod.POST)
    @ResponseBody
    public void  updateRes(@RequestBody Resore res){
        adveService.updateRes(res);
    }


    @RequestMapping(value = "queryRes",method = RequestMethod.POST)
    @ResponseBody
    public List<Resore> queryRes(Resore res){
        List<Resore> reslist = adveService.queryRes(res);
        return reslist;
    }
}
