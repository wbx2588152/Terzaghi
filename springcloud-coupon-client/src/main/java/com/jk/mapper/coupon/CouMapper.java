package com.jk.mapper.coupon;

import com.jk.model.Coupon;
import com.jk.model.User;
import com.jk.model.UserCou;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouMapper {
    long queryComm(Coupon coupon);

    List<Coupon> queryCoomList(@Param("st") int start, @Param("end") int end, Coupon coupon);

    void deleteCoupon(@Param("id") String id);



    Coupon queryCouById(@Param("id")String id);

    void updateCou(Coupon coupon);

    User getUserInfo(User user);

    void regUser(@Param("u") User user);

    User queryUserInfoByPhone(@Param("u") User user);

    List<Coupon> queryCouList(Coupon coupon);

    void addCouByUser(UserCou userCou);

    void addCoupon(List<Coupon> list);


    List<UserCou> queryUserCou(@Param("id") String id);

    void updateCouCount(@Param("id") String couId);
}
