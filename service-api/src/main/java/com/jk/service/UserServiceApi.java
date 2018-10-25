package com.jk.service;

import com.jk.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface UserServiceApi {
    @RequestMapping(value="wbx/ha",method = RequestMethod.POST)
    List<Users> sayHafromOneClient(@RequestBody Users users);

    @RequestMapping(value="wbx/saveWbxUser",method = RequestMethod.POST)
    void saveWbxUser(@RequestBody Users user);

    @RequestMapping(value="wbx/deleteWbxUser",method = RequestMethod.GET)
    void deleteWbxUser(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/queryWbxUserById",method = RequestMethod.GET)
    Users queryWbxUserById(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/updateWbxUser",method = RequestMethod.POST)
    void updateWbxUser(@RequestBody Users user);

    @RequestMapping(value="wbx/getTree",method = RequestMethod.GET)
    List<Power> seepowertree();

    @RequestMapping(value="wbx/seeAllMenu")
    List<Menu> selectAllMenu();

    @RequestMapping(value="wbx/queryRole",method = RequestMethod.GET)
    List<Role> queryRole();

    @RequestMapping(value="wbx/queryRole2",method = RequestMethod.GET)
    List<Role> queryRole2(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/queryUser")
    Map<String, Object> queryUser(@RequestParam(value="page")int page, @RequestParam(value="rows")int rows, @RequestBody User user);

    @RequestMapping(value="wbx/deleteUser",method = RequestMethod.GET)
    void deleteUser(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/saveUser")
    void saveUser(@RequestBody User user);

    @RequestMapping(value="wbx/updateUser")
    void updateUser(@RequestBody User user);

    @RequestMapping(value="wbx/queryUserById",method = RequestMethod.GET)
    User queryUserById(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/getRoleList")
    Map<String, Object> getRoleList(@RequestParam(value="page")Integer page, @RequestParam(value="rows")Integer rows, @RequestBody Role role);

    @RequestMapping(value="wbx/getNavListByRoleId")
    List<Power> getNavListByRoleId(@RequestParam(value="roleId")String roleId);

    @RequestMapping(value="wbx/saveRoleNav")
    void saveRoleNav(@RequestParam(value="roleId")String roleId, @RequestParam(value="ids")String ids);

    @RequestMapping(value="wbx/addRole")
    void addRole(@RequestBody Role role);

    @RequestMapping(value="wbx/toEditRole")
    Role toEditRole(@RequestBody Role role);

    @RequestMapping(value="wbx/updateRole")
    void updateRole(@RequestBody Role role);

    @RequestMapping(value="wbx/deleteRole")
    void deleteRole(@RequestBody Role role);

    @RequestMapping(value="wbx/delOneNav")
    void delOneNav(@RequestParam(value="id")String id);

    @RequestMapping(value="wbx/editOneNav")
    void editOneNav(@RequestBody Power power);

    @RequestMapping(value="wbx/saveOneNav")
    void saveOneNav(@RequestBody Power power);

    @RequestMapping(value="wbx/addOneMenu")
    void addOneMenu(@RequestBody Menu menu);

    @RequestMapping(value="wbx/delmanyMenu")
    void delmanyMenu(@RequestParam(value="ids") String ids);

    @RequestMapping(value="wbx/editOneMenu")
    void editOneMenu(@RequestBody Menu menu);

    @RequestMapping(value="wbx/getOneMenu")
    Menu getOneMenu(@RequestParam(value="id") String id);

    @RequestMapping(value="wbx/queryMenuList")
    ResultPage queryMenuList(@RequestBody Menu menu);

    @RequestMapping(value="wbx/getALLNav")
    List<Power> getALLNav();
    @RequestMapping(value="wbx/findUserBuyCar")
    List<BuyCar> findUserBuyCar(@RequestParam(value="id")String id);
    @RequestMapping(value="wbx/delBuycarByName")
    void delBuycarByName(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id);
    @RequestMapping(value="wbx/delOneBuycarByName")
    void delOneBuycarByName(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id);
    @RequestMapping(value="wbx/addOneBuycar")
    void addOneBuycar(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id);
    @RequestMapping(value="wbx/delManyBuycarByName")
    void delManyBuycarByName(@RequestParam(value="gids")String gids, @RequestParam(value="id")String id);
}
