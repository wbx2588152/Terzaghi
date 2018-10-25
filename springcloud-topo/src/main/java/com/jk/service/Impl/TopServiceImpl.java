package com.jk.service.Impl;

import com.jk.mapper.StockBeanMapper;
import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import com.jk.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopServiceImpl implements TopService {
    @Autowired
    private StockBeanMapper stockBeanMapper;


    @Override
    public List<StockBean> getUserList(String name, int page, int limit) {
        return stockBeanMapper.getUserList(name,page,limit);
    }

    @Override
    public int delete(Integer stockid) {
        return stockBeanMapper.delete(stockid);
    }

    @Override
    public List<ProbabilityBean> suiji() {
        return stockBeanMapper.suiji();
    }

    @Override
    public int getlunpanCount() {
        return stockBeanMapper.getlunpanCount();
    }

    @Override
    public List<ProbabilityBean> getLunpanList(String name, int page, int limit) {
        return stockBeanMapper.getLunpanList(name,page,limit);
    }

    @Override
    public ProbabilityBean getUserById(Integer id) {
        return stockBeanMapper.getUserById(id);
    }

    @Override
    public int update(ProbabilityBean probabilityBean) {
        return stockBeanMapper.update(probabilityBean);
    }

    @Override
    public int getUserCount() {
        return stockBeanMapper.getUserCount();
    }
}
