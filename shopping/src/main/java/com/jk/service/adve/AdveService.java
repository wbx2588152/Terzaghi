package com.jk.service.adve;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-coupon")
public interface AdveService extends AdveServiceApi {
}
