package com.jk.controller;

        import com.jk.model.Menu;
        import com.jk.model.Role;
        import com.jk.model.User;
        import com.jk.service.UserService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userservice;

    private static final String ON = "on";

    @Value("${server.port}")
    String port;

    @RequestMapping(value="user/findUserByName",method = RequestMethod.POST)
    @ResponseBody
    public User findUserByName(@RequestParam(value="userName")String username) {
        return userservice.findUserByName(username);
    }


    //跳转优惠券页面
    @GetMapping("user/coupon")
    public String index() {
        return "coupon/couponList";
    }

    @RequestMapping(value = "user/findUserRole",method = RequestMethod.POST)
    @ResponseBody
    List<Role> findUserRole(@RequestParam(value="userName") String userName){
        return userservice.findUserRole(userName);
    }
    @RequestMapping(value="user/findUserPermissions",method = RequestMethod.POST)
    @ResponseBody
    List<Menu> findUserPermissions(@RequestParam(value="userName") String userName){
        return userservice.findUserPermissions(userName);
    }
    @RequestMapping(value="user/updateLoginTime",method = RequestMethod.POST)
    @ResponseBody
    void updateLoginTime(String loginname){
        userservice.updateLoginTime(loginname);
    }

    @RequestMapping(value="user/selectAllMenu",method = RequestMethod.POST)
    List<Menu> selectAllMenu(){
        return userservice.selectAllMenu();
    }
}
