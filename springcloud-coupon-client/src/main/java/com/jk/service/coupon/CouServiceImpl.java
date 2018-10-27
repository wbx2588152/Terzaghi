package com.jk.service.coupon;

import com.jk.mapper.coupon.CouMapper;
import com.jk.model.Coupon;
import com.jk.model.User;
import com.jk.model.UserCou;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public void addCoupon(Coupon coupon)
    {
        String count = coupon.getCount();
        int i1 = Integer.parseInt(count);
        List<Coupon> list = new ArrayList<Coupon>();
        for (int i = 0; i <i1; i++) {
            Coupon coupon1 = new Coupon();
            coupon1.setId(UUID.randomUUID().toString().replaceAll("-",""));
            coupon1.setMan(coupon.getMan());
            coupon1.setFixday(coupon.getFixday());
            coupon1.setPrice(coupon.getPrice());
           String sum =  (coupon.getPrice()+coupon.getMan());
            coupon1.setCount(sum);
            list.add(coupon1);
        }
        couMapper.addCoupon(list);
    }

    @Override
    public void regUser(User user) {

        couMapper.regUser(user);
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
    public User queryUserInfoByPhone(User user) {
        return couMapper.queryUserInfoByPhone(user);

    }

    @Override
    public List<Coupon> queryCouList(Coupon coupon) {
        return couMapper.queryCouList(coupon);
    }

    @Override
    public void addCouByUser(UserCou userCou) {
        couMapper.addCouByUser(userCou);
        couMapper.updateCouCount(userCou.getCouId());


    }

    @Override
    public List<UserCou> queryUserCou(String id) { return couMapper.queryUserCou(id); }

}
