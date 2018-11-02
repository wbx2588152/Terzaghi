package com.jk.controller.frontorder;

import com.jk.model.Comm;
import com.jk.model.OrderBean;
import com.jk.model.RegionBean;
import com.jk.model.User;
import com.jk.service.FrontOrder.FrontService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<OrderBean> list = (List<OrderBean>) map.get("list");
        modelMap.put("list",list);
        return "frontorder/orderlist";
    }


    @RequestMapping("toshowdetail")
    public String toshowdetail(String orderid,HttpServletRequest request,ModelMap modelMap){

        HttpSession session = request.getSession();
        User  userBean = (User) session.getAttribute(session.getId());
        OrderBean orderBean=frontService.queryRegionAgain(userBean.getId(),orderid);
        Date subtime = orderBean.getSubtime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(subtime);
        orderBean.setSubdate(format);
        modelMap.put("orderBean",orderBean);
        return "frontorder/frontdetail";
    }

    @RequestMapping("updateorderstatus")
    @ResponseBody
    public String updateorderstatus(String orderid){
        frontService.updateorderstatus(orderid);
        return "success";
    }




}
