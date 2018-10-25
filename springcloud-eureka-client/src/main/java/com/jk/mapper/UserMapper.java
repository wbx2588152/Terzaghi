package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    @Select(" select * from t_user ")
    List<User> getUserList();

    void saveUser(User user);

    void deleteUser(@Param("id") String id);
    @Select(" select * from t_user where id=#{id} ")
    User getUserById(@Param("id") String id);

    void updateUser(@Param("user")User user);

    List<Power> getpowerlist(String userId);
    @Select(" select * from t_user ")
    List<Users> seeUserList();
    @Select(" select * from t_menu ")
    List<Menu> selectAllMenu();

    void delManyUsertoUserId(User user);

    void saveUsertoRole(UserRole utr);

    void saveUserRole(List<UserRole> list);

    void deleteUserRole(@Param("id")String id);

    List<Role> queryRole();

    List<Role> queryRole2(String id);

    long queryUser(@Param("user")User user);

    List<Role> queryRoleList();

    List<User> queryUserList(@Param("st")int start, @Param("end")int end, @Param("user")User user);

    User queryUserById(@Param("id")String id);

    Integer getRoleTotal(@Param("r")Role role);

    List<Role> getRoleList(@Param("st") Integer start, @Param("end") Integer end, @Param("r")Role role);

    List<RolePower> getNavIdListByRoleId(String roleId);

    List<Power> getNavListByRoleId(HashMap<String, Object> params);

    void delRoleNavByRoleId(String roleId);

    void saveRoleNav(ArrayList<RolePower> arrayList);

    void addRole(@Param("r")Role role);

    Role toEditRole(@Param("r")Role role);

    void updateRole(@Param("r")Role role);

    void deleteRole(@Param("r")Role role);

    void delManyNav(List<String> ids);

    List<Power> getNavALLById(String id);

    void editOneNav(Power power);

    void saveOneNav(Power power);

    void addOneMenu(Menu menu);

    void delmanyMenu(String[] id);

    void editOneMenu(Menu menu);

    Menu getOneMenu(String id);

    int querygetMenuCountByNavid(Menu menu);

    List<Menu> getMenuListByNavid(Menu menu);

    List<BuyCar> findUserBuyCar(String id);

    void delBuycarByName(@Param("gid") String gid, @Param("id")String id);

    List<BuyCar> getBuycarsList(@Param("gid") String gid, @Param("id")String id);

    void delOneBuyCar(String id);

    void addOneBuycar(BuyCar buyCar);

    void delManyBuycarByName(@Param("gid")String gids, @Param("id")String id);
}
