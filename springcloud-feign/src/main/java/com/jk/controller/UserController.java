package com.jk.controller;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.*;

import com.jk.service.PowerService;
import com.jk.util.MD5Utils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("wbx")
public class UserController {
    @Autowired
    private PowerService userservice;

    @RequestMapping(value="seelist")
    public String seelist(){
        return "/userlist";
    }
    @RequestMapping(value="seeadmin")
    public String seeadmin(){
        return "/index";
    }
    @RequestMapping(value="toWbxAdd")
    public String toWbxAdd(){
        return "/adduser";
    }
    @RequestMapping(value="ha")
    @ResponseBody
    public List<Users> ha(Users users){
        return userservice.sayHafromOneClient(users);
    }
    @RequestMapping(value="saveWbxUser")
    @ResponseBody
    public String saveWbxUser(Users user){
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        userservice.saveWbxUser(user);
        return "1";
    }
    @RequestMapping(value="deleteWbxUser")
    @ResponseBody
    public String deleteWbxUser(@RequestParam(value="id")String id){
        userservice.deleteWbxUser(id);
        return "1";
    }
    @RequestMapping(value="toWbxEdit")
    public String toWbxEdit(@RequestParam(value="id")String id, HttpServletRequest request){
        Users user =  userservice.queryWbxUserById(id);
        request.setAttribute("user", user);
        return "/editlist";
    }
    @RequestMapping(value="updateWbxUser")
    @ResponseBody
    public String updateWbxUser(Users user){
        userservice.updateWbxUser(user);
        return "1";
    }
    @RequestMapping(value="seepowertree")
    @ResponseBody
    public List<Power> seepowertree(){
       List<Power> x =userservice.seepowertree();
       return x;
    }
    @RequestMapping(value="seepowertree2")
    @ResponseBody
    public List<Power> seepowertree2(){
        List<Power> x =userservice.seepowertree();
        return x;
    }
    @RequestMapping(value="seetree")
    public String seetree(){
        return "/user/index";
    }

    /* *
     * 用户列表
    */
    @RequestMapping("queryUser")
    @ResponseBody
    public String queryUser(int page,int limit,User user){
        int rows=limit;
        Map<String,Object> map = userservice.queryUser(page,rows,user);
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",(Integer)map.get("total"));
        obj.put("data" , (List<User>)map.get("rows"));

        return obj.toString();
    }

    /**
     * 删除用户
*/
    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(String id) {
        userservice.deleteUser(id);
        return "1";
    }

    /**
     * 两表新增，用户，用户赋角色
*/
    @RequestMapping("saveUser")
    @ResponseBody
    public String saveUser(User user) {
        
        user.setLastdate(new Date());
        String password = MD5Utils.encrypt(user.getLoginname(), user.getPassword());
        user.setPassword(password);
        userservice.saveUser(user);

        return "1";
    }

   /* *
     * 修改用户
*/
    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(User user) {
        userservice.updateUser(user);
        return "1";
    }
  /*  *
     * 跳转用户页面
*/
    @RequestMapping("toUser")
    public String toUser() {
        return "user/userlist";
    }

/*
    *
     * 跳转新增用户页面
*/

    @RequestMapping("toAdd")
    public String toAdd(HttpServletRequest request) {
        List<Role> roleAll = userservice.queryRole();
        request.setAttribute("list", roleAll);
        return "user/adduser";
    }

   /* *
     * 跳转修改用户页面
*/
    @RequestMapping("toEdit")
    public String toEdit(HttpServletRequest request,String id) {
        User user =  userservice.queryUserById(id);
        List<Role> roleAll = userservice.queryRole();
        List<Role> roles2=userservice.queryRole2(id);
        request.setAttribute("user", user);
        request.setAttribute("role", roleAll);
        request.setAttribute("roles", roles2);
        return "user/edituser";
    }
    @RequestMapping("toEdit2")
    @ResponseBody
    public Map<String,Object> toEdit2(String id) {
        Map<String,Object> map= new HashMap<>();

        List<Role> roleAll = userservice.queryRole();
        List<Role> roles2=userservice.queryRole2(id);
        map.put("role", roleAll);
        map.put("roles", roles2);
        return map;
    }
    //跳转角色管理
    @RequestMapping("tojslb")
    public String tojslb(){

        return "yx/RoleManagement";

    }

