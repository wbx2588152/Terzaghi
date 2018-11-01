package com.jk.service.FrontOrder;


import com.jk.service.order.OrderServiceApI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="service-order")
public interface FrontService extends OrderServiceApI {
}
