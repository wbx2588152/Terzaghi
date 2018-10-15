package com.jk.service;

import com.jk.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "haError")
    public List<User> haService() {
        List<User> forObject = restTemplate.getForObject("http://SERVICE-HA/wbx/ha", List.class);

        return forObject;
    }

    public List<User> haError(){
        List<User> list=new ArrayList<>();
        User user =new User();
        user.setMycode("sorry,error!");
        list.add(user);
        return list;
    }

}
