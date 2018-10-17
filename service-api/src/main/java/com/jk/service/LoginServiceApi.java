package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface LoginServiceApi {

    @RequestMapping(value="user/findUserPermissions",method = RequestMethod.POST)
    List<Menu> findUserPermissions(String userName);

    @RequestMapping(value="user/checkUserName",method = RequestMethod.POST)
    User findByName(String userName);

    @RequestMapping(value="user/findUserRole",method = RequestMethod.POST)
    List<Role> findUserRole(String userName);
}
