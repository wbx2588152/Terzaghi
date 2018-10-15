package com.jk.service;

import com.jk.model.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();

    void saveUser(User user);

    void deleteUser(String sid);

    User getUserById(String id);

    void updateUser(User user);
}
