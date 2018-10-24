package com.jk.mapper.coupon;

import com.jk.model.Coupon;
import com.jk.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouMapper {
    long queryComm(Coupon coupon);

    List<Coupon> queryCoomList(@Param("st") int start, @Param("end") int end, Coupon coupon);

    void deleteCoupon(@Param("id") String id);

    void addCoupon(Coupon coupon);

    Coupon queryCouById(@Param("id")String id);

    void updateCou(Coupon coupon);

    User getUserInfo(User user);

    void regUser(@Param("u") User user);

    User queryUserInfoByPhone(@Param("u") User user);
}
