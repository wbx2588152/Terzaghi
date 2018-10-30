package com.jk.service;

import com.jk.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUserList();

    void saveUser(User user);

    void deleteUser(String id);

    User getUserById(String id);

    void updateUser(User user);

    List<Power> getAllNav(HttpServletRequest request);

    List<Users> seeUserList();

    List<Menu> selectAllMenu();

    List<Role> queryRole();

    List<Role> queryRole2(String id);

    Map<String, Object> queryUser(int page, int rows, User user);

    User queryUserById(String id);

    Map<String, Object> getRoleList(Integer page, Integer rows, Role role);

    List<Power> getNavListByRoleId(String roleId);

    void saveRoleNav(String roleId, String ids);

    void addRole(Role role);

    void updateRole(Role role);

    Role toEditRole(Role role);

    void deleteRole(Role role);

    void delOneNav(String id);

    void editOneNav(Power power);

    void saveOneNav(Power power);

    void addOneMenu(Menu menu);

    void delmanyMenu(String ids);

    void editOneMenu(Menu menu);

    Menu getOneMenu(String id);

    ResultPage queryMenuList(Menu menu);

    List<Power> getALLNav();

    List<BuyCar> findUserBuyCar(String id);

    void delBuycarByName(String gid, String id);

    void delOneBuycarByName(String gid, String id);

    void addOneBuycar(String gid, String id);

    void delManyBuycarByName(String gids, String id);

    void saveBuycar(String gid, Integer gnum, String id);
}
