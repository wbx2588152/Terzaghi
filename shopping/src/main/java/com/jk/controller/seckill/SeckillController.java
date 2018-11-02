package com.jk.controller.seckill;

import com.alibaba.fastjson.JSON;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jk.config.AlipayConfig;
import com.jk.model.*;
import com.jk.service.CouServiceApi;
import com.jk.service.pay.PayService;
import com.jk.service.seckill.SeckilServiceApi;
import com.jk.utils.ConnectionUtils;
import com.jk.utils.IdWorker;
import com.jk.utils.UserUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alipay.api.AlipayClient;
import sun.text.resources.zh.FormatData_zh;

/**
 * @author 王超杰
 * @date 2018/10/18
 * @Description:秒杀项目前台
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {
    @Autowired
    private PayService payService;
    @Autowired
    private SeckilServiceApi seckilServiceApi;
    @Autowired
    private CouServiceApi couServiceApi;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 加载提交订单的商品列表
     * */
    @RequestMapping("queryBuyCarSaveOrderById")
    @ResponseBody
    public List<BuyCar> queryBuyCarSaveOrderById(String ids, HttpServletRequest request){
        String userId = UserUtil.getUserId(request);
        String[] split = ids.split(",");
        List<BuyCar> cartList =new ArrayList<>();
        List<BuyCar> cartList_redis = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(userId);
        //临时从数据库中调用
        List<Comm> comm= payService.getAllComm();
        for (int i =0;i<split.length;i++){
            String id = split[i].substring(1,split[i].length()-1);
            for (int j = 0; j < cartList_redis.size(); j++) {
                if(id.equals(cartList_redis.get(j).getGoodsid())){
                    cartList.add(cartList_redis.get(j));
                    break;
                }
            }
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

    /**
     * 跳转到确认订单页面
     * */
    @RequestMapping("toCarSaveOrder")
    public String toCarSaveOrder(String ids,String price,ModelMap modelMap){
        modelMap.put("ids",ids);
        modelMap.put("price",price);
        return "buycarSaveOrder";
    }


    /**
     *
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 前往支付宝第三方网关进行支付
     * Copyright: Copyright (c) 2018
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author 王超杰
     * @date 2018年10月24日 下午3:50:43
     * @version V1.0
     */
    @RequestMapping(value = "/goAlipays", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipays(String regionId,String ids,String price,String couid, HttpServletRequest request, HttpServletRequest response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());

        Date date = new Date();     //订单统一提交时间
        String[] split = ids.split(",");

        for (int i =0;i<split.length;i++){
            String id = split[i].substring(1,split[i].length()-1);
            List<BuyCar> cartList_redis = (List<BuyCar>) redisTemplate.boundHashOps("cartList").get(user.getId());
            BuyCar buyCar =new BuyCar();
            for (int j = 0; j < cartList_redis.size(); j++) {
                if(id.equals(cartList_redis.get(j).getGoodsid())){
                    buyCar= cartList_redis.get(j);
                    for (int k = 0; k < buyCar.getCount(); k++) {
                        //生成唯一订单号
                        IdWorker idWorker = new IdWorker(1, 0);
                        long idWork = idWorker.nextId();
                        String dingDanHao = String.valueOf(idWork);
                        //生成订单
                        OrderMon orderBean = new OrderMon();
                        orderBean.setOrderid(dingDanHao);
                        orderBean.setLinkuserid(user.getId());
                        orderBean.setLinkcommodifyid(buyCar.getGoodsid());   //关联商品id
                        orderBean.setLinkbothid(regionId);  //关联收货地址id
                        orderBean.setPaystatus("2");    //未付款状态
                        orderBean.setOrderstatus("1");  //未发货状态
                        orderBean.setSubtime(date);   //提交时间
                        orderBean.setCouid(couid);
                        mongoTemplate.save(orderBean);
                    }
                }
            }
        }



        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString().replaceAll("-","");
        //付款金额，必填
        String total_amount = price;
        //订单名称，必填
        String subject = "金科商品";
        //商品描述，可空
        String body = "用户订购商品个数：" ;

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }
    /**
     *  支付成功往数据库新增订单
     * */
    @RequestMapping("insertOrders")
    @ResponseBody
    public OrderMon insertOrders(HttpServletRequest request) {
        Query query = new Query();
        String userId = UserUtil.getUserId(request);
        query=Query.query(Criteria.where("linkuserid").is(userId));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"subtime")));
        List<OrderMon> find=mongoTemplate.find(query, OrderMon.class);
        System.out.println(find.get(0));
        Query query2 = new Query();
        query2=Query.query(Criteria.where("subtime").is(find.get(0).getSubtime()));
        List<OrderMon> find2=mongoTemplate.find(query2, OrderMon.class);
        System.out.println(find2.size());
        for (int i = 0; i <find2.size() ; i++) {
            OrderBean ob =new OrderBean();
            ob.setOrderid(find2.get(i).getOrderid());
            ob.setLinkuserid(find2.get(i).getLinkuserid());
            ob.setLinkcommodifyid(find2.get(i).getLinkcommodifyid());   //关联商品id
            ob.setLinkbothid(find2.get(i).getLinkbothid());  //关联收货地址id
            ob.setPaystatus("1");    //未付款状态
            ob.setOrderstatus("2");  //未发货状态
            ob.setSubtime(find2.get(i).getSubtime());   //提交时间
            seckilServiceApi.addOrderInfo(ob);
        }
        couServiceApi.deleteCoupon(find.get(0).getCouid());
        return find.get(0);
    }

    /**
     *  倒计时结束根据id修改状态 前台不展示
     * */
    @RequestMapping("deleteTimeLimit")
    @ResponseBody
    public String deleteTimeLimit(String id){
        seckilServiceApi.deleteTimeLimit(id);
        return "{}";
    }

    /**
     *  支付成功修改订单状态
     * */
    @RequestMapping("updateOrderStatus")
    @ResponseBody
    public String updateOrderStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());

        OrderBean orderBean = new OrderBean();
        orderBean.setLinkuserid("3");   //未实现登录 先用死数据
        orderBean.setPaystatus("2");    //状态2为未支付
        OrderBean orders = seckilServiceApi.queryOrderById(orderBean);
        orderBean.setPaystatus("1");
        orderBean.setOrderstatus("2");
        orderBean.setOrderid(orders.getOrderid());
        seckilServiceApi.updateOrderStatus(orderBean);
        return "{}";
    }


    /**         ,String inDetailId,String nameIdd,String shengId,String phoneId
     * 把用户秒杀的商品新增到商品表
     * */
    @RequestMapping("addCommmondityInfo")
    @ResponseBody
    public String addCommmondityInfo(String name, String artNo, String seckillPrice, String commmondityImg){
        String id = UUID.randomUUID().toString().replace("-","");
        seckilServiceApi.addCommmondityInfo(id,name,artNo,seckillPrice,commmondityImg);
        return id;
    }

    /**
     *
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 前往支付宝第三方网关进行支付
     * Copyright: Copyright (c) 2018
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author 王超杰
     * @date 2018年10月24日 下午3:50:43
     * @version V1.0
     */
    @RequestMapping(value = "/goAlipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(String seckillId,String regionId,String id,String name, String artNo, String seckillPrice, String commmondityImg,String commmondityCount, HttpServletRequest request, HttpServletRequest response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());

        //根据id查看是否是限量秒杀的商品
        SeckilCommodity seckilCommodity = seckilServiceApi.querySeckillComInfoById(seckillId);
        if(seckilCommodity != null){    //如果是限量秒杀的商品在生成订单的时候库存减一
            seckilServiceApi.updateCommInfo(seckillId);
        }else{//那就是限时秒杀表 限时秒杀已购买的数量加一且库存减一
            seckilServiceApi.updateTImeLimitById(seckillId);
        }

        //生成唯一订单号
        IdWorker idWorker = new IdWorker(1, 0);
        long idWork = idWorker.nextId();
        String dingDanHao = String.valueOf(idWork);

        //生成订单
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderid(dingDanHao);
        orderBean.setLinkuserid(user.getId());//登录id
        orderBean.setLinkcommodifyid(id);   //关联商品id
        orderBean.setLinkbothid(regionId);  //关联收货地址id
        orderBean.setPaystatus("2");    //未付款状态
        orderBean.setOrderstatus("1");  //未发货状态
        orderBean.setSubtime(new Timestamp(new Date().getTime()));   //提交时间
        seckilServiceApi.addOrderInfo(orderBean);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = dingDanHao;
        //付款金额，必填
        String total_amount = seckillPrice;
        //订单名称，必填
        String subject = name;
        //商品描述，可空
        String body = "用户订购商品个数：" + commmondityCount;

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        return result;
    }

    /**
     * 跳转到付款页面
     * */
    @RequestMapping("toPayment")
    public String toPayment(){
        return "/payment/paymentList";
    }

    /**
     * 删除收货地址
     * */
    @RequestMapping("deleteRegion")
    @ResponseBody
    public String deleteRegion(String id){
        seckilServiceApi.deleteRegion(id);
        return "{}";
    }

    /**
     * 新增收货地址
     * */
    @RequestMapping("addRegion")
    @ResponseBody
    public String addRegion(RegionBean regionBean,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());
        regionBean.setUserId(user.getId());
        regionBean.setId(UUID.randomUUID().toString().replace("-",""));
        seckilServiceApi.addRegion(regionBean);
        return "{}";
    }

    /**
     * 查询当前用户的地址
     * */
    @RequestMapping("queryRegionList")
    @ResponseBody
    public List<RegionBean> queryRegionList(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());
        String userId = user.getId();
        List<RegionBean> region = seckilServiceApi.queryRegionList(userId);
        return region;
       /* if(user != null){
            String userId = user.getId();
            List<RegionBean> region = seckilServiceApi.queryRegionList(userId);
            return region;
        }
        return null;*/
    }

    /**
     * 查询限时秒杀列表的距离秒杀时间
     * */
    @RequestMapping("queryDaoJiShi")
    @ResponseBody
    public long queryDaoJiShi(String id) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        TimeLimitSeckill timeLimitSeckill = seckilServiceApi.queryDaoJiShi(id);
        String date = simpleDateFormat.format(new Date());
        String endTime = simpleDateFormat.format(timeLimitSeckill.getEndTime());
        Date date1 = simpleDateFormat.parse(date);
        Date end = simpleDateFormat.parse(endTime);
        long  miao=(end.getTime()-date1.getTime())/1000;//除以1000是为了转换成秒
        return miao;
    }

    /**
     * 查询限量秒杀列表
     * */
    @RequestMapping("querySeckillCommodityList")
    @ResponseBody
    public List<SeckilCommodity> querySeckillCommodityList(){
        List<SeckilCommodity> list = seckilServiceApi.querySeckillCommodityList();
        return list;
    }

    /**
    /**
     * 查询限时秒杀列表
     * */
    @RequestMapping("queryTimeLimitSeckillList")
    @ResponseBody
    public List<SeckilCommodity> queryTimeLimitSeckillList(){
        List<SeckilCommodity> list = seckilServiceApi.queryTimeLimitSeckillList();
        return list;
    }

    /**
     * 跳转到支付页面  查看是否还有库存
     * */
    @RequestMapping("tosaveOrderForm")
    public String tosaveOrderForm(String id,String name, String artNo, String seckillPrice, String commmondityImg, ModelMap modelMap){

        SeckilCommodity seckilCommodity = seckilServiceApi.querySeckillComInfoById(id);
        if(seckilCommodity != null){
            if(seckilCommodity.getSurplusCount() <= 0){  //库存大于0正常执行 小于0状态前台不展示
                seckilServiceApi.deleteComInfoById(id);
                return "/seckill/seckillList";
            }
        }
        modelMap.put("id",id);
        modelMap.put("name",name);
        modelMap.put("artNo",artNo);
        modelMap.put("seckillPrice",seckillPrice);
        modelMap.put("commmondityImg",commmondityImg);
        return "/seckill/saveOrderForm";
    }

    /**
     * 跳转到秒杀页
     * */
    @RequestMapping("toSeckillList")
    public String toSeckillList(){
        return "/seckill/seckillList";
    }


    /**
     * 查询秒杀列表
     * */
    @RequestMapping("querySeckillList")
    @ResponseBody
    public List<SeckilCommodity> querySeckillList(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, SeckilCommodity seckilCommodity){
        Map<String , Object> map = seckilServiceApi.querySeckillList(page,rows, seckilCommodity);
        List<SeckilCommodity> seckilCommodities = (List<SeckilCommodity>) map.get("rows");
        System.out.println(seckilCommodities);
        return seckilCommodities;
    }


}
