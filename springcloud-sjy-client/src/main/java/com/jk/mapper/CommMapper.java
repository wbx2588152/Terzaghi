package com.jk.mapper;


import com.jk.model.Comm;

import com.jk.model.*;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommMapper {

                    /*
                     * 前台页面
                     *
                     *
                     * */

    List<Comm> getComm();

    Comm getDetail(@Param("id") String id );



                    /*
                     * 后台页面
                     *
                     *
                     * */
    long queryComm(@Param("comm") Comm comm);

    List<Comm> queryCoomList(@Param("st") int start, @Param("end") int end, @Param("comm") Comm comm);

    void saveComm(@Param("comm") Comm comm);

    Comm queryCommById(@Param("id") String id);

    void updateComm(Comm comm);

    void deleteComm(@Param("id") String id);

    int deleteAllComm(@Param("ids") String[] split);

    void updateAudit2(Comm comm);

    void updateAudit1(Comm comm);

    void updateAdded(Comm comm);

    Comm queryDetail(@Param("id") String id);

    void updateLabel(Comm comm);

    List<Color> queryColour();

    List<Phone> queryPhone();

    List<Models> queryModels(Phone phone);

    List<Spec> querySpec();

    void saveModels(Models models);

    Phone queryPhone2(@Param("id") String id);

    long queryPhoneAll(@Param("phone") Phone phone);

    List<Comm> queryPhoneList(@Param("st")int start, @Param("end")int end, @Param("phone")Phone phone);

    long queryModlesAll(@Param("modles") Models models);

    List<Comm> queryModlesList(@Param("st") int start, @Param("end") int end, @Param("modles") Models models);

    void deleteModels(@Param("id") String id);

    Models queryModelsById(@Param("id") String id);

    void updateModels(Models models);

    int deleteAllModels(@Param("ids") String[] split);

    void savePhone(Phone phone);

    int deleteAllPhone(@Param("ids") String[] split);

    Phone queryPhoneById(@Param("id") String id);

    void updatePhone(Phone phone);

    void deletePhone(@Param("id") String id);

    long queryFood(Food food);

    List<Food> queryFoodList(@Param("st")int start, @Param("end")int end, @Param("food")Food food);

    void updateAddedf(Food food);

    void updateLabelf(Food food);

    List<FoodType> queryTypeAll();

    List<FoodUnit> queryUnitAll();

    void saveFood(Food food);

    int deleteAllFood(@Param("ids")String[] split);

    Food queryFoodById(@Param("id")String id);

    void updateFood(Food food);

    void deleteFood(@Param("id") String id);

    long queryMan(Man man);

    List<Food> queryManList(@Param("st") int start, @Param("end") int end, @Param("man") Man man);

    void updateManAdded(Man man);

    List<ManType> queryTypeMan();

    void updateLabelm(Man man);

    void saveMan(Man man);

    int deleteAllMan(@Param("ids") String[] split);

    Man queryManById(@Param("id") String id);

    void updateMan(Man man);

    void deleteMan(@Param("id") String id);

    long queryManAll(ManType mantype);

    List<Food> queryManAllList(@Param("st") int start, @Param("end") int end, @Param("type") ManType mantype);

    void saveManType(ManType manType);

    int deleteAllManType(@Param("ids") String[] split);

    ManType queryManTypeById(@Param("id") String id);

    void updateManType(ManType manType);

    void deleteManType(@Param("id") String id);

    void saveDetail(Detail detail);

    void saveFoodDetail(FoodDetail foddetail);

    void saveManDetail(ManDetail manDetail);

    Detail getDetail2(@Param("id") String id);

    List<Man> getMan();

    List<Food> getFood();

    Food getFoodDetail(@Param("id") String id);

    Man getManDetail(@Param("id") String id);
}

