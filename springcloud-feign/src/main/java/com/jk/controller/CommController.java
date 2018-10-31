package com.jk.controller;




import com.alibaba.fastjson.JSONObject;
import com.jk.model.Comm;

import com.jk.model.*;


import com.jk.service.CommServiceApi;
import com.jk.util.AliyunUtil;
import com.jk.util.StringUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Controller
@RequestMapping("comm")
public class CommController<HrResult> {

    @Autowired
    private CommServiceApi commService;

    /*
     * 查询商品列表
     * */
    @RequestMapping("queryComm")
    @ResponseBody
    public String queryComm (int page, int limit, Comm comm){
        Map<String,Object> map=new HashMap<>();
        map = commService.queryComm(page,limit,comm);
        List<Comm> list = (List<Comm>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();

    }

    /*
     * 跳转商品页面
     * */
    @RequestMapping("toComm")
    public String toComm(){

        return "comm/commlist";
    }

    /*
     * 查询手机列表
     * */
    @RequestMapping("queryPhone")
    @ResponseBody
    public List<Phone> queryPhone(){
        List<Phone> x= commService.queryPhone();
        return x;
    }

    /*
     * 查询型号列表
     * */
    @RequestMapping("queryModels")
    @ResponseBody
    public List<Models> queryModels(Phone phone){
        List<Models> list= commService.queryModels(phone);
        return list;
    }


    /*
     * 跳转新增商品页面
     * */
    @RequestMapping("toAddComm")
    public String toAddComm(HttpServletRequest request){
        List<Color> list = commService.queryColour();
    /*List<Phone1> phone = commService.queryPhone();
    request.setAttribute("phone",phone);*/
        List<Spec> spec = commService.querySpec();
        request.setAttribute("spec",spec);
        request.setAttribute("list",list);
        return "comm/addcomm";

    }

        /*
         * 新增商品
         * */
        @RequestMapping("saveComm")
        @ResponseBody
        public String saveComm(Comm comm){
            comm.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            commService.saveComm(comm);
            return "1";
        }

        /*
         * 跳转修改商品页面
         * */
        @RequestMapping("toEditComm")
        public String toEditComm(String id, HttpServletRequest request,Phone phone){
            Comm comm = commService.queryCommById(id);
            List<Phone> phone2= commService.queryPhone();
            List<Models> models= commService.queryModels(phone);
            List<Color> color = commService.queryColour();
            List<Spec> spec = commService.querySpec();
            request.setAttribute("comm",comm);
            request.setAttribute("phone2",phone);
            request.setAttribute("models",models);
            request.setAttribute("color",color);
            request.setAttribute("spec",spec);
            return "comm/editcomm";
        }

        /*
         * 修改商品
         * */
        @RequestMapping("updateComm")
        @ResponseBody
        public String updateComm (Comm comm){
            commService.updateComm(comm);
            return "1";
        }

        /**
         *删除商品
         * */
        @RequestMapping("deleteComm")
        @ResponseBody
        public String deleteComm(String id){

            commService.deleteComm(id);
            return "1";
        }

        /**
         *批量删除商品
         * */
        @RequestMapping("deleteAllComm")
        @ResponseBody
        public String deleteAllComm(String ids){
            int n=	commService.deleteAllComm(ids);


            return n+"";
        }

        /**
         * 手机图片上传
         */
        @RequestMapping("uploadOneFile")
        @ResponseBody
        public Map<String, Object>  upload(HttpServletRequest servletRequest,
                                           @RequestParam("file") MultipartFile file
        ) throws IOException {

            //如果文件内容不为空，则写入上传路径
            if (!file.isEmpty()) {
                String url = "商品/";

                InputStream fos = file.getInputStream();

                String fileName = file.getOriginalFilename();

                URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
                String string = fileUpload.toString();
                String[] split = string.split("[?]");
                Map<String, Object> res = new HashMap<>();
                //返回的是一个url对象
                res.put("url", fileName);
                res.put("url2",split[0]);
                return res;

            } else {
                return null;

            }

        }

    /***
     * 改变审核状态
     *
     */
    @RequestMapping("updateAudit2")
    @ResponseBody
    public String updateAudit2(Comm comm){
        commService.updateAudit2(comm);

        return "1";
    }

    /***
     * 拒绝通过审核
     *
     */
    @RequestMapping("updateAudit1")
    @ResponseBody
    public String updateAudit1(Comm comm){
        commService.updateAudit1(comm);

        return "1";
    }


    /***
     * 商品上架、下架
     *
     */
    @RequestMapping("upAdded")
    @ResponseBody
    public String upAdded(Comm comm){
        commService.updateAdded(comm);
        return "1";
    }

    /***
     * 查看商品详情
     *
     */
    @RequestMapping("queryDetail")
    public String queryDetail(String id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "comm/detailist";
    }

    /**
     * 商品详情图片上传
     */
    @RequestMapping("uploadOneFileDetail")
    @ResponseBody
    public Map<String, Object>  uploadDetail(HttpServletRequest servletRequest,
                                       @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "详情/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;

        } else {
            return null;

        }

    }

