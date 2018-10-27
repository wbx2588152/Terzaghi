package com.jk.service;

import com.jk.model.User;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha")
public interface PowerService extends UserServiceApi{


}
