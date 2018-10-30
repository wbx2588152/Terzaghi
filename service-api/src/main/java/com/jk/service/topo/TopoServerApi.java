package com.jk.service.topo;

import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import com.jk.util.LayuiData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 王嘟嘟
 * @Date: 2018/10/18 09:41
 * @Description:
 */
public interface TopoServerApi {

    @RequestMapping(value="topo/getUserList")
    List<StockBean> getUserList(@RequestParam(value="name")String name, @RequestParam(value="page")int page, @RequestParam(value="limit") int limit);

    @RequestMapping(value="topo/getUserCount")
    int getUserCount();

    @RequestMapping(value="topo/delete")
    int delete(@RequestParam(value="stockid")Integer stockid);

    @RequestMapping(value = "/topo/suiji")
    List<ProbabilityBean> suiji();

    @RequestMapping(value="topo/getlunpanCount")
    int getlunpanCount();

    @RequestMapping(value="topo/getLunpanList")
    List<ProbabilityBean> getLunpanList(@RequestParam(value="name")String name,@RequestParam(value="page") int page,@RequestParam(value="limit")  int limit);

    @RequestMapping(value = "/topo/toUpdate")
    ProbabilityBean getUserById(@RequestParam(value="id")Integer id);

    @RequestMapping(value = "/topo/userUpdate")
     String update(@RequestParam(value="id")String id,@RequestParam(value="name") String name, @RequestParam(value="probability")String probability);




}
