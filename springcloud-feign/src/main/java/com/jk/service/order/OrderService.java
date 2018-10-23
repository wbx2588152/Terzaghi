package com.jk.service.order;

import com.jk.model.OrderBean;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

@FeignClient(value="service-order")
public interface OrderService extends OrderServiceApI {



}
