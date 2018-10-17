package com.jk.controller;

        import com.jk.model.User;
        import com.jk.service.UserService;
        import org.apache.commons.lang3.StringUtils;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    @Value("${server.port}")
    String port;

    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        User result = this.userService.findByName(username);
        return result == null;
    }

}