    /**
     * 新增商品详情
     */
    @RequestMapping("saveDetail")
    @ResponseBody
    public String saveDetail(Detail detail){
        detail.setId(StringUUID.getUUID());
        detail.setDetaildate(new Date());
        commService.saveDetail(detail);
        return "1";
    }

    /***
     * 商品标签
     *
     */
    @RequestMapping("updateLabel")
    @ResponseBody
    public String updateLabel(Comm comm){
        commService.updateLabel(comm);
        return "1";
    }

    /***
     * 跳转新增手机型号页面
     *
     */
    @RequestMapping("toAddModels")
    public String toAddModels(HttpServletRequest request,String id){
        Phone phone = commService.queryPhone2(id);
        request.setAttribute("id",phone.getId());
        return "models/addmodels";
    }

    /***
     * 新增手机型号
     *
     */
    @RequestMapping("saveModels")
    @ResponseBody
    public String saveModels(Models models){
        models.setId(StringUUID.getUUID());
        models.setModelsdate(new Date());
        commService.saveModels(models);
            return "1";
    }

    /***
     * 跳转手机页面
     *
     */
    @RequestMapping("toPhoneAll")
    public String queryPhoneAll(){

        return "phone/phonelist";
    }

    /***
     * 查询手机列表
     *
     */
    @RequestMapping("queryPhoneAll")
    @ResponseBody
    public String queryPhoneAll (int page, int limit, Phone phone){
        Map<String,Object> map=new HashMap<>();
        map = commService.queryPhoneAll(page,limit,phone);
        List<Phone> list = (List<Phone>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();

    }

    /**
     *跳转手机型号页面
     *
     */
    @RequestMapping("toModelsAll")
    public String toModelsAll()
    {
        return "models/modelslist";
    }

    /**
     *
     * 查询手机型号列表
     */
    @RequestMapping("queryModlesAll")
    @ResponseBody
    public String queryModlesAll (int page, int limit, Models models){
        Map<String,Object> map=new HashMap<>();
        map = commService.queryModlesAll(page,limit,models);
        List<Models> list = (List<Models>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();

    }

    /**
     *删除手机型号
     *
     */
    @RequestMapping("deleteModels")
    @ResponseBody
    public String deleteModels(String id){
        commService.deleteModels(id);
        return "1";
    }

    /**
     *跳转修改手机型号页面
     *
     */
    @RequestMapping("toEditModels")
    public String toEditModels(HttpServletRequest request,String id){
        Models models = commService.queryModelsById(id);
        request.setAttribute("models",models);
        return "models/editmodels";
    }

    /**
     *修改手机型号
     *
     */
    @RequestMapping("updateModels")
    @ResponseBody
    public String updateModels(Models models){
        models.setModelsdate(new Date());
        commService.updateModels(models);
        return "1";
    }

    /**
     *批量删除手机型号
     *
     */
    @RequestMapping("deleteAllModels")
    @ResponseBody
    public String deleteAllModels(String ids){
        int n=	commService.deleteAllModels(ids);


        return n+"";
    }

    /**
     *跳转新增手机页面
     *
     */
    @RequestMapping("toAddPhone")
    public String toAddPhone(){
        return "phone/addphone";
    }

    /**
     *新增手机
     *
     */
    @RequestMapping("savePhone")
    @ResponseBody
    public String savePhone(Phone phone){
        phone.setId(StringUUID.getUUID());
        phone.setPhonedate(new Date());
        commService.savePhone(phone);
        return  "1";
    }

    /**
     *批量删除手机
     *
     */
    @RequestMapping("deleteAllPhone")
    @ResponseBody
    public String deleteAllPhone(String ids){
        int n=	commService.deleteAllPhone(ids);

        return n+"";
    }

    /**
     *跳转修改手机页面
     *
     */
    @RequestMapping("toEditPhone")
    public String toEditPhone(String id,HttpServletRequest request){
        Phone phone = commService.queryPhoneById(id);
        request.setAttribute("phone",phone);
        return "phone/editphone";
    }

    /**
     *修改手机
     *
     */
    @RequestMapping("updatePhone")
    @ResponseBody
    public String updatePhone(Phone phone){
        phone.setPhonedate(new Date());
        commService.updatePhone(phone);
        return "1";
    }

    /**
     *删除手机
     *
     */
    @RequestMapping("deletePhone")
    @ResponseBody
    public String deletePhone(String id){
        commService.deletePhone(id);
        return "1";

    }

    /**
     *跳转食品列表页面
     *
     */
    @RequestMapping("toFood")
    public String toFood(){
        return "food/foodlist";
    }

    /**
     *查询食品列表
     *
     */
    @RequestMapping("queryFood")
    @ResponseBody
    public String queryFood(int page,int limit,Food food){

        Map<String,Object> map=new HashMap<>();
        map = commService.queryFood(page,limit,food);
        List<Food> list = (List<Food>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();
    }

    /***
     * 食品上架、下架
     *
     */
    @RequestMapping("upAddedf")
    @ResponseBody
    public String upAddedf(Food food){
        commService.updateAddedf(food);
        return "1";
    }

    /***
     * 食品标签
     *
     */
    @RequestMapping("updateLabelf")
    @ResponseBody
    public String updateLabelf(Food food){
        commService.updateLabelf(food);
        return "1";
    }

    /***
     * 跳转新增食品页面
     *
     */
    @RequestMapping("toAddFood")
    public String toAddFood(HttpServletRequest request){
        List<FoodType> type = commService.queryTypeAll();
        List<FoodUnit> unit = commService.queryUnitAll();
        request.setAttribute("type",type);
        request.setAttribute("unit",unit);
        return "food/addfood";
    }

    /***
     * 新增食品
     *
     */
    @RequestMapping("saveFood")
    @ResponseBody
    public String saveFood(Food food){
        food.setId(StringUUID.getUUID());
        commService.saveFood(food);
        return "1";
    }

    /**
     * 食品图片上传
     */
    @RequestMapping("uploadOneFileFood")
    @ResponseBody
    public Map<String, Object>  uploadFood(HttpServletRequest servletRequest,
                                       @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "食品/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;

        } else {
            return null;

        }

    }

    /**
     * 批量删除食品
     *
     */
    @RequestMapping("deleteAllFood")
    @ResponseBody
    public String deleteAllFood(String ids){
        int n = commService.deleteAllFood(ids);
        return n+"";
    }

    /**
     * 跳转修改食品页面
     *
     */
    @RequestMapping("toEditFood")
    public String queryFoodById(HttpServletRequest request,String id){
        Food food = commService.queryFoodById(id);
        List<FoodType> type = commService.queryTypeAll();
        List<FoodUnit> unit = commService.queryUnitAll();
        request.setAttribute("type",type);
        request.setAttribute("unit",unit);
        request.setAttribute("food",food);
        return "food/editfood";
    }

    /**\
     * 修改食品列表
     *
     */
    @RequestMapping("updateFood")
    @ResponseBody
    public String updateFood(Food food){
        commService.updateFood(food);
        return "1";
    }

    /**
     * 删除食品
     *
     */
    @RequestMapping("deleteFood")
    @ResponseBody
    public String deleteFood(String id){
        commService.deleteFood(id);
        return "1";
    }

    /**
     *跳转男装页面
     *
     */
    @RequestMapping("toMan")
    public String toMan(){
        return "man/manlist";
    }

    /**
     *查询男装列表
     *
     */
    @RequestMapping("queryMan")
    @ResponseBody
    public String queryMan(int page,int limit,Man man){
        Map<String,Object> map=new HashMap<>();
        map = commService.queryMan(page,limit,man);
        List<Man> list = (List<Man>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();
    }

    /**
     * 男装上下架
     */
    @RequestMapping("upAddedm")
    @ResponseBody
    public String upAddedm(Man man){
        commService.updateManAdded(man);
        return "1";
    }

    /**
     * 男装标签
     */
    @RequestMapping("updateLabelm")
    @ResponseBody
    public String updateLabelm(Man man){
        commService.updateLabelm(man);
        return "1";
    }

    /**
     * 跳转男装新增页面
     */
    @RequestMapping("toAddMan")
    public String toAddMan(HttpServletRequest request){
        List<ManType> list = commService.queryTypeMan();
        request.setAttribute("list",list);
        return "man/addman";
    }

    /**
     * 男装图片上传
     */
    @RequestMapping("uploadOneFileMan")
    @ResponseBody
    public Map<String, Object>  uploadMan(HttpServletRequest servletRequest,
                                           @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "男装/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;

        } else {
            return null;

        }

    }

    /**
     * 新增男装
     */
    @RequestMapping("saveMan")
    @ResponseBody
    public String saveMan(Man man){
        man.setId(StringUUID.getUUID());
        commService.saveMan(man);
        return "1";
    }

    /**
     * 批量删除男装
     */
    @RequestMapping("deleteAllMan")
    @ResponseBody
    public String deleteAllMan(String ids){
        int n = commService.deleteAllMan(ids);
        return n+"";
    }

    /**
     * 跳转修改男装页面
     */
    @RequestMapping("toEditMan")
    public String toEditMan(String id,HttpServletRequest request){
        Man man = commService.queryManById(id);
        List<ManType> list = commService.queryTypeMan();
        request.setAttribute("list",list);
        request.setAttribute("man",man);
        return "man/editman";
    }

    /**
     * 修改男装
     */
    @RequestMapping("updateMan")
    @ResponseBody
    public String updateMan(Man man){
        commService.updateMan(man);
        return "1";
    }

    /**
     * 删除男装
     */
    @RequestMapping("deleteMan")
    @ResponseBody
    public String deleteMan(String id){
        commService.deleteMan(id);
        return "1";
    }


    /**
     * 跳转男装类型列表
     */
    @RequestMapping("toManType")
    public String toManType(){
        return "mantype/mantypelist";
    }

    /**
     * 查询男装类型列表
     */
    @RequestMapping("queryManAll")
    @ResponseBody
    public String queryManAll(int page,int limit,ManType mantype){
        Map<String,Object> map=new HashMap<>();
        map = commService.queryManAll(page,limit,mantype);
        List<ManType> list = (List<ManType>) map.get("rows");
        int count= (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj=new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",count);
        obj.put("data",list);

        return obj.toString();
    }

    /**
     * 跳转新增男装类型页面（下级）
     */
    @RequestMapping("toAddManType")
    public String toAddManType(){
        return "mantype/addmantype";
    }

    /**
     * 新增男装类型(下级)
     */
    @RequestMapping("saveManType")
    @ResponseBody
    public String saveManType(ManType manType){
        manType.setId(StringUUID.getUUID());
        manType.setTypedate(new Date());
        commService.saveManType(manType);
        return "1";
    }

    /**
     * 批量删除男装类型
     */
    @RequestMapping("deleteAllManType")
    @ResponseBody
    public String deleteAllManType(String ids){
        int n = commService.deleteAllManType(ids);
        return n+"";
    }

    /**
     * 跳转修改男装类型页面
     */
    @RequestMapping("toEditManType")
    public String toEditManType(HttpServletRequest request,String id){
        ManType manType = commService.queryManTypeById(id);
        request.setAttribute("manType",manType);
        return "mantype/editmantype";
    }

    /**
     * 修改男装类型
     */
    @RequestMapping("updateManType")
    @ResponseBody
    public String updateManType(ManType manType){
        manType.setTypedate(new Date());
        commService.updateManType(manType);
        return "1";
    }

    /**
     * 删除男装类型
     */
    @RequestMapping("deleteManType")
    @ResponseBody
    public String deleteManType(String id){
        commService.deleteManType(id);
        return "1";
    }

    /**
     * 跳转新增食品详情页面
     */
    @RequestMapping("toAddFoodDetail")
    public String toAddFoodDetail(String id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "food/dtaillist";
    }

    /**
     * 食品详情图片上传
     */
    @RequestMapping("uploadFoodDetail")
    @ResponseBody
    public Map<String, Object>  uploadFoodDetail(HttpServletRequest servletRequest,
                                             @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "食品详情/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;

        } else {
            return null;

        }

    }

    /**
     * 新增食品详情
     */
    @RequestMapping("saveFoodDetail")
    @ResponseBody
    public String saveFoodDetail(FoodDetail foddetail){
        foddetail.setId(StringUUID.getUUID());
        foddetail.setDetaildate(new Date());
        commService.saveFoodDetail(foddetail);
        return "1";
    }

    /**
     * 跳转新增男装详情页面
     */
    @RequestMapping("toAddManDetail")
    public String toAddManDetail(String id,HttpServletRequest request){
        request.setAttribute("id",id);
        return "man/dtaillist";
    }

    /**
     * 食品详情图片上传
     */
    @RequestMapping("uploadManDetail")
    @ResponseBody
    public Map<String, Object>  uploadManDetail(HttpServletRequest servletRequest,
                                                 @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            String url = "男装详情/";

            InputStream fos = file.getInputStream();

            String fileName = file.getOriginalFilename();

            URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
            String string = fileUpload.toString();
            String[] split = string.split("[?]");
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", fileName);
            res.put("url2",split[0]);
            return res;

        } else {
            return null;

        }

    }

    /**
     * 新增男装详情
     */
    @RequestMapping("saveManDetail")
    @ResponseBody
    public String saveManDetail(ManDetail manDetail){
        manDetail.setId(StringUUID.getUUID());
        manDetail.setDetaildate(new Date());
        commService.saveManDetail(manDetail);
        return "1";

}
