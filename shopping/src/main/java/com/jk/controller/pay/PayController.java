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
        List<BuyCar> buycar=payService.findUserBuyCar(attribute.getId());
        return buycar;
    }
    @RequestMapping("delBuycarByName")
    @ResponseBody
    public Boolean delBuycarByName(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delBuycarByName(gid,attribute.getId());
        return true;
    }
    @RequestMapping("delOneBuycarByName")
    @ResponseBody
    public Boolean delOneBuycarByName(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delOneBuycarByName(gid,attribute.getId());
        return true;
    }
    @RequestMapping("delManyBuycarByName")
    @ResponseBody
    public Boolean delManyBuycarByName(String gids,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.delManyBuycarByName(gids,attribute.getId());
        return true;
    }

    @RequestMapping("addOneBuycar")
    @ResponseBody
    public Boolean addOneBuycar(String gid,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.addOneBuycar(gid,attribute.getId());
        return true;
    }

    @RequestMapping("saveSomeBuycar")
    @ResponseBody
    public Boolean saveBuycar(String gid,Integer gnum,HttpServletRequest request){
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        payService.saveBuycar(gid,gnum,attribute.getId());
        return true;
    }
}
