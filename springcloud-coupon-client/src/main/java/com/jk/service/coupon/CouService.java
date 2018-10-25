package com.jk.service.coupon;

import com.jk.model.Coupon;
import com.jk.model.User;
import com.jk.model.UserCou;

import java.util.List;
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


    List<Coupon> queryCouList(Coupon coupon);

    void addCouByUser(UserCou userCou);

    List<UserCou> queryUserCou(String id);
}
