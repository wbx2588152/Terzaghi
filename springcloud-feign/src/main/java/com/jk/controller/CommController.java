package com.jk.controller;


import com.alibaba.fastjson.JSONObject;
import com.jk.model.Comm;

import com.jk.service.CommServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     * 跳转新增商品页面
     * */
    @RequestMapping("toAddComm")
    public String toAddComm(){
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
    public String toEditComm(String id, HttpServletRequest request){
        Comm comm = commService.queryCommById(id);
        request.setAttribute("comm",comm);
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
     * 图片上传
     */
    @RequestMapping("uploadOneFile")
    @ResponseBody
    public Map<String, Object>  upload(HttpServletRequest servletRequest,
                                       @RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = "C:/JAVA/sjy-idea-shixun2/springcloud-feign/src/main/resources/static/imgs";

            System.out.println("文件名称"+file.getOriginalFilename());
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);


            //判断路径是否存在，没有就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }

            //将上传文件保存到一个目标文档中
            File file1;
            file1 = new File(path + File.separator + filename);
            file.transferTo(file1);
            Map<String, Object> res = new HashMap<>();
            //返回的是一个url对象
            res.put("url", file1);
            res.put("url2","../imgs/"+filename);
            return res;

        } else {
            return null;

        }

    }

   /* public String uploadOneFile(MultipartFile filesrc) throws IOException {



			*//*String url= wbxService.stupanurl(pid);

			String[] url2=url.split("/");

			url="";
			if(url2.length>=1){
				for (int i = url2.length-1; i >= 0; i--) {
					url+=url2[i]+"/";
				}
			}*//*

        String url = "广告/";

        InputStream fos = filesrc.getInputStream();

        String fileName = filesrc.getOriginalFilename();

        URL fileUpload = AliyunUtil.upFObject("stupan", url+fileName , fos);
        String string = fileUpload.toString();
        String[] split = string.split("[?]");
        return split[0];

    }
*/
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




}
