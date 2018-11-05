package com.jk.mapper.adve;

import com.jk.model.Adve;
import com.jk.model.Coupon;
import com.jk.model.Resore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdveMapper {
    long queryAdveCount(Adve adve);

    List<Coupon> queryAdveList(@Param("st") int start, @Param("end") int end, Adve adve);

    void delAdve(@Param("id") String id);

    void saveAdve(Adve adve);

    Adve queryAdveById(@Param("id") String id);

    void updateAdve(Adve adve);

    List<Adve> queryAdve(Adve adve);

    long queryResCount(Resore res);

    List<Resore> queryReslist(@Param("st")int start, @Param("end")int end, Resore res);

    void saveRes(Resore res);

    void delRes(@Param("id")String id);

    Resore queryResById(@Param("id")String id);

    void updateRes(Resore res);

    List<Resore> queryRes(Resore res);
}
