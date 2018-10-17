package com.jk.mapper;

import com.jk.config.MyMapper;
import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    List<Role> findUserRole(String userName);

    List<Menu> findUserPermissions(String userName);

    void updateByExampleSelective(User user, Example example);
}
