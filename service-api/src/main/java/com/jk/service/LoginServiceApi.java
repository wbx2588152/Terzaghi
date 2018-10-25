package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.Power;
import com.jk.model.Role;
import com.jk.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface LoginServiceApi {

    @RequestMapping(value="user/findUserPermissions",method = RequestMethod.POST)
    List<Menu> findUserPermissions(@RequestParam(value="userName") String userName);

    @RequestMapping(value="user/checkUserName",method = RequestMethod.POST)
    User findByName(@RequestParam(value="userName")String userName);

    @RequestMapping(value="user/findUserRole",method = RequestMethod.POST)
    List<Role> findUserRole(@RequestParam(value="userName") String userName);

    @RequestMapping(value="user/updateLoginTime",method = RequestMethod.POST)
    void updateLoginTime(String loginname);

    @RequestMapping(value="user/findUserByName",method = RequestMethod.POST)
    User findUserByName(@RequestParam(value="userName")String loginname);

    @RequestMapping(value="user/selectAllMenu",method = RequestMethod.POST)
    List<Menu> selectAllMenu();
}
