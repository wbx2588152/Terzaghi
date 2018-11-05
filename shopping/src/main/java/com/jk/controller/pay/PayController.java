package com.jk.controller.pay;

import com.alibaba.fastjson.JSON;
import com.jk.model.*;
import com.jk.service.CouServiceApi;
import com.jk.service.pay.PayService;
import com.jk.utils.CookieUtil;
import com.jk.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("pay")
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private CouServiceApi couServiceApi;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping("seeCarbuy")
    public String seeCarbuy(){
        return "/buycar";
    }
    @RequestMapping("getUserBuyCar")
    @ResponseBody
    public List<BuyCar> getUserBuyCar(){
        //临时从数据库中调用
        List<Comm> comm= payService.getAllComm();
        String userId = UserUtil.getUserId(request);
        String cookieValue = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
        String tips="";
        //防止报错
        if (cookieValue == null || cookieValue.equals("")) {
            cookieValue = "[]";
        }
        List<BuyCar> cartList_cookie = JSON.parseArray(cookieValue,BuyCar.class);
        if (userId.equals("")) {//如果未登录
            //从cookie中提取购物车
            for (int i = 0; i <cartList_cookie.size() ; i++) {
                for (int j = 0; j < comm.size(); j++) {
                    if(cartList_cookie.get(i).getGoodsid().equals(comm.get(j).getId())){
                        cartList_cookie.get(i).setName(comm.get(j).getNumber());
                        cartList_cookie.get(i).setPrice(comm.get(j).getPrice());
                        cartList_cookie.get(i).setPhoto(comm.get(j).getPhoto());
                    }
                }

            }
            return cartList_cookie;
        }else{//如果已登录
            //获取 redis购物车
            List<BuyCar> cartList_redis = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(userId);
            if (cartList_cookie.size()>0) {//当本地购物车存在数据则合并
                //得到合并后的购物车
                List<BuyCar> cartList = mergeCartList(cartList_cookie, cartList_redis);
                //将合并后的购物车存入 redis
                if(cartList.size()<128){
                    redisTemplate.boundHashOps("cartList").put(userId, cartList);
                    //本地购物车清除
                    CookieUtil.deleteCookie(request, response, "cartList");
                }else{
                    int x=cartList.size()-128;
                    redisTemplate.boundHashOps("cartList").put(userId, cartList_redis);
                    tips="购物车存在的商品种类已经超过上限"+x+"种！请及时清理购物车，否则可能会丢失本地的商品数据。";
                }
                for (int i = 0; i <cartList.size() ; i++) {
                    for (int j = 0; j < comm.size(); j++) {
                        if(cartList.get(i).getGoodsid().equals(comm.get(j).getId())){
                            cartList.get(i).setName(comm.get(j).getNumber());
                            cartList.get(i).setPrice(comm.get(j).getPrice());
                            cartList.get(i).setPhoto(comm.get(j).getPhoto());
                        }
                    }

                }

                return cartList;
            }
            request.setAttribute("tips",tips);
            for (int i = 0; i <cartList_redis.size() ; i++) {
                for (int j = 0; j < comm.size(); j++) {
                    if(cartList_redis.get(i).getGoodsid().equals(comm.get(j).getId())){
                        cartList_redis.get(i).setName(comm.get(j).getNumber());
                        cartList_redis.get(i).setPrice(comm.get(j).getPrice());
                        cartList_redis.get(i).setPhoto(comm.get(j).getPhoto());
                    }
                }

            }
            return cartList_redis;
        }
    }
    @RequestMapping("delBuycarByName")
    @ResponseBody
    public Boolean delBuycarByName(String gid,HttpServletRequest request){
        String userId = UserUtil.getUserId(request);
        if (userId.equals("")) {
            String cookieValue = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
            List<BuyCar> cartList_cookie = JSON.parseArray(cookieValue,BuyCar.class);
            for (int i = 0; i < cartList_cookie.size() ; i++){
                BuyCar bc1 = cartList_cookie.get(i);
                    if(gid.equals(bc1.getGoodsid())){
                        cartList_cookie.remove(i);
                    }
            }
            String cookieValue2 = JSON.toJSONString(cartList_cookie);
            CookieUtil.setCookie(request, response, "cartList", cookieValue2, 3600*24, "UTF-8");
            System.out.println("改变cookie中的值");
        }else{//如果已登录
            delSomeOnRediscarByName(gid);
        }
        return true;
    }
    @RequestMapping("delOneBuycarByName")
    @ResponseBody
    public Boolean delOneBuycarByName(String gid,HttpServletRequest request){
        String userId = UserUtil.getUserId(request);
        if (userId.equals("")) {
            String cookieValue = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
            List<BuyCar> cartList_cookie = JSON.parseArray(cookieValue,BuyCar.class);
            for (int i = 0; i < cartList_cookie.size() ; i++){
                BuyCar bc1 = cartList_cookie.get(i);
                if(gid.equals(bc1.getGoodsid())){
                    if(bc1.getCount()>1){
                        Integer num= bc1.getCount();
                        bc1.setCount(num-1);

                    }else{
                        cartList_cookie.remove(i);
                    }
                }
            }
            String cookieValue2 = JSON.toJSONString(cartList_cookie);
            CookieUtil.setCookie(request, response, "cartList", cookieValue2, 3600*24, "UTF-8");
            System.out.println("改变cookie中的值");
        }else{//如果已登录
            delOneByOnRediscarByName(gid);
        }
        return true;
    }
    @RequestMapping("delManyBuycarByName")
    @ResponseBody
    public Boolean delManyBuycarByName(String gids,HttpServletRequest request){
        String userId = UserUtil.getUserId(request);
        if (userId.equals("")) {
            String[] gidarray = gids.split(",");
            for (int i = 0; i <gidarray.length ; i++) {
                gidarray[i]=gidarray[i].substring(1,gidarray[i].length()-1);
            }

            String cookieValue = CookieUtil.getCookieValue(request, "cartList", "UTF-8");
            List<BuyCar> cartList_cookie = JSON.parseArray(cookieValue,BuyCar.class);
            for (int i = 0; i <gidarray.length ; i++) {
                for (int j = 0; j < cartList_cookie.size() ; j++){
                    BuyCar bc1 = cartList_cookie.get(j);
                    if( gidarray[i].equals(bc1.getGoodsid())){
                        cartList_cookie.remove(j);
                        break;
                    }
                }
            }
            String cookieValue2 = JSON.toJSONString(cartList_cookie);
            CookieUtil.setCookie(request, response, "cartList", cookieValue2, 3600*24, "UTF-8");
            System.out.println("改变cookie中的值");
        }else{//如果已登录
            delSomeOnRediscarByName(gids);
        }
        return true;
    }

    @RequestMapping("addOneBuycar")
    @ResponseBody
    public Boolean addOneBuycar(String gid,HttpServletRequest request){
        try {
            List<BuyCar> cartList =  getUserBuyCar();
            List<BuyCar> cartList2 =  new ArrayList<>();
            String userId = UserUtil.getUserId(request);
            if (userId.equals("")) {
                BuyCar buyCar1=new BuyCar();
                buyCar1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                buyCar1.setGoodsid(gid);
                buyCar1.setCount(1);
                buyCar1.setUserid(userId);
                cartList2.add(buyCar1);
                //如果未登录
                //将新的购物车存入cookie
                List<BuyCar> cartList3=mergeCartList(cartList,cartList2);
                String cookieValue = JSON.toJSONString(cartList3);
                CookieUtil.setCookie(request, response, "cartList", cookieValue, 3600*24, "UTF-8");
                System.out.println("存入Cookie成功");
            }else{//如果已登录
                saveBuycartoRedis(gid, 1, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存入失败");
            return false;
        }
        return true;
    }

    @RequestMapping("saveSomeBuycar")
    @ResponseBody
    public Boolean saveBuycar(String gid,Integer gnum,HttpServletRequest request){
        try {
            List<BuyCar> cartList =  getUserBuyCar();
            List<BuyCar> cartList2 =  new ArrayList<>();
            String userId = UserUtil.getUserId(request);
            if (userId.equals("")) {
                BuyCar buyCar1=new BuyCar();
                buyCar1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                buyCar1.setGoodsid(gid);
                buyCar1.setCount(gnum);
                buyCar1.setUserid(userId);
                cartList2.add(buyCar1);
                //如果未登录
                //将新的购物车存入cookie
                List<BuyCar> cartList3=mergeCartList(cartList,cartList2);
                String cookieValue = JSON.toJSONString(cartList3);
                CookieUtil.setCookie(request, response, "cartList", cookieValue, 3600*24, "UTF-8");
                System.out.println("存入Cookie成功");
            }else{//如果已登录
                saveBuycartoRedis(gid, gnum, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("存入失败");
            return false;
        }
        return true;
    }
    @RequestMapping("seeCanUseCoupon")
    public String seeCanUseCoupon(String price,HttpServletRequest request){
        request.setAttribute("price",price);
        return "seeCoupon";
    }
    @RequestMapping("queryCanUserCou")
    @ResponseBody
    public List<UserCou> queryCanUserCou(String pri) {
        List<UserCou> couList2 =new ArrayList<>();
        Date date=new Date();
        String userId = UserUtil.getUserId(request);
        List<UserCou> couList = couServiceApi.queryUserCou(userId);

        for (int i = 0; i <couList.size() ; i++) {
            if(Float.parseFloat(couList.get(i).getMan())<Float.parseFloat(pri)&&couList.get(i).getEndDate()!=null){
                if(couList.get(i).getEndDate().after(date)){
                    couList2.add(couList.get(i));
                }
            }
        }

        return couList2;
    }
    //redis操作
    private List<BuyCar> mergeCartList(List<BuyCar> cartList_cookie,List<BuyCar> cartList_redis){
        String userId = UserUtil.getUserId(request);
        for (BuyCar bc1 : cartList_cookie) {
            int flag=1;
            for(BuyCar bc2 : cartList_redis){
                //添加过程 直接重用已经做好的添加方法
                if(bc2.getGoodsid().equals(bc1.getGoodsid())){
                    Integer num= bc2.getCount();
                    bc2.setCount(num+bc1.getCount());
                    flag=2;
                    break;
                }
            }
            if(flag==1){
                cartList_redis.add(bc1);
            }
        }
        return cartList_redis;

    }
    //新增进redis
    private Boolean saveBuycartoRedis(String gid,Integer count,String id){
        BuyCar bc =new BuyCar();
        bc.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        bc.setUserid(id);
        bc.setCount(count);
        bc.setGoodsid(gid);

        List<BuyCar> carList = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(id);
        if(carList==null||carList.equals("")||carList.equals("[]")||carList.size()==0){
            List<BuyCar> carList2 =new ArrayList<>();
            carList2.add(bc);
            redisTemplate.boundHashOps("cartList").put(id, carList2);
            return true;
        }else{
            for (int i = 0; i < carList.size() ; i++) {
                BuyCar bc1 = carList.get(i);
                if(bc.getGoodsid().equals(bc1.getGoodsid())){
                    Integer num= bc1.getCount();
                    bc1.setCount(num+count);
                    redisTemplate.boundHashOps("cartList").put(id, carList);
                    return true;
                }
            }
            if(carList.size()<128){
                carList.add(bc);
                redisTemplate.boundHashOps("cartList").put(id, carList);
                return true;
            }else{
                return false;
            }
        }
    }
    private void delOneByOnRediscarByName(String gid){
        String userId = UserUtil.getUserId(request);
        List<BuyCar> carList = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(userId);
        for (int i = 0; i < carList.size() ; i++){
            BuyCar bc1 = carList.get(i);
            if(gid.equals(bc1.getGoodsid())){
                if(bc1.getCount()>1){
                    Integer num= bc1.getCount();
                    bc1.setCount(num-1);
                    redisTemplate.boundHashOps("cartList").put(userId, carList);
                }else{
                    carList.remove(i);
                    redisTemplate.boundHashOps("cartList").put(userId, carList);
                }
            }
        }
    }
    private void delSomeOnRediscarByName(String gids){
        String[] gidarray = gids.split(",");
        String userId = UserUtil.getUserId(request);
        List<BuyCar> carList = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(userId);
        for (int i = 0; i <gidarray.length ; i++) {
            for (int j = 0; j < carList.size() ; j++){
                BuyCar bc1 = carList.get(j);
                if(gidarray[i].equals(bc1.getGoodsid())){
                    carList.remove(j);
                    redisTemplate.boundHashOps("cartList").put(userId, carList);
                    break;
                }
            }
        }
    }
    @RequestMapping("seechart")
    public String seechart(){
        String userId = UserUtil.getUserId(request);
        User user = UserUtil.getUser(request);
        if(!userId.equals("")) {
            request.setAttribute("userid",userId);

            List<User> list = (List<User>) redisTemplate.boundHashOps("cartList").get("chartname");
            int flag = 1;
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId().equals(userId)) {
                        flag = 2;
                        break;
                    }
                }
            }

            if (flag == 1) {
                list.add(user);
            }
            redisTemplate.boundHashOps("cartList").put("chartname", list);
        }

        return "duihua";
    }
    @RequestMapping("toSearchPhone")
    public String toSearchPhone(String name){
        request.setAttribute("stip",name);
        return "comm/phone2";
    }
    @RequestMapping("searchSolrPhone")
    @ResponseBody
    public List<Comm> searchSolrPhone(String name){
        return payService.searchSolrPhone(name);
    }
}
