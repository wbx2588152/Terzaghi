package com.jk.service;

import com.jk.mapper.CommMapper;

import com.jk.model.Comm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommServiceImpl implements CommServiceApi {


    @Autowired
    private CommMapper commDao;


                    /**
                     *
                     * 后台页面
                     *
                     */
    @Override
    public Map<String, Object> queryComm(int page, int rows, @RequestBody Comm comm) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryComm(comm);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Comm> commList = commDao.queryCoomList(start,end,comm);
        map.put("total",count);
        map.put("rows",commList);
        return map;
    }

    @Override
    public void saveComm(@RequestBody Comm comm) {
        commDao.saveComm(comm);
    }

    @Override
    public Comm queryCommById(String id) {

        return commDao.queryCommById(id);
    }

    @Override
    public void updateComm(@RequestBody Comm comm) {

        commDao.updateComm(comm);
    }

    @Override
    public int deleteAllComm(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllComm(split);
    }

    @Override
    public void deleteComm(String id) {

         commDao.deleteComm(id);
    }

    @Override
    public void updateAudit2(@RequestBody Comm comm) {

        commDao.updateAudit2(comm);
    }

    @Override
    public void updateAudit1(@RequestBody Comm comm) {

        commDao.updateAudit1(comm);
    }

    @Override
    public void updateAdded(@RequestBody Comm comm) {
        commDao.updateAdded(comm);
    }

    @Override
    public Comm queryDetail(String id) {
        return commDao.queryDetail(id);
    }

    @Override
    public void updateLabel(@RequestBody Comm comm) {

        commDao.updateLabel(comm);
    }

    @Override
    public List<Color> queryColour() {
        return commDao.queryColour();
    }

    @Override
    public List<Phone> queryPhone() {
        List<Phone> phones=commDao.queryPhone();
        return phones;
    }

    @Override
    public List<Models> queryModels(@RequestBody Phone phone) {
        List<Models> x =commDao.queryModels(phone);
        return x;
    }

    @Override
    public List<Spec> querySpec() {
        return commDao.querySpec();
    }

    @Override
    public void saveModels(@RequestBody Models models) {
        commDao.saveModels(models);
    }

    @Override
    public Phone queryPhone2(String id) {
        return commDao.queryPhone2(id);
    }

    @Override
    public Map<String, Object> queryPhoneAll(int page, int rows, @RequestBody Phone phone) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryPhoneAll(phone);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Comm> phonelist = commDao.queryPhoneList(start,end,phone);
        map.put("total",count);
        map.put("rows",phonelist);
        return map;
    }

    @Override
    public Map<String, Object> queryModlesAll(int page, int rows, @RequestBody Models models) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryModlesAll(models);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Comm> modelslist = commDao.queryModlesList(start,end,models);
        map.put("total",count);
        map.put("rows",modelslist);
        return map;
    }

    @Override
    public void deleteModels(String id) {
        commDao.deleteModels(id);
    }

    @Override
    public Models queryModelsById(String id) {
        return commDao.queryModelsById(id);
    }

    @Override
    public void updateModels(@RequestBody Models models) {
        commDao.updateModels(models);
    }

    @Override
    public int deleteAllModels(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllModels(split);
    }

    @Override
    public void savePhone(@RequestBody Phone phone) {

        commDao.savePhone(phone);
    }

    @Override
    public int deleteAllPhone(String ids) {

        String[] split = ids.split(",");

        return commDao.deleteAllPhone(split);
    }

    @Override
    public Phone queryPhoneById(String id) {

        return commDao.queryPhoneById(id);
    }

    @Override
    public void updatePhone(@RequestBody Phone phone) {

        commDao.updatePhone(phone);
    }

    @Override
    public void deletePhone(String id) {

        commDao.deletePhone(id);
    }

    @Override
    public Map<String, Object> queryFood(int page, int rows, @RequestBody Food food) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryFood(food);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Food> foodList = commDao.queryFoodList(start,end,food);
        map.put("total",count);
        map.put("rows",foodList);
        return map;
    }

    @Override
    public void updateAddedf(@RequestBody Food food) {
        commDao.updateAddedf(food);
    }

    @Override
    public void updateLabelf(@RequestBody Food food) {

        commDao.updateLabelf(food);
    }

    @Override
    public List<FoodType> queryTypeAll() {
        return commDao.queryTypeAll();
    }

    @Override
    public List<FoodUnit> queryUnitAll() {
        return commDao.queryUnitAll();
    }

    @Override
    public void saveFood(@RequestBody Food food) {
        commDao.saveFood(food);
    }

    @Override
    public int deleteAllFood(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllFood(split);

    }

    @Override
    public Food queryFoodById(String id) {
        return commDao.queryFoodById(id);
    }

    @Override
    public void updateFood(@RequestBody Food food) {

        commDao.updateFood(food);
    }

    @Override
    public void deleteFood(String id) {
        commDao.deleteFood(id);
    }

    @Override
    public Map<String, Object> queryMan(int page, int rows, @RequestBody Man man) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryMan(man);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Food> manList = commDao.queryManList(start,end,man);
        map.put("total",count);
        map.put("rows",manList);
        return map;

    }

    @Override
    public void updateManAdded(@RequestBody Man man) {

        commDao.updateManAdded(man);
    }

    @Override
    public List<ManType> queryTypeMan() {
        return commDao.queryTypeMan();
    }

    @Override
    public void updateLabelm(@RequestBody Man man) {
        commDao.updateLabelm(man);
    }

    @Override
    public void saveMan(@RequestBody Man man) {
        commDao.saveMan(man);
    }

    @Override
    public int deleteAllMan(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllMan(split);
    }

    @Override
    public Man queryManById(String id) {
        return commDao.queryManById(id);
    }

    @Override
    public void updateMan(@RequestBody Man man) {
        commDao.updateMan(man);
    }

    @Override
    public void deleteMan(String id) {
        commDao.deleteMan(id);
    }

    @Override
    public Map<String, Object> queryManAll(int page, int rows, @RequestBody ManType mantype) {
        HashMap<String, Object> map = new HashMap<>();
        long count = commDao.queryManAll(mantype);
        int start = (page - 1)*rows;
        int end = start + rows;
        List<Food> manList = commDao.queryManAllList(start,end,mantype);
        map.put("total",count);
        map.put("rows",manList);
        return map;
    }

    @Override
    public void saveManType(@RequestBody ManType manType) {
        commDao.saveManType(manType);
    }

    @Override
    public int deleteAllManType(String ids) {
        String[] split = ids.split(",");
        return commDao.deleteAllManType(split);
    }

    @Override
    public ManType queryManTypeById(String id) {
        return commDao.queryManTypeById(id);
    }

    @Override
    public void updateManType(@RequestBody ManType manType) {
        commDao.updateManType(manType);
    }

    @Override
    public void deleteManType(String id) {

        commDao.deleteManType(id);
    }

    @Override
    public void saveDetail(@RequestBody Detail detail) {

        commDao.saveDetail(detail);
    }

    @Override
    public void saveFoodDetail(@RequestBody FoodDetail foddetail) {
        commDao.saveFoodDetail(foddetail);
    }

    @Override
    public void saveManDetail(@RequestBody ManDetail manDetail) {
        commDao.saveManDetail(manDetail);
    }




    /**
     *
     * 前台页面
     *
     */

    @Override
    public List<Comm> getComm() {
        return commDao.getComm();
    }

    @Override
    public Comm getDetail(String id) {
        return commDao.getDetail(id);
    }

    @Override
    public Detail getDetail2(String id) {
        return commDao.getDetail2(id);
    }

    @Override
    public List<Man> getMan() {
        return commDao.getMan();
    }

    @Override
    public List<Food> getFood() {
        return commDao.getFood();
    }

}
