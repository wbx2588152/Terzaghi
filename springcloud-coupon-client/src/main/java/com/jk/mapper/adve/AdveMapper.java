package com.jk.mapper.adve;

import com.jk.model.Adve;
import com.jk.model.Coupon;
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
}
