package com.jk.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
   /* @Select(" select * from t_user ")
    List<User> getUserList();
    @Insert(" insert into t_user(id,name,mycode) values (#{u.id},#{u.name},#{u.mycode}) ")
    void saveUser(@Param("u") User user);
    @Delete(" delete from t_user where id=#{u.id}")
    void deleteUser(@Param("id") String id);
    @Select(" select * from t_user where id=#{id} ")
    User getUserById(@Param("id") String id);
    @Update(" update t_user set name=#{u.name},mycode=#{u.mycode} where id=#{u.id} ")
    void updateUser(@Param("u")User user);*/
}
