package com.jk.service.coupon;

import com.jk.mapper.coupon.CouMapper;
import com.jk.model.Coupon;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouServiceImpl implements  CouService{

    @Autowired
    private CouMapper couMapper;


    @Override
    public Map<String, Object> querycouponlist(int page, int rows, Coupon coupon) {
            HashMap<String, Object> map = new HashMap<>();
            long count = couMapper.queryComm(coupon);
            int start = (page - 1)*rows;
            int end = start + rows;
            List<Coupon> commList = couMapper.queryCoomList(start,end,coupon);
            map.put("total",count);
            map.put("rows",commList);
            return map;

    }

    @Override
    public void deleteCoupon(String id) {
        couMapper.deleteCoupon(id);
    }

    @Override
    public void addCoupon(Coupon coupon) {
        couMapper.addCoupon(coupon);
    }

    @Override
    public Coupon queryCouById(String id) {
        return  couMapper.queryCouById(id);
    }

    @Override
    public void updateCou(Coupon coupon) {
        couMapper.updateCou(coupon);
    }

    @Override
    public User getUserInfo(User user) {
        return couMapper.getUserInfo(user);
    }

    @Override
    public void regUser(User user) {
               couMapper.regUser(user);
    }

    @Override
    public User queryUserInfoByPhone(User user) {
        return couMapper.queryUserInfoByPhone(user);

    }
}
