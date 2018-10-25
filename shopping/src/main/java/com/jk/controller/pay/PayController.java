package com.jk.controller.pay;

import com.jk.model.BuyCar;
import com.jk.model.User;
import com.jk.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("seeCarbuy")
    public String seeCarbuy(){
        return "/buycar";
    }
    @RequestMapping("getUserBuyCar")
    @ResponseBody
    public List<BuyCar> getUserBuyCar(HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        List<BuyCar> buycar=payService.findUserBuyCar("1");
        return buycar;
    }
    @RequestMapping("delBuycarByName")
    @ResponseBody
    public Boolean delBuycarByName(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delBuycarByName(gid,"1");
        return true;
    }
    @RequestMapping("delOneBuycarByName")
    @ResponseBody
    public Boolean delOneBuycarByName(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delOneBuycarByName(gid,"1");
        return true;
    }
    @RequestMapping("delManyBuycarByName")
    @ResponseBody
    public Boolean delManyBuycarByName(String gids,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delManyBuycarByName(gids,"1");
        return true;
    }

    @RequestMapping("addOneBuycar")
    @ResponseBody
    public Boolean addOneBuycar(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.addOneBuycar(gid,"1");
        return true;
    }
}
