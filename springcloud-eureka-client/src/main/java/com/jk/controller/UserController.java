package com.jk.controller;


import com.jk.model.*;
import com.jk.service.UserService;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("wbx")
public class UserController {
    @Value("${server.port}")
    String port;
    @Autowired
    private UserService userservice;
    @Autowired
    private SolrClient client;

    @Autowired
    private CloudSolrClient cloudSolrClient;

    //主页面左侧树
    @RequestMapping(value="getTree",method = RequestMethod.GET)
    public List<Power> getTree(HttpServletRequest request) {
        return userservice.getAllNav(request);
    }
    @RequestMapping(value="seeUserList",method = RequestMethod.GET)
    public List<Users> seeUserList(){
        return userservice.seeUserList();
    }
    @RequestMapping(value="seeAllMenu",method = RequestMethod.GET)
    public List<Menu> selectAllMenu(){
        return userservice.selectAllMenu();
    }
    //五表用户
    @RequestMapping(value="queryRole",method = RequestMethod.GET)
    List<Role> queryRole(){
        return userservice.queryRole();
    }
    @RequestMapping(value="queryRole2",method = RequestMethod.GET)
    List<Role> queryRole2(@RequestParam(value="id") String id){
        return userservice.queryRole2(id);
    }
    @RequestMapping(value="queryUser",method = RequestMethod.POST)
    Map<String, Object> queryUser(@RequestParam(value="page")int page, @RequestParam(value="rows")int rows, @RequestBody User user){
        return userservice.queryUser(page,rows,user);
    }

    @RequestMapping(value="deleteUser",method = RequestMethod.GET)
    void deleteUser(@RequestParam(value="id") String id){
        userservice.deleteUser(id);
    }

    @RequestMapping(value="saveUser",method = RequestMethod.POST)
    void saveUser(@RequestBody User user){
        userservice.saveUser(user);
    }

    @RequestMapping(value="updateUser",method = RequestMethod.POST)
    void updateUser(@RequestBody User user){
        userservice.updateUser(user);
    }

    @RequestMapping(value="queryUserById",method = RequestMethod.GET)
    User queryUserById(@RequestParam(value="id") String id){
        return userservice.queryUserById(id);
    }

    @RequestMapping(value="getRoleList")
    Map<String, Object> getRoleList(@RequestParam(value="page")Integer page, @RequestParam(value="rows")Integer rows, @RequestBody Role role){
        return userservice.getRoleList(page, rows, role);
    }

    @RequestMapping(value="getNavListByRoleId")
    List<Power> getNavListByRoleId(@RequestParam(value="roleId")String roleId){
        return userservice.getNavListByRoleId(roleId);
    }

    @RequestMapping(value="saveRoleNav")
    void saveRoleNav(@RequestParam(value="roleId")String roleId, @RequestParam(value="ids")String ids){
        userservice.saveRoleNav(roleId, ids);
    }

    @RequestMapping(value="addRole")
    void addRole(@RequestBody Role role){
        userservice.addRole(role);
    }

    @RequestMapping(value="toEditRole")
    Role toEditRole(@RequestBody Role role){
        return userservice.toEditRole(role);
    }

    @RequestMapping(value="updateRole")
    void updateRole(@RequestBody Role role){
        userservice.updateRole(role);
    }

    @RequestMapping(value="deleteRole")
    void deleteRole(@RequestBody Role role){
        userservice.deleteRole(role);
    }

    @RequestMapping(value="delOneNav")
    void delOneNav(@RequestParam(value="id")String id){
        userservice.delOneNav(id);
    }

    @RequestMapping(value="editOneNav")
    void editOneNav(@RequestBody Power power){
        userservice.editOneNav(power);
    }

    @RequestMapping(value="saveOneNav")
    void saveOneNav(@RequestBody Power power){
        userservice.saveOneNav(power);
    }

    @RequestMapping(value="addOneMenu")
    void addOneMenu(@RequestBody Menu menu){
        userservice.addOneMenu(menu);
    }

    @RequestMapping(value="delmanyMenu")
    void delmanyMenu(@RequestParam(value="ids") String ids){
        userservice.delmanyMenu(ids);
    }

    @RequestMapping(value="editOneMenu")
    void editOneMenu(@RequestBody Menu menu){
        userservice.editOneMenu(menu);
    }

    @RequestMapping(value="getOneMenu")
    Menu getOneMenu(@RequestParam(value="id") String id){
        return userservice.getOneMenu(id);
    }

