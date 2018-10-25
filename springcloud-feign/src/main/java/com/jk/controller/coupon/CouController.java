package com.jk.controller.coupon;

import java.util.concurrent.TimeUnit;
import com.alibaba.fastjson.JSONObject;
import com.jk.ConstantsConf;
import com.jk.model.Coupon;
import com.jk.model.User;
import com.jk.model.UserCou;
import com.jk.service.CouServiceApi;
import com.jk.shiro.FebsProperties;
import com.jk.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("cou")
public class CouController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String CODE_KEY = "_code";

    @Autowired
    private FebsProperties febsProperties;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${server.port}")
    String port;

    @Autowired
    private CouServiceApi couServiceApi;

    //跳转优惠券页面
    @RequestMapping(value = "coupon")
    public String index() {
        return "coupon/couponList";
    }

    @RequestMapping(value = "querycouponPage", method = RequestMethod.GET)
    @ResponseBody
    public String querycouponPage(int page, int limit, Coupon coupon) {
        Map<String, Object> map = new HashMap<>();
        map = couServiceApi.querycouponlist(page, limit, coupon);
        List<Coupon> list = (List<Coupon>) map.get("rows");
        int count = (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", list);

        return obj.toString();

    }

    //删除优惠券
    @RequestMapping(value = "deleteCoupon", method = RequestMethod.GET)
    @ResponseBody
    public String deleteCoupon(String id) {
        couServiceApi.deleteCoupon(id);
        return "1";
    }

    //跳转新增优惠券页面
    @RequestMapping(value = "toAddCoupon")
    public String toAddCoupon() {
        return "coupon/addcoupon";
    }

    //新增优惠券
    @RequestMapping(value = "addCoupon", method = RequestMethod.POST)
    @ResponseBody
    public String addCoupon(Coupon coupon) {
        coupon.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        couServiceApi.addCoupon(coupon);
        return "{}";
    }

    //跳转优惠券修改页面
    @RequestMapping(value = "toEditCou", method = RequestMethod.GET)
    public String toEditCou(@RequestParam(value = "id") String id, HttpServletRequest request) {
        Coupon coupon = couServiceApi.queryCouById(id);
        request.setAttribute("cou", coupon);
        return "coupon/editcoupon";
    }

    //修改优惠券
    @RequestMapping(value = "updateCou", method = RequestMethod.POST)
    @ResponseBody
    public String updateCou(Coupon coupon) {
        couServiceApi.updateCou(coupon);
        return "1";
    }

    //跳转登录界面
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login2";
    }

    //账号密码登录

    /**
     * 登录
     */

    //登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(User user, String code, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        //判断验证码
        if (!StringUtils.isNotBlank(code)) {
            result.put("code", 1);
            result.put("msg", "验证码不能为空");
            return result;
        }
        Session session = SecurityUtils.getSubject().getSession();
        String sessionCode = (String) session.getAttribute(CODE_KEY);
        if (!code.equalsIgnoreCase(sessionCode)) {
            result.put("code", 2);
            result.put("msg", "验证码错误");
            return result;
        }
        //通过登录账号获取账号信息
        User userInfo = couServiceApi.getUserInfo(user);
        if (userInfo == null) {
            result.put("code", 3);
            result.put("msg", "账号不存在");
            return result;
        }
        //判断密码是否一致
        String password = user.getPassword();
        //加密前台传过来的密码
        if (!userInfo.getPassword().equals(password)) {
            result.put("code", 4);
            result.put("msg", "密码错误");
            return result;
        }
        session.setAttribute(session.getId(), userInfo);
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
    }

    @GetMapping(value = "gifCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            Captcha captcha = new GifCaptcha(
                    febsProperties.getValidateCode().getWidth(),
                    febsProperties.getValidateCode().getHeight(),
                    febsProperties.getValidateCode().getLength());
            HttpSession session = request.getSession(true);
            captcha.out(response.getOutputStream());
            session.removeAttribute(CODE_KEY);
            session.setAttribute(CODE_KEY, captcha.text().toLowerCase());
        } catch (Exception e) {
            log.error("图形验证码生成失败", e);
        }
    }


    /**
     * 获取验证码
     */
    @RequestMapping("queryVerificationCode")
    @ResponseBody
    public Map<String, Object> queryVerificationCode(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, Object> result = new HashMap<>();
        User userInfo = couServiceApi.queryUserInfoByPhone(user);
        User user1 = new User();
        if (user.getPhoneNumber() != null && user.getPhoneNumber() != "" && !user.getPhoneNumber().equals("")) {
            //判断手机号是否在用户列表中  手机号未注册注册的话新增用户
            if (userInfo == null) {
              /*  user.setId(UUID.randomUUID().toString().replace("-", ""));
                couServiceApi.addUserByPhone(user);
                user1.setId(user.getId());*/
                result.put("code", 1);
                result.put("msg", "请去注册手机号");
                return result;
            } else {
                user1.setId(userInfo.getId());
            }
            //缓存验证码key 保证唯一 特定的自负前面加上手机号
            String cacheKey = ConstantsConf.VERIFICATION_CODE + user.getPhoneNumber();
            //缓存一分钟锁 点击验证码一分钟之内不可以重复获取
            String cacheFlagKey = ConstantsConf.PHOEO_CODE_FLAG + user.getPhoneNumber();
            String lock = redisTemplate.opsForValue().get(cacheFlagKey);
            if (!StringUtil.isNotEmpty(lock)) {
                HashMap<String, Object> params = new HashMap<>();
                params.put("accountSid", ConstantsConf.ACCOUNT_SID);
                params.put("templateid", ConstantsConf.TEMPLATE_ID);
                int verificationCode = (int) (100000 + Math.random() * 899999);
                user.setVerificationCode(verificationCode);
                user.setPhoneNumber(user.getPhoneNumber());
                params.put("param", "" + verificationCode + ",5");
                params.put("to", user.getPhoneNumber());
                String timestamap = TimeUtil.format(new Date());
                params.put("timestamp", timestamap);
                String sign = Md5Util.getMd532(ConstantsConf.ACCOUNT_SID + ConstantsConf.AUTH_TOKEN + timestamap);
                params.put("sig", sign);
                HttpClientUtil.post(ConstantsConf.SMS_URL, params);
                redisTemplate.opsForValue().set(cacheKey, String.valueOf(verificationCode), ConstantsConf.PHOEO_CODE_TIME_OUT, TimeUnit.MINUTES);
                redisTemplate.opsForValue().set(cacheFlagKey, "lock", 1, TimeUnit.MINUTES);
                session.setAttribute(session.getId(), user);
                result.put("code", 0);
                result.put("msg", "请在手机查看!");
                return result;
            } else {
                result.put("code", 3);
                result.put("msg", "请60秒后再进行获取!");
                return result;
            }
        } else {
            result.put("code", 2);
            result.put("msg", "获取验证码失败!");
            return result;
        }
    }

    @RequestMapping("loginPhone")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, User user) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        User users = SessionUserUtil.getUserInfo(request);
        if (users != null) {
            if (!user.getVerificationCode().equals(users.getVerificationCode())) {
                result.put("code", 3);
                result.put("msg", "手机号或验证码错误!");
                return result;
            }
            if (!user.getPhoneNumber().equals(users.getPhoneNumber())) {
                result.put("code", 1);
                result.put("msg", "手机号或验证码错误");
                return result;
            }
            result.put("code", 0);
            result.put("msg", "登录成功!");
            result.put("user", users);
            HttpSession session = request.getSession();
            session.setAttribute(session.getId(), users);
            return result;
        } else {
            result.put("code", 1);
            result.put("msg", "手机号或验证码错误");
            return result;
        }
    }

    //跳转注册页面
    @RequestMapping("toReg")
    public String toReg() {
        return "reg";
    }

    //注册新的用户
    @RequestMapping(value = "regUser", method = RequestMethod.POST)
    @ResponseBody
    public String regUser(User user) {
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        couServiceApi.regUser(user);
        return "{}";
    }

    //跳转首页
    @RequestMapping(value = "toIndex")
    public String toIndex() {
        return "beforeindex";
    }

    //获取登录人的信息
    @RequestMapping(value = "getUsername")
    @ResponseBody
    public String getUsername(HttpServletRequest request, Model model, User user) {
        HttpSession session = request.getSession();
        User attribute = (User) session.getAttribute(session.getId());
        String name = attribute.getName();
        request.setAttribute("name", name);
        return name;
    }

    //退出
    @RequestMapping("exit")

    public String DeleteSessionId(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute(session.getId());
        return "beforeindex";

    }

    //查看session是否有用户id  更改导航条的状态
    @RequestMapping(value = "queryNavById")
    @ResponseBody
    public Map<String, Object> queryNavById(User user, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User userId = (User) session.getAttribute(session.getId());
        if (userId != null) {
            map.put("code", 0);
            map.put("loginname", user.getLoginname());
            return map;
        } else {
            map.put("code", 1);
        }
        return map;


    }

    //跳转我的优惠券页面
    @RequestMapping(value = "toCoupon")
    public String toCoupon() {
        return "coupon/coupon";
    }

    //查询我的优惠券
    @RequestMapping(value = "queryCouList", method = RequestMethod.POST)
    @ResponseBody
    public List<Coupon> queryCouList(Coupon coupon) {

        List<Coupon> couList = couServiceApi.queryCouList(coupon);

        return couList;
    }

    //用户领取优惠券
    @RequestMapping(value = "addCouByUser", method = RequestMethod.POST)
    @ResponseBody
    public String addCouByUser(UserCou userCou, String id, HttpServletRequest request) {
       HttpSession session = request.getSession();
        User user = (User)session.getAttribute(session.getId());
        userCou.setUserId(user.getId());
        userCou.setCouId(id);
        userCou.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        couServiceApi.addCouByUser(userCou);


      /*  Coupon coupon = new Coupon();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,+7);
        coupon.setEndDate(cal.getTime());
*/

        return "{}";
    }


    //显示已经拥有的优惠券和未领取的优惠券
    @RequestMapping(value = "queryUserCou")
    @ResponseBody
    public Map<String,Object> queryUserCou(UserCou userCou, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        User users = (User) session.getAttribute(session.getId());
        List<UserCou> userCous = couServiceApi.queryUserCou(users.getId());
       for (int i = 0; i < userCous.size(); i++) {
            UserCou userCou1 = userCous.get(i);
            if (!users.getId().equals(userCou1.getUserId())) {
                map.put("code", 1);

            } else {
                map.put("code", 0);


            }
        }
         map.put("userCou1",userCous);
        return map;
    }







}


