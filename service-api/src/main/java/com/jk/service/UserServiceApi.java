package com.jk.service;

import com.jk.model.Power;
import com.jk.model.User;
import com.jk.model.Users;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserServiceApi {
    @RequestMapping(value="wbx/ha")
    List<User> sayHafromOneClient();

    @RequestMapping(value="wbx/saveUser",method = RequestMethod.POST)
    void saveWbxUser(@RequestBody User user);

    @RequestMapping(value="wbx/deleteUser",method = RequestMethod.GET)
    void deleteWbxUser(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/getUserById",method = RequestMethod.GET)
    User queryWbxUserById(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/updateUser",method = RequestMethod.POST)
    void updateWbxUser(@RequestBody User user);

    @RequestMapping(value="wbx/getTree")
    List<Power> seepowertree();

    @RequestMapping(value="wbx/seeUserList")
    List<Users> seeUserList();

}
