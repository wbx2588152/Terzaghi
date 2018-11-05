package com.jk.service.adve;

import com.jk.model.Adve;
import com.jk.model.Resore;

import java.util.List;
import java.util.Map;

public interface AdveService {


    Map<String, Object> queryDvrelist(int page, int rows, Adve adve);

    void delAdve(String id);

    void saveAdve(Adve adve);

    Adve queryAdveById(String id);

    void updateAdve(Adve adve);

    List<Adve> queryAdveList(Adve adve);

    Map<String, Object> queryReslist(int page, int limit, Resore res);

    void saveRes(Resore res);

    void delRes(String id);

    Resore queryResById(String id);

    void updateRes(Resore res);

    List<Resore> queryRes(Resore res);
}
