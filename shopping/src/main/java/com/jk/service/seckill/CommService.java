package com.jk.service.seckill;

import com.jk.service.CommServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha")
public interface CommService extends CommServiceApi {
}
