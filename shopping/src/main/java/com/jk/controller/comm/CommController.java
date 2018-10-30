package com.jk.controller.comm;

import com.jk.model.Comm;
import com.jk.service.CommServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("comm")
public class CommController {

    @Autowired
    private CommServiceApi commServiceApi;

    /**
     *
     * 查询商品列表
     */
    @RequestMapping("getComm")
    @ResponseBody
    public List<Comm> getComm(){
        List<Comm> commlist = commServiceApi.getComm();
        return commlist;
    }

    /**
     *
     * 查询商品详情
     */
    @RequestMapping("getDetail")
    @ResponseBody
    public Comm getDetail(String id){

        return commServiceApi.getDetail(id);
    }


    /**
     * 跳转商品主页
     */
    @RequestMapping("toComm")
    public String toComm(){

        return "comm/commlist";
    }


    /**
     * 跳转手机页面
     */
    @RequestMapping("toPhone")
    public String toPhone()
    {

        return "comm/phone";
    }

    /**
     * 跳转手机详情页面
     */
    @RequestMapping("toDetail")
    public String toDetail(String id, HttpServletRequest request){
        request.setAttribute("id",id);
        return "comm/detail";
    }
}
