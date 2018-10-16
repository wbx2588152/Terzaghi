package com.jk.service;

import com.jk.mapper.SeckilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckilMapper seckilMapper;

}
