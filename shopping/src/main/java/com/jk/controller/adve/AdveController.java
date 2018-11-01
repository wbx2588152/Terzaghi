package com.jk.controller.adve;

import com.jk.model.Adve;
import com.jk.service.adve.AdveServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adve")
public class AdveController {

    @Value("${server.port}")
    String port;

      @Autowired
    private AdveServiceApi adveServiceApi;

      //查询广告管理
    @RequestMapping(value = "queryAdveList",method = RequestMethod.POST)
    @ResponseBody
    public List<Adve> queryAdveList(Adve adve){
        List<Adve> adveList  = adveServiceApi.queryAdveList(adve);
        return adveList;
    }


}
