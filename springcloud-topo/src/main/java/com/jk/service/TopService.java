package com.jk.service;

import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;

import java.util.List;

/**
 * @Auther: 王嘟嘟
 * @Date: 2018/10/18 20:10
 * @Description:
 */
public interface TopService {



    int getUserCount();

    List<StockBean> getUserList(String name, int page, int limit);

    int delete(Integer stockid);

    List<ProbabilityBean> suiji();

    int getlunpanCount();

    List<ProbabilityBean> getLunpanList(String name, int page, int limit);

    ProbabilityBean getUserById(Integer id);

    int update(ProbabilityBean probabilityBean);


}
