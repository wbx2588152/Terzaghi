package com.jk.controller.comm;

import com.jk.model.Comm;
import com.jk.model.Detail;
import com.jk.model.Food;
import com.jk.model.Man;
import com.jk.service.CommServiceApi;
import com.netflix.ribbon.proxy.annotation.Http;
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

    /**
     *
     * 查询商品详情
     */
    @RequestMapping("getDetail2")
    @ResponseBody
    public Detail getDetail2(String id){
        return commServiceApi.getDetail2(id);
    }

    /**
     *
     * 跳转男装页面
     */
    @RequestMapping("toMan")
    public String toMan(){
        return "man/manlist";
    }

    /**
     *
     * 查询男装列表
     */
    @RequestMapping("getMan")
    @ResponseBody
    public List<Man> getMan(){
        List<Man> manlist = commServiceApi.getMan();
        return manlist;
    }


    /**
     *
     * 跳转男装详情页面
     */
    @RequestMapping("toManDetail")
    public String toManDetail(String id, HttpServletRequest request){
        request.setAttribute("id",id);
        return "man/detailman";
    }

    /**
     *
     * 跳转食品页面
     */
    @RequestMapping("toFood")
    public String toFood(){
        return "food/foodlist";
    }

    /**
     *
     * 查询食品列表
     */
    @RequestMapping("getFood")
    @ResponseBody
    public List<Food> getFood(){
        List<Food> foodlist = commServiceApi.getFood();
        return foodlist;
    }

    @RequestMapping("toFoodDetail")
    public String toFoodDetail(String id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "food/detailfood";
    }
}
