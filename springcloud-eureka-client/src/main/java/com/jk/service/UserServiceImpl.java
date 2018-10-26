package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.*;
import com.netflix.discovery.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
        String[] roleidAll = user.getRoleid().split(",");
        List<UserRole> list = new ArrayList<UserRole>();
        for (int i = 0; i < roleidAll.length; i++) {
            UserRole userrBean = new UserRole();
            userrBean.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            userrBean.setRoleid(roleidAll[i]);
            userrBean.setUserid(user.getId());
            list.add(userrBean);
        }
        userMapper.saveUserRole(list);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteUser(id);
        userMapper.deleteUserRole(id);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);

        String roles = user.getRoleid();
        String[] split = roles.split(",");
        userMapper.delManyUsertoUserId(user);
        for (int i = 0; i < split.length; i++) {
            UserRole utr =new UserRole();
            utr.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            utr.setRoleid(split[i]);
            utr.setUserid(user.getId());
            userMapper.saveUsertoRole(utr);
        }
    }

    public List<Power> getAllNav(HttpServletRequest request) {
        // TODO Auto-generated method stub
/*        HttpSession session = request.getSession();
        Users attribute = (Users)session.getAttribute(session.getId());*/
        String userId = "1";
        List<Power> powerlist0=new ArrayList<Power>();
        Power power=new Power();
        List<Power> powerlist=userMapper.getpowerlist(userId);
        List<Power> powerlist2=powerlist;
        for(int i=0;i<powerlist.size();i++){
            List<Power> powsarr=new ArrayList<Power>();
            if(powerlist.get(i).getPid().equals("0")){
                for(int j=0;j<powerlist2.size();j++){
                    String x=powerlist.get(i).getId();
                    String y=powerlist2.get(j).getPid();
                    if(x.equals(y)){
                        powsarr.add(powerlist2.get(j));
                    }
                }
                powerlist.get(i).setChildren(powsarr);
                powerlist0.add(powerlist.get(i));
            }
        }
        return powerlist0;
    }

    @Override
    public List<Users> seeUserList() {
        return userMapper.seeUserList();
    }

    @Override
    public List<Menu> selectAllMenu() {
        return userMapper.selectAllMenu();
    }

    @Override
    public List<Role> queryRole() {
        return userMapper.queryRole();
    }

    @Override
    public List<Role> queryRole2(String id) {
        return userMapper.queryRole2(id);
    }

    @Override
    public Map<String, Object> queryUser(int page, int rows, User user) {
        // TODO Auto-generated method stub
        HashMap<String,Object> map = new HashMap<String,Object>();
        long count = userMapper.queryUser(user);
        int start = (page - 1)*rows;
        int end = start + rows;
        //查询出所有角色
        List<Role> roleListAll = userMapper.queryRoleList();
        List<User> userlist = userMapper.queryUserList(start,end,user);
        for (User user2 : userlist) {
            String roleName = "";
            String[] split = user2.getRoleid().split(",");
            for (String roleid : split) {
                for (Role roleBean2 : roleListAll) {
                    if (roleid.equals(roleBean2.getId())) {
                        roleName += roleName == "" ? roleBean2.getName() : ","+roleBean2.getName();
                    }
                }
            }
            user2.setRolename(roleName);
        }
        map.put("total", count);
        map.put("rows", userlist);
        return map;
    }

    @Override
    public User queryUserById(String id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public Map<String, Object> getRoleList(Integer page, Integer rows, Role role) {
        Map<String,Object> map = new HashMap<>();
        Integer total = userMapper.getRoleTotal(role);
        Integer start = (page-1)*rows;
        Integer end = start+rows;
        List<Role> rlist = userMapper.getRoleList(start,end,role);
        map.put("total", total);
        map.put("rows", rlist);
        return map;
    }

    @Override
    public List<Power> getNavListByRoleId(String roleId) {
        //查询出角色所拥有的权限
        List<RolePower> roleNavs =  userMapper.getNavIdListByRoleId(roleId);

        String id = "0";
        List<Power> navs = navNode(roleId,id,roleNavs);
        return navs;
    }

    private List<Power> navNode(String roleId,String id, List<RolePower> roleNavs) {
        HashMap<String, Object> params = new HashMap<String,Object>();
        params.put("roleId", roleId);
        params.put("id", id);
        List<Power> navs = userMapper.getNavListByRoleId(params);
        for(Power nav : navs) {
            for (RolePower roleNav : roleNavs) {
                if (nav.getId().equals(roleNav.getPowerId())) {
                    nav.setChecked(true);
                    break;
                }else {
                    nav.setChecked(false);
                }
            }
            List<Power> navList = navNode(roleId, nav.getId(),roleNavs);
            nav.setChildren(navList);

        }
        return navs;
    }

    @Override
    public void saveRoleNav(String roleId, String ids) {
//删除原有的权限
        userMapper.delRoleNavByRoleId(roleId);

        //添加新的权限
        ArrayList<RolePower> arrayList = new ArrayList<RolePower>();
        String[] navIdArr = ids.split(",");
        for (int i = 0; i < navIdArr.length; i++) {
            RolePower roleNav = new RolePower();
            roleNav.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            roleNav.setRoleId(roleId);
            roleNav.setPowerId(navIdArr[i]);
            arrayList.add(roleNav);

        }
        userMapper.saveRoleNav(arrayList);
    }

    @Override
    public void addRole(Role role) {
        userMapper.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        userMapper.updateRole(role);
    }

    @Override
    public Role toEditRole(Role role) {
        return userMapper.toEditRole(role);
    }

    @Override
    public void deleteRole(Role role) {
        userMapper.deleteRole(role);
    }

    @Override
    public void delOneNav(String id) {
        List<String> ids=new ArrayList<String>();
        ids.add(id);
        delnavNode(id,ids);
        userMapper.delManyNav(ids);
    }

    private List<String> delnavNode(String id, List<String> ids) {
        // TODO Auto-generated method stub
        List<Power> lists=userMapper.getNavALLById(id);
        for(Power x:lists) {
            delnavNode(x.getId(),ids);
            ids.add(x.getId());
        }
        return ids;
    }

    @Override
    public void editOneNav(Power power) {
        userMapper.editOneNav(power);
    }

    @Override
    public void saveOneNav(Power power) {
        power.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        userMapper.saveOneNav(power);
    }

    @Override
    public void addOneMenu(Menu menu) {
        menu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        menu.setCreateTime(format);
        userMapper.addOneMenu(menu);
    }

    @Override
    public void delmanyMenu(String ids) {
        String[] id = ids.split(",");
        userMapper.delmanyMenu(id);
    }

    @Override
    public void editOneMenu(Menu menu) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        menu.setCreateTime(format);
        userMapper.editOneMenu(menu);
    }

    @Override
    public Menu getOneMenu(String id) {
        return userMapper.getOneMenu(id);
    }

    @Override
    public ResultPage queryMenuList(Menu menu) {
        ResultPage resultPage = new ResultPage();
        int count=userMapper.querygetMenuCountByNavid(menu);
        resultPage.setTotal(count);
        List<Menu> list=userMapper.getMenuListByNavid(menu);
        resultPage.setRows(list);
        return resultPage;
    }

    @Override
    public List<Power> getALLNav() {
        List<Power> navs=new ArrayList<Power>();
        Power root=new Power();
        root.setId("0");
        root.setText("根节点");
        List<Power> navs2=navNode2("0");
        root.setChildren(navs2);
        navs.add(root);
        return navs;
    }

    private List<Power> navNode2(String i) {
        // TODO Auto-generated method stub
        List<Power> lists=userMapper.getNavALLById(i);
        for(Power x:lists) {
            List<Power> lists2=navNode2(x.getId());
            x.setChildren(lists2);
        }
        return lists;
    }

    @Override
    public List<BuyCar> findUserBuyCar(String id) {
        return userMapper.findUserBuyCar(id);
    }

    @Override
    public void delBuycarByName(String gid, String id) {
        userMapper.delBuycarByName(gid,id);
    }

    @Override
    public void delOneBuycarByName(String gid, String id) {
        List<BuyCar> list = userMapper.getBuycarsList(gid,id);
        userMapper.delOneBuyCar(list.get(0).getId());
    }

    @Override
    public void addOneBuycar(String gid, String id) {
        BuyCar buyCar=new BuyCar();
        buyCar.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        buyCar.setGoodsid(gid);
        buyCar.setUserid(id);
        userMapper.addOneBuycar(buyCar);
    }

    @Override
    public void delManyBuycarByName(String gids, String id) {
        userMapper.delManyBuycarByName(gids, id);
    }

    @Override
    public void saveBuycar(String gid, Integer gnum, String id) {
        for (int i = 0; i <gnum ; i++) {
            BuyCar buyCar=new BuyCar();
            buyCar.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            buyCar.setGoodsid(gid);
            buyCar.setUserid(id);
            userMapper.addOneBuycar(buyCar);
        }
    }


}