    //展示角色列表
    @RequestMapping("getRoleList")
    @ResponseBody
    public Map<String,Object> getRoleList(Integer page,Integer rows,Role role){
        Map<String,Object> map = userservice.getRoleList(page,rows,role);
        return map;

    }

    //点击绑定权限出复选框树  getNavTreeByRoleId
    @RequestMapping("getNavTreeByRoleId")
    @ResponseBody
    public List<Power> getNavTreeByRoleId(String roleId){
        return userservice.getNavListByRoleId(roleId);

    }

    //保存角色权限
    @RequestMapping("saveRoleNav")
    @ResponseBody
    public Boolean saveRoleNav(String roleId,String ids) {
        try {
            userservice.saveRoleNav(roleId,ids);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //跳转添加角色页面
    @RequestMapping("toaddRole")
    public String toaddRole(){
        return "yx/addRole";

    }

    //增加角色
    @RequestMapping("addRole")
    @ResponseBody
    public String addRole(Role role){
        role.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userservice.addRole(role);
        return "1";

    }

    //修改回显
    @RequestMapping("toEditRole")
    public String toEditRole(Role role,HttpServletRequest request){
        Role re = userservice.toEditRole(role);
        request.setAttribute("r",re);
        return "yx/updateRole";

    }

    //修改角色
    @RequestMapping("updateRole")
    @ResponseBody
    public String updateRole(Role role){
        userservice.updateRole(role);
        return "1";

    }

    //删除角色
    @RequestMapping("deleteRole")
    @ResponseBody
    public String deleteRole(Role role){
        userservice.deleteRole(role);
        return "1";

    }
    //权限树
    //递归删除权限树节点
    @RequestMapping("delOneNav")
    @ResponseBody
    public String delOneNav(String id) {
        userservice.delOneNav(id);
        return "1";
    }

    //修改权限树节点
    @RequestMapping("editOneNav")
    @ResponseBody
    public void editOneNav(Power power) {
        userservice.editOneNav(power);
    }
    //打开修改权限页面
    @RequestMapping("seeEditNav")
    public String seeEditNav(Power power,HttpServletRequest request) {
        request.setAttribute("node", power);
        return "/wwq/editnode";
    }

    //新增权限树节点
    @RequestMapping("addOneNav")
    @ResponseBody
    public void addOneNav(Power power) {

        userservice.saveOneNav(power);

    }
    //打开新增权限页面
    @RequestMapping("seeAddNav")
    public String seeAddNav(String id,HttpServletRequest request) {
        request.setAttribute("nodeid", id);
        return "/wwq/addnode";
    }

    //新增菜单
    @RequestMapping("addOneMenu")
    @ResponseBody
    public void addOneMenu(Menu menu) {

        userservice.addOneMenu(menu);

    }

    //打开新增菜单页面
    @RequestMapping("seeAddMenu")
    public String seeAddMenu(String id,HttpServletRequest request) {
        request.setAttribute("navid", id);
        return "/wwq/add";
    }
    //批量删除菜单
    @RequestMapping("delmanyMenu")
    @ResponseBody
    public String delmanyMenu(String[] id) {
        String ids ="";
        for (int i = 0; i <id.length ; i++) {
            ids+= (ids=="" ? "":",")+id[i];
        }
        userservice.delmanyMenu(ids);
        return "1";

    }
    //修改菜单
    @RequestMapping("editOneMenu")
    @ResponseBody
    public void editOneMenu(Menu menu) {

        userservice.editOneMenu(menu);

    }

    //打开修改菜单页面
    @RequestMapping("seeEditMenu")
    public String seeEditMenu(String id,HttpServletRequest request) {
        Menu menu=userservice.getOneMenu(id);
        request.setAttribute("mymenu", menu);
        return "/wwq/edit";
    }

    /**
     * 查詢
     * @param menu
     * @return
     */

    //获取权限表
    @RequestMapping("querymenuList")
    @ResponseBody
    public ResultPage querymenuList(Menu menu) {
        menu.calculate();
        return userservice.queryMenuList(menu) ;

    }

    /**
     * 跳轉界面方法
     */
    @RequestMapping("toAddRole")
    public String torolepage() {
        return "/wwq/rolePower";

    }

    //获取权限树
    @RequestMapping("getALLNav")
    @ResponseBody
    public List<Power> getALLNav(){
        return userservice.getALLNav();
    }


}
