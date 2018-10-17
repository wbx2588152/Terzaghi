package com.jk.controller;


import com.jk.model.Power;
import com.jk.model.User;
import com.jk.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("wbx")
public class UserController {
    @Value("${server.port}")
    String port;
    @Autowired
    private UserService userService;
    @RequestMapping(value="ha",method = RequestMethod.GET)
    @ResponseBody
    public List<User> userlist() {
        System.out.println(port);
        return userService.getUserList();
    }
    @RequestMapping(value="saveUser",method = RequestMethod.POST)
    @ResponseBody
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }
    @RequestMapping(value="deleteUser",method = RequestMethod.GET)
    @ResponseBody
    public void deleteUser(@RequestParam(value="id") String id){
        userService.deleteUser(id);
    }
    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@RequestParam(value="id") String id){
        return userService.getUserById(id);
    }
    @RequestMapping(value="updateUser",method = RequestMethod.POST)
    @ResponseBody
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }
    //主页面左侧树
    @RequestMapping(value="getTree",method = RequestMethod.GET)
    @ResponseBody
    public List<Power> getTree(HttpServletRequest request) {
            return userService.getAllNav(request);
    }

}
