package com.jk.service.coupon;

import com.jk.model.Coupon;
import com.jk.model.User;

import java.util.Map;

public interface CouService {


    Map<String, Object> querycouponlist(int page, int rows, Coupon coupon);

    void deleteCoupon(String id);

    void addCoupon(Coupon coupon);

    Coupon queryCouById(String id);

    void updateCou(Coupon coupon);

    User getUserInfo(User user);

    void regUser(User user);

    User queryUserInfoByPhone(User user);
}
