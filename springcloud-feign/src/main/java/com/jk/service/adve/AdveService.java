package com.jk.service.adve;

import com.jk.model.Coupon;
import com.jk.service.adve.AdveServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-coupon")
public interface AdveService extends AdveServiceApi {
}
