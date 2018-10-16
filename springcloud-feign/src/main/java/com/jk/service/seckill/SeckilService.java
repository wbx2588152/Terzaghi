package com.jk.service;

import com.jk.service.impl.UserError;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 王超杰
 * @date 2018/10/16
 * @Description:
 */
@FeignClient(value="service-ha",fallback = UserError.class)
public interface SeckilService {

}
