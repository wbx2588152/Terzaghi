package com.jk.controller.adve;

import com.alibaba.fastjson.JSONObject;
import com.jk.model.Adve;
import com.jk.model.Coupon;
import com.jk.service.CouServiceApi;
import com.jk.service.adve.AdveService;
import com.jk.service.adve.AdveServiceApi;
import com.jk.util.AliyunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("adve")
public class AdveController {

    @Value("${server.port}")
    String port;

    @Autowired
    private AdveServiceApi adveServiceApi;

    //跳转广告管理页面
    @RequestMapping(value = "toAdve")
    public String toAdve(){
        return "adve/adveList";
    }

    //查询广告列表
    @RequestMapping(value = "queryDvre",method = RequestMethod.GET)
    @ResponseBody
    public String queryDvre(int page, int limit, Adve adve) {
        Map<String, Object> map = new HashMap<>();
        map = adveServiceApi.queryDvrelist(page, limit, adve);
        List<Adve> list = (List<Adve>) map.get("rows");
        int count = (int) map.get("total");
        //list转成json
//		 JSONArray array =new JSONArray();
        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", count);
        obj.put("data", list);

        return obj.toString();

    }

    //删除广告列表
    @RequestMapping(value = "delAdve",method = RequestMethod.GET)
    @ResponseBody
    public String delAdve(String id){
        adveServiceApi.delAdve(id);
        return "1";
    }

    //跳转广告新增页面
    @RequestMapping(value = "toaddAdve")
    public String toaddAdve(){
        return "adve/addadve";
    }

    //新增广告
    @RequestMapping(value = "saveAdve",method = RequestMethod.POST)
    @ResponseBody
    public String saveAdve(Adve adve){
        adve.setId(UUID.randomUUID().toString().replaceAll("-",""));
        adveServiceApi.saveAdve(adve);
        return "{}";
    }

   /* @RequestMapping("uploadOneFile")
    @ResponseBody
    public Map<String, Object>  upload(HttpServletRequest servletRequest,@RequestParam("file") MultipartFile file
    ) throws IOException {

        //如果文件内容不为空，则写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
            String path = "F:/idea2018/wangboxiao/Terzaghi/springcloud-feign/src/main/resources/static/img";

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
            res.put("url2","../img/"+filename);
            return res;

        } else {
            return null;

        }

    }
*/
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
            String url = "广告/";

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

    //跳转优惠券修改页面
    @RequestMapping(value = "toEditAdve", method = RequestMethod.GET)
    public String toEditAdve(@RequestParam(value = "id") String id, HttpServletRequest request) {
        Adve adve = adveServiceApi.queryAdveById(id);
        request.setAttribute("adve", adve);
        return "adve/editadve";
    }

    //修改优惠券
    @RequestMapping(value = "updateAdve", method = RequestMethod.POST)
    @ResponseBody
    public String updateAdve(Adve adve) {
        adveServiceApi.updateAdve(adve);
        return "1";
    }
}
