package com.jk.service;

import com.jk.service.impl.UserError;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha",fallback = UserError.class)
public interface Userservice extends UserServiceApi {

}
