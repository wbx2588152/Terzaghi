package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    public String haService(String name) {
        return restTemplate.getForObject("http://SERVICE-HA/wbx/ha?name="+name,String.class);
    }

}
