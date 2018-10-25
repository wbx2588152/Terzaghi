package com.jk.controller;

import com.jk.model.Power;
import com.jk.service.LoginServiceApi;
import com.jk.service.PowerService;
import com.jk.service.UserServiceApi;
import com.jk.shiro.FebsProperties;
import com.jk.shiro.ResponseBo;
import com.jk.util.Captcha;
import com.jk.util.GifCaptcha;
import com.jk.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.apache.shiro.util.ThreadContext.getSubject;

@Controller
public class LoginController {

    @Value("${server.port}")
    String port;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @Autowired
    private FebsProperties febsProperties;

    @Autowired
    private LoginServiceApi userservice;

    @Autowired
    private PowerService powerService;

    @RequestMapping("seepowertree")
    @ResponseBody
    public List<Power> seepowertree(){
        List<Power> x =powerService.seepowertree();
        return x;
    }


    //跳转登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    //跳转登录页面
    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String loginname, String password, String code, Boolean rememberMe) {
        if (!StringUtils.isNotBlank(code)) {
            return ResponseBo.warn("验证码不能为空！");
        }
        Session session = SecurityUtils.getSubject().getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            return ResponseBo.warn("验证码错误！");
        }
        // 密码 MD5 加密
       password = MD5Utils.encrypt(loginname.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, password, rememberMe);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            subject.login(token);
            this.userservice.updateLoginTime(loginname);
            return ResponseBo.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
       } catch (AuthenticationException e) { }
        return ResponseBo.ok();
    }
    @RequestMapping("/logout")
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(
                    febsProperties.getValidateCode().getWidth(),
                    febsProperties.getValidateCode().getHeight(),
                    febsProperties.getValidateCode().getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }










}