    @RequestMapping(value="queryMenuList")
    ResultPage queryMenuList(@RequestBody Menu menu){
        return userservice.queryMenuList(menu);
    }

    @RequestMapping(value="getALLNav")
    List<Power> getALLNav(){
        return userservice.getALLNav();
    }


    //slor test

    @RequestMapping(value="ha",method = RequestMethod.POST)
    public List<Users> userlist(@RequestBody Users users) throws IOException, SolrServerException {
        List<Users> userslist=new ArrayList<>();
        SolrQuery params = new SolrQuery();
        if(users.getIntroduce()!=""){
            params.set("q", users.getIntroduce());
        }else{
            params.set("q", "*:*");
        }
        params.set("df", "solr_introduce");
        params.set("fl", "id,solr_name,solr_introduce");
        params.addHighlightField("solr_introduce"); // 高亮字段
        //高亮
        //打开开关
        params.setHighlight(true);
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");
        QueryResponse queryResponse = cloudSolrClient.query("core2",params);
        SolrDocumentList results = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        int i=0;
        for (SolrDocument result : results) {
            Users user=new Users();
            String highname="";
            Map<String, List<String>> map = highlight.get(result.get("id"));
            List<String> list = map.get("solr_introduce");
            if(list==null){
                highname=(String)result.get("solr_introduce");
            }else{
                highname=list.get(i);
            }
            user.setId((String)result.get("id"));
            user.setName((String)result.get("solr_name"));
            user.setIntroduce(highname);
            userslist.add(user);
            i++;
        }
        return userslist;
    }

    @RequestMapping(value="queryWbxUserById",method = RequestMethod.GET)
    Users queryWbxUserById(@RequestParam(value="id") String id) throws IOException, SolrServerException {
        SolrDocument document = cloudSolrClient.getById("core2", id);
        Users user=new Users();
        user.setId((String)document.get("id"));
        user.setName((String)document.get("solr_name"));
        user.setIntroduce((String)document.get("solr_introduce"));
        return user;
    }
    @RequestMapping(value="updateWbxUser",method = RequestMethod.POST)
    void updateWbxUser(@RequestBody Users user){
        try {

            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", user.getId());
            doc.setField("solr_name", user.getName());
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 core1 这个参数
             * 下面都是一样的
             */
            cloudSolrClient.add("core2", doc);
            //client.commit();
            cloudSolrClient.commit("core2");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value="saveWbxUser",method = RequestMethod.POST)
    void saveWbxUser(@RequestBody Users user){
        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", uuid);
            doc.setField("solr_introduce", user.getIntroduce());
            doc.setField("solr_name", user.getName());
            /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 core1 这个参数
             * 下面都是一样的
             */
            cloudSolrClient.add("core2", doc);
            //client.commit();
            cloudSolrClient.commit("core2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value="deleteWbxUser",method = RequestMethod.GET)
    void deleteWbxUser(@RequestParam(value="id") String id){
        try {
            cloudSolrClient.deleteById("core2",id);
            cloudSolrClient.commit("core2");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @RequestMapping(value = "getUserById",method = RequestMethod.GET)
    public User getUserById(@RequestParam(value="id") String id){

        return userservice.getUserById(id);
    }

    @RequestMapping(value="findUserBuyCar")
    List<BuyCar> findUserBuyCar(@RequestParam(value="id")String id){
        return userservice.findUserBuyCar(id);
    }

    @RequestMapping(value="delBuycarByName")
    void delBuycarByName(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id){
        userservice.delBuycarByName(gid, id);
    }

    @RequestMapping(value="delOneBuycarByName")
    void delOneBuycarByName(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id){
        userservice.delOneBuycarByName(gid, id);
    }

    @RequestMapping(value="addOneBuycar")
    void addOneBuycar(@RequestParam(value="gid")String gid, @RequestParam(value="id")String id){
        userservice.addOneBuycar(gid, id);
    }

    @RequestMapping(value="delManyBuycarByName")
    void delManyBuycarByName(@RequestParam(value="gids")String gids, @RequestParam(value="id")String id){
        userservice.delManyBuycarByName(gids, id);
    }

    @RequestMapping(value="saveBuycar")
    void saveBuycar(@RequestParam(value="gid")String gid, @RequestParam(value="gnum")Integer gnum, @RequestParam(value="id")String id){
        userservice.saveBuycar(gid,gnum,id);
    }


}
