package com.jk.controller.adve;

import com.jk.model.Adve;
import com.jk.model.Coupon;
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


}
