package com.jk.service.coupon;


import com.jk.service.CouServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-zjw")
public interface CouService extends CouServiceApi {
}
