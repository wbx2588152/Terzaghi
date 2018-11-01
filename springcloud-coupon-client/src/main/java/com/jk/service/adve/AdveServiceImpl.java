package com.jk.service.adve;

import com.jk.mapper.adve.AdveMapper;
import com.jk.mapper.coupon.CouMapper;
import com.jk.model.Adve;
import com.jk.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdveServiceImpl  implements AdveService {

    @Autowired
    private AdveMapper adveMapper;

    @Override
    public Map<String, Object> queryDvrelist(int page, int rows, Adve adve) {
        HashMap<String, Object> map = new HashMap<>();
        long count = adveMapper.queryAdveCount(adve);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Coupon> commList = adveMapper.queryAdveList(start,end,adve);
        map.put("total",count);
        map.put("rows",commList);
        return map;
    }

    @Override
    public void delAdve(String id) {
       adveMapper.delAdve(id);
    }

    @Override
    public void saveAdve(Adve adve) {
        adveMapper.saveAdve(adve);
    }

    @Override
    public Adve queryAdveById(String id) {
        return adveMapper.queryAdveById(id);
    }

    @Override
    public void updateAdve(Adve adve) {
         adveMapper.updateAdve(adve);
    }

    @Override
    public List<Adve> queryAdveList(Adve adve) {
        return adveMapper.queryAdve(adve);
    }


}
