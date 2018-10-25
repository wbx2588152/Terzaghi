package com.jk.controller;

import com.jk.model.Coupon;
import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;
import com.jk.service.LoginServiceApi;
import com.jk.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginServiceApi userService;

    private static final String ON = "on";

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "user/checkUserName",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        User result = this.userService.findByName(username);
        return result == null;
    }

    @RequestMapping(value="user/findUserRole",method = RequestMethod.GET)
    @ResponseBody
    public List<Role> roleList(String username) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getLoginname();
        return userService.findUserRole(userName);
    }

    @RequestMapping(value="user/findUserPermissions",method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> permissionList(String username) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String userName = user.getLoginname();
        return userService.findUserPermissions(userName);
    }







}
