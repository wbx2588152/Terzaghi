package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha")
public interface Userservice extends UserServiceApi {

}
