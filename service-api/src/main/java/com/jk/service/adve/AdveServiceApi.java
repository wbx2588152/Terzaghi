package com.jk.service.adve;

import com.jk.model.Adve;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AdveServiceApi {

    @RequestMapping(value="adve/queryDvre",method = RequestMethod.POST)
    Map<String, Object> queryDvrelist(@RequestParam(value="page")int page, @RequestParam(value="rows")int limit, @RequestBody Adve adve);

    @RequestMapping(value="adve/delAdve",method = RequestMethod.GET)
    void delAdve(@RequestParam(value = "id")String id);

    @RequestMapping(value="adve/saveAdve",method = RequestMethod.POST)
    void saveAdve(@RequestBody Adve adve);

    @RequestMapping(value="adve/queryAdveById",method = RequestMethod.GET)
    Adve queryAdveById(@RequestParam(value = "id") String id);

    @RequestMapping(value="adve/updateAdve",method = RequestMethod.POST)
    void updateAdve(@RequestBody Adve adve);

    @RequestMapping(value="adve/queryAdveList",method = RequestMethod.POST)
    List<Adve> queryAdveList(@RequestBody Adve adve);
}