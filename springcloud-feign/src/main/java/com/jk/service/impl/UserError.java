package com.jk.service.impl;

import com.jk.model.Power;
import com.jk.model.User;



import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
<<<<<<<<< Temporary merge branch 1
public class UserError  {
=========
public class UserError implements Userservice {
    @Override
    public List<User> sayHafromOneClient() {
        List<User> list=new ArrayList<>();
        User user =new User();
        //user.setMycode("sorry,error!");
        list.add(user);
        return list;
    }

    @Override
    public void saveWbxUser(User user) {

    }

    @Override
    public void deleteWbxUser(String id) {

    }

    @Override
    public User queryWbxUserById(String id) {
        User user =new User();
        //user.setMycode("sorry,error!");
        return user;
    }

    @Override
    public void updateWbxUser(User user) {
>>>>>>>>> Temporary merge branch 2

}
