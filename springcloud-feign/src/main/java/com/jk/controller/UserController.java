package com.jk.controller;

import com.jk.model.User;
import com.jk.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    private Userservice userservice;

    @RequestMapping(value="seelist")
    public String seelist(){
        return "/userlist";
    }
    @RequestMapping(value="toWbxAdd")
    public String toWbxAdd(){
        return "/adduser";
    }
    @RequestMapping(value="ha")
    @ResponseBody
    public List<User> ha(){
        return userservice.sayHafromOneClient();
    }
    @RequestMapping(value="saveWbxUser")
    @ResponseBody
    public String saveWbxUser(User user){
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        userservice.saveWbxUser(user);
        return "1";
    }
    @RequestMapping(value="deleteWbxUser")
    @ResponseBody
    public String deleteWbxUser(@RequestParam(value="id")String id){
        userservice.deleteWbxUser(id);
        return "1";
    }
    @RequestMapping(value="toWbxEdit")
    public String toWbxEdit(@RequestParam(value="id")String id, HttpServletRequest request){
        User user =  userservice.queryWbxUserById(id);
        request.setAttribute("user", user);
        return "/editlist";
    }
    @RequestMapping(value="updateWbxUser")
    @ResponseBody
    public String updateWbxUser(User user){
        userservice.updateWbxUser(user);
        return "1";
    }

}
