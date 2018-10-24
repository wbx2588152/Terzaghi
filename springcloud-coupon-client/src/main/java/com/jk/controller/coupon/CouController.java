package com.jk.controller.coupon;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.Coupon;
import com.jk.model.User;
import com.jk.service.coupon.CouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("cou")
public class CouController {

    @Value("${server.port}")
    String port;

    @Autowired
    private CouService couService;


    /*@RequestMapping(value = "querycouponPage",method = RequestMethod.POST)
    @ResponseBody
    public String querycouponPage(@RequestParam(value="page") int page, @RequestParam(value="rows") int rows, @RequestBody Coupon coupon){
        Map<String,Object> map=couService.querycouponlist(page,rows,coupon);
        List<Coupon> list = (List<Coupon>) map.get("data");
        int count= (int) map.get("count");
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);
        return obj.toString();
    }*/

    @RequestMapping(value = "querycouponPage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> querycouponPage (@RequestParam(value="page")  int page, @RequestParam("rows") int limit, @RequestBody Coupon coupon) {
        Map<String, Object> map = new HashMap<>();
        map = couService.querycouponlist(page, limit, coupon);
           return map;

    }

    //删除优惠券
    @RequestMapping(value="deleteCoupon",method = RequestMethod.GET)
    @ResponseBody
    public void deleteCoupon(@RequestParam(value="id") String id){
        couService.deleteCoupon(id);
    }


    //新增优惠券
    @RequestMapping(value = "addCoupon",method = RequestMethod.POST)
    @ResponseBody
    public void  addCoupon(@RequestBody Coupon coupon){
        coupon.setId(UUID.randomUUID().toString().replaceAll("-",""));
        couService.addCoupon(coupon);

    }

    @RequestMapping(value = "queryCouById",method = RequestMethod.GET)
    @ResponseBody
    public Coupon  queryCouById(@RequestParam(value="id")String id){
        Coupon coupon = couService.queryCouById(id);
        return coupon;
    }

    @RequestMapping(value = "updateCou",method = RequestMethod.POST)
    @ResponseBody
    public void  updateCou(@RequestBody Coupon coupon){
        couService.updateCou(coupon);
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public User  getUserInfo(@RequestBody User user){
        User userInfo = couService.getUserInfo(user);
        return userInfo;
    }

    //新增用户信息
    @RequestMapping(value = "regUser",method = RequestMethod.POST)
    @ResponseBody
    public void  regUser(@RequestBody User user){
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        couService.regUser(user);

    }


    @RequestMapping(value = "queryUserInfoByPhone",method = RequestMethod.POST)
    @ResponseBody
    public User  queryUserInfoByPhone(@RequestBody User user){
        User userInfo = couService.queryUserInfoByPhone(user);
        return userInfo;
    }




}


