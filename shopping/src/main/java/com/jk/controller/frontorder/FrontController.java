package com.jk.controller.frontorder;

import com.jk.model.Comm;
import com.jk.model.User;
import com.jk.service.FrontOrder.FrontService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("front")
public class FrontController {

    @Autowired
    private FrontService frontService;

    @RequestMapping("toshoworder")
    public  String toshoworder(ModelMap modelMap, HttpServletRequest request){
        HttpSession session = request.getSession();
        User  userBean = (User) session.getAttribute(session.getId());
        Map<String,Object> map=frontService.queryuserorder(userBean.getId());
        List<Comm> list = (List<Comm>) map.get("list");
        modelMap.put("list",list);
        return "frontorder/orderlist";
    }





}
