package com.jk.service.topo;

import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;


@FeignClient(value="service-topo")
public interface TopService  extends TopoServerApi {



}
