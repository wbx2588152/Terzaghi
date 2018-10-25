package com.jk.controller.seckill;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.jk.config.AlipayConfig;
import com.jk.model.*;
import com.jk.service.seckill.SeckilServiceApi;
import com.jk.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alipay.api.AlipayClient;

/**
 * @author 王超杰
 * @date 2018/10/18
 * @Description:秒杀项目前台
 */
@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private SeckilServiceApi seckilServiceApi;

    /**
     *  支付成功修改订单状态
     * */
    @RequestMapping("updateOrderStatus")
    @ResponseBody
    public String updateOrderStatus(HttpServletRequest request) {
        //HttpSession session = request.getSession();
        //User user = (User) session.getAttribute(session.getId());

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
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author 王超杰
     * @date 2018年10月24日 下午3:50:43
     * @version V1.0
     */
    @RequestMapping(value = "/goAlipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(String regionId,String id,String name, String artNo, String seckillPrice, String commmondityImg,String commmondityCount, HttpServletRequest request, HttpServletRequest response) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());

        //生成唯一订单号
        IdWorker idWorker = new IdWorker(1, 0);
        long idWork = idWorker.nextId();
        String dingDanHao = String.valueOf(idWork);

        //生成订单
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderid(dingDanHao);
        //orderBean.setLinkuserid(user.getId());
        orderBean.setLinkuserid("3");   //登录未实现先用死数据
        orderBean.setLinkcommodifyid(id);   //关联商品id
        orderBean.setLinkbothid(regionId);  //关联收货地址id
        orderBean.setPaystatus("2");    //未付款状态
        orderBean.setOrderstatus("1");  //未发货状态
        orderBean.setSubtime(new Date());   //提交时间
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
       /* HttpSession session = request.getSession();
        User user = (User) session.getAttribute(session.getId());
        regionBean.setUserid(user.getId());*/
        regionBean.setId(UUID.randomUUID().toString().replace("-",""));
        regionBean.setUserId("3");
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
        String userId = "3";
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
     * 跳转到支付页面
     * */
    @RequestMapping("tosaveOrderForm")
    public String tosaveOrderForm(String id,String name, String artNo, String seckillPrice, String commmondityImg, ModelMap modelMap){
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
