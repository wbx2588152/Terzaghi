package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-ha2")
public interface CommService extends CommServiceApi {
}
