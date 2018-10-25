package com.jk.service;


import com.jk.model.Comm;

import com.jk.model.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

import java.util.Map;

public interface CommServiceApi {

    @RequestMapping(value = "/queryComm", method = RequestMethod.POST)
    Map<String, Object> queryComm(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, @RequestBody Comm comm);

    @RequestMapping(value = "/saveComm", method = RequestMethod.POST)
    void saveComm(@RequestBody Comm comm);

    @RequestMapping(value = "/queryCommById", method = RequestMethod.GET)
    Comm queryCommById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateComm", method = RequestMethod.POST)
    void updateComm(@RequestBody Comm comm);

    @RequestMapping(value = "/deleteAllComm", method = RequestMethod.POST)
    int deleteAllComm(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/deleteComm", method = RequestMethod.GET)
    void deleteComm(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateAudit2", method = RequestMethod.POST)
    void updateAudit2(@RequestBody Comm comm);

    @RequestMapping(value = "/updateAudit1", method = RequestMethod.POST)
    void updateAudit1(@RequestBody Comm comm);

    @RequestMapping(value = "/updateAdded", method = RequestMethod.POST)
    void updateAdded(@RequestBody Comm comm);

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    Comm queryDetail(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateLabel", method = RequestMethod.POST)
    void updateLabel(@RequestBody Comm comm);

    @RequestMapping(value = "/queryColour", method = RequestMethod.POST)
    List<Color> queryColour();

    @RequestMapping(value = "/queryPhone", method = RequestMethod.POST)
    List<Phone> queryPhone();

    @RequestMapping(value = "/queryModels", method = RequestMethod.POST)
    List<Models> queryModels(@RequestBody Phone phone);

    @RequestMapping(value = "/querySpec", method = RequestMethod.POST)
    List<Spec> querySpec();

}
