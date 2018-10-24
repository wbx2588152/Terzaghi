package com.jk.service;

import com.jk.model.Menu;
import com.jk.model.Role;
import com.jk.model.User;

import com.jk.service.impl.UserError;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
@FeignClient(value="service-zjw")
public interface Userservice extends LoginServiceApi {


}