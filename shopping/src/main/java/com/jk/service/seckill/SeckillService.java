package com.jk.service.seckill;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
@FeignClient(value="service-seckill")
public interface SeckillService extends SeckilServiceApi {
}
