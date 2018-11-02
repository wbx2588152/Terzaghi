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

    @RequestMapping(value = "/getComm", method = RequestMethod.POST)
    List<Comm> getComm();

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    Comm getDetail(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/saveModels", method = RequestMethod.POST)
    void saveModels(@RequestBody Models models);

    @RequestMapping(value = "/queryPhone2", method = RequestMethod.POST)
    Phone queryPhone2(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/queryPhoneAll", method = RequestMethod.POST)
    Map<String, Object> queryPhoneAll(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, @RequestBody Phone phone);

    @RequestMapping(value = "/queryModlesAll", method = RequestMethod.POST)
    Map<String, Object> queryModlesAll(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody Models models);

    @RequestMapping(value = "/deleteModels", method = RequestMethod.GET)
    void deleteModels(@RequestParam(value ="id") String id);

    @RequestMapping(value = "/queryModelsById", method = RequestMethod.POST)
    Models queryModelsById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateModels", method = RequestMethod.POST)
    void updateModels(@RequestBody Models models);

    @RequestMapping(value = "/deleteAllModels", method = RequestMethod.POST)
    int deleteAllModels(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/savePhone", method = RequestMethod.POST)
    void savePhone(@RequestBody Phone phone);

    @RequestMapping(value = "/deleteAllPhone", method = RequestMethod.POST)
    int deleteAllPhone(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/queryPhoneById", method = RequestMethod.POST)
    Phone queryPhoneById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    void updatePhone(@RequestBody Phone phone);

    @RequestMapping(value = "/deletePhone", method = RequestMethod.GET)
    void deletePhone(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/queryFood", method = RequestMethod.POST)
    Map<String, Object> queryFood(@RequestParam(value = "page") int page, @RequestParam(value = "rows")int rows, @RequestBody Food food);

    @RequestMapping(value = "/updateAddedf", method = RequestMethod.POST)
    void updateAddedf(@RequestBody Food food);

    @RequestMapping(value = "/updateLabelf", method = RequestMethod.POST)
    void updateLabelf(@RequestBody Food food);

    @RequestMapping(value = "/queryTypeAll", method = RequestMethod.POST)
    List<FoodType> queryTypeAll();

    @RequestMapping(value = "/queryUnitAll", method = RequestMethod.POST)
    List<FoodUnit> queryUnitAll();

    @RequestMapping(value = "/saveFood", method = RequestMethod.POST)
    void saveFood(@RequestBody Food food);

    @RequestMapping(value = "/deleteAllFood", method = RequestMethod.POST)
    int deleteAllFood(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/queryFoodById", method = RequestMethod.POST)
    Food queryFoodById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateFood",method = RequestMethod.POST)
    void updateFood(@RequestBody Food food);

    @RequestMapping(value = "/updateFood",method = RequestMethod.GET)
    void deleteFood(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/queryMan",method = RequestMethod.POST)
    Map<String, Object> queryMan(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, @RequestBody Man man);

    @RequestMapping(value = "/updateManAdded",method = RequestMethod.POST)
    void updateManAdded(@RequestBody Man man);

    @RequestMapping(value = "/queryTypeMan",method = RequestMethod.POST)
    List<ManType> queryTypeMan();

    @RequestMapping(value = "/updateLabelm",method = RequestMethod.POST)
    void updateLabelm(@RequestBody Man man);

    @RequestMapping(value = "/saveMan",method = RequestMethod.POST)
    void saveMan(@RequestBody Man man);

    @RequestMapping(value = "/deleteAllMan",method = RequestMethod.POST)
    int deleteAllMan(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/queryManById",method = RequestMethod.POST)
    Man queryManById(@RequestParam("id") String id);

    @RequestMapping(value = "/updateMan",method = RequestMethod.POST)
    void updateMan(@RequestBody Man man);

    @RequestMapping(value = "/deleteMan",method = RequestMethod.GET)
    void deleteMan(@RequestBody String id);

    @RequestMapping(value = "/queryManAll",method = RequestMethod.POST)
    Map<String, Object> queryManAll(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows, @RequestBody ManType mantype);

    @RequestMapping(value = "/saveManType",method = RequestMethod.POST)
    void saveManType(@RequestBody ManType manType);

    @RequestMapping(value = "/deleteAllManType",method = RequestMethod.POST)
    int deleteAllManType(@RequestParam(value = "ids") String ids);

    @RequestMapping(value = "/queryManTypeById",method = RequestMethod.POST)
    ManType queryManTypeById(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/updateManType",method = RequestMethod.POST)
    void updateManType(@RequestBody ManType manType);

    @RequestMapping(value = "/deleteManType",method = RequestMethod.GET)
    void deleteManType(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/saveDetail",method = RequestMethod.POST)
    void saveDetail(@RequestBody Detail detail);

    @RequestMapping(value = "/saveFoodDetail",method = RequestMethod.POST)
    void saveFoodDetail(@RequestBody FoodDetail foddetail);

    @RequestMapping(value = "/saveManDetail",method = RequestMethod.POST)
    void saveManDetail(@RequestBody ManDetail manDetail);

    @RequestMapping(value = "/getDetail2",method = RequestMethod.POST)
    Detail getDetail2(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/getMan",method = RequestMethod.POST)
    List<Man> getMan();

    @RequestMapping(value = "/getFood",method = RequestMethod.POST)
    List<Food> getFood();

    @RequestMapping(value = "/getFoodDetail",method = RequestMethod.POST)
    Food getFoodDetail(@RequestParam(value = "id") String id);

    @RequestMapping(value = "/getManDetail",method = RequestMethod.POST)
    Man getManDetail(@RequestParam(value = "id") String id);
}
