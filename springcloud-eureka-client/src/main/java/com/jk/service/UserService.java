package com.jk.service;

import com.jk.model.Power;
import com.jk.model.User;
import com.jk.model.Users;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    List<User> getUserList();

    void saveUser(User user);

    void deleteUser(String sid);

    User getUserById(String id);

    void updateUser(User user);

    List<Power> getAllNav(HttpServletRequest request);

    List<Users> seeUserList();
}
