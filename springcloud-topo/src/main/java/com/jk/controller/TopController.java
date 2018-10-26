package com.jk.controller;

import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import com.jk.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TopController {
    @Autowired
    private TopService topService;

    @GetMapping("topo/userList2")
    public String userList2() {
        return "/topo/aaa";
    }

    @GetMapping("topo/userList1")
    public String userList1() {
        return "/topo/userListPage";
    }

    @RequestMapping("topo/getUserCount")
    @ResponseBody
    public int getUserCount(){
        return topService.getUserCount();
    }
    @RequestMapping("topo/getUserList")
    @ResponseBody
    public List<StockBean> getUserList(String name, int page, int limit){
        return topService.getUserList(name,page,limit);
    }

    @RequestMapping("topo/getlunpanCount")
    @ResponseBody
    public int getlunpanCount(){
        return topService.getlunpanCount();
    }
    @RequestMapping("topo/getLunpanList")
    @ResponseBody
    public List<ProbabilityBean> getLunpanList(String name, int page, int limit){
        return topService.getLunpanList(name,page,limit);
    }

    /**
     *
     * @param stockid
     * @return
     */
    @RequestMapping("/topo/delete")
    @ResponseBody
    public Integer delete(@RequestParam(value="stockid")Integer stockid) {
        int num = topService.delete(stockid);
        return num;
    }

    @GetMapping("topo/tozhuan")
    public String tozhuan() {
        return "/zhuanpan/demo1";
    }


    @RequestMapping("/topo/suiji")
    @ResponseBody
     public   List<ProbabilityBean> suiji(){
      List<ProbabilityBean> userList = topService.suiji();
        return userList;
    }

    /**
     * 去修改界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/topo/toUpdate")
    @ResponseBody
    public ProbabilityBean toUpdate(Integer id, Model model) {
        ProbabilityBean user = topService.getUserById(id);
        return  user ;
    }

    @RequestMapping("/topo/userUpdate")
    @ResponseBody
    public Integer  userUpdate(String  id,String name,String probability){
        ProbabilityBean probabilityBean = new ProbabilityBean();
        probabilityBean.setId(id);
        probabilityBean.setName(name);
        probabilityBean.setProbability(probability);
        int num = topService.update(probabilityBean);
        return num;
    }

}

