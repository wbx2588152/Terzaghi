package com.jk.controller;

import com.jk.service.SeckilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:秒杀后台
 */
@RequestMapping("user")
@Controller
public class SeckillController {

    @Autowired
    private SeckilService seckilService;


}
