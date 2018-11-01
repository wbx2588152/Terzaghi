package com.jk.service.adve;

import com.jk.model.Adve;

import java.util.List;
import java.util.Map;

public interface AdveService {


    Map<String, Object> queryDvrelist(int page, int rows, Adve adve);

    void delAdve(String id);

    void saveAdve(Adve adve);

    Adve queryAdveById(String id);

    void updateAdve(Adve adve);

    List<Adve> queryAdveList(Adve adve);
}
