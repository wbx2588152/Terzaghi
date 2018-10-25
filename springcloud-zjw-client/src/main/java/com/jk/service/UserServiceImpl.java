package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;

@Service("userservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User>  implements  UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Menu> findUserPermissions(String userName) {
        return this.userMapper.findUserPermissions(userName);
    }



    /*@Override
    public User findByName(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(loginname)=", userName.toLowerCase());
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);

    }*/



    @Override
    public List<Role> findUserRole(String userName) {
        return this.userMapper.findUserRole(userName);
    }

    @Override
    public void updateLoginTime(String loginname) {
        Date date=new Date();
        userMapper.updateLoginTime(loginname,date);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public List<Menu> selectAllMenu() {
        return userMapper.selectAllMenu();
    }
}
