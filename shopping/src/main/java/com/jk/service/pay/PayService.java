package com.jk.service.pay;

import com.jk.service.UserServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha")
public interface PayService extends UserServiceApi {
}
