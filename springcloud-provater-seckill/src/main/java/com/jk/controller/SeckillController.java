package com.jk.controller;

import com.jk.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:秒杀后台
 */
@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;


}
