package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;

import java.util.List;

public interface UserService {


    List<Menu> findUserPermissions(String userName);

    /*User findByName(String userName);*/

    List<Role> findUserRole(String userName);


    void updateLoginTime(String loginname);

    User findUserByName(String username);

    List<Menu> selectAllMenu();
}
