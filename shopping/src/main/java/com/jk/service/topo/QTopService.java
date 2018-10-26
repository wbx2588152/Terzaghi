package com.jk.service.topo;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Auther: 王嘟嘟
 * @Date: 2018/10/26 16:23
 * @Description:
 */
@FeignClient(value="service-topo")
public interface QTopService extends TopoServerApi {
}
