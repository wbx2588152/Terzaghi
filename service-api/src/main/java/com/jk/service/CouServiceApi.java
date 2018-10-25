package com.jk.service;

import com.jk.model.Coupon;
import com.jk.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface CouServiceApi {

    @RequestMapping(value="cou/querycouponPage",method = RequestMethod.POST)
    Map<String, Object> querycouponlist(@RequestParam(value="page") int page, @RequestParam(value="rows") int rows, @RequestBody Coupon coupon);

    @RequestMapping(value="cou/deleteCoupon",method = RequestMethod.GET)
    void deleteCoupon(@RequestParam(value = "id") String id);

    @RequestMapping(value="cou/addCoupon",method = RequestMethod.POST)
    void addCoupon(@RequestBody Coupon coupon);

    @RequestMapping(value="cou/queryCouById",method = RequestMethod.GET)
    Coupon queryCouById(@RequestParam(value = "id")String id);

    @RequestMapping(value="cou/updateCou",method = RequestMethod.POST)
    void updateCou(@RequestBody Coupon coupon);

    @RequestMapping(value="cou/login",method = RequestMethod.POST)
    User getUserInfo(@RequestBody User user);


    @RequestMapping(value="cou/queryUserInfoByPhone",method = RequestMethod.POST)
    User queryUserInfoByPhone(@RequestBody User user);

    @RequestMapping(value="cou/regUser",method = RequestMethod.POST)
    void regUser(@RequestBody User user);
}
