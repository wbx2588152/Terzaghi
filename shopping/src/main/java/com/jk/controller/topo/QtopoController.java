package com.jk.controller.topo;

import com.jk.model.ProbabilityBean;
import com.jk.service.topo.QTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: 王嘟嘟
 * @Date: 2018/10/26 16:21
 * @Description:
 */
@Controller
public class QtopoController {
    @Autowired
    private QTopService topService;

    //跳转页面轮盘
    @GetMapping("topo/usrzhuanpan")
    public  String usrzhuanpan(){
        return "/topo/zhuanpan/wheelpages";
    }

    @RequestMapping("/topo/suiji")
    @ResponseBody
    public int suiji(){
        List<ProbabilityBean> userList = topService.suiji();
        //  System.out.println(userList);
        String ids = "";//求出id中的数字（字符串）
        String names =  "";//求出名字中的字符串
        String probabilitys ="";//求出概率
        int he = 0; //求出概率中奖的数和

        double wan = 0;
        double two  = 0;
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        double seven = 0;
        double eight = 0;
        double nine = 0;
        double ten = 0;

        double huo =  0;

        //结果参数
        int jieguo = 0;

        for (int i = 0; i < userList.size(); i++) {
            String id = userList.get(i).getId();
            ids+=(ids=="" ? "":",")+id;
            String name = userList.get(i).getName();
            names+=(names=="" ? "":",")+name;
            String probability =  userList.get(i).getProbability();
            int aa =  Integer.parseInt( userList.get(i).getProbability());
            //    probabilitys+=(probability=="" ? "":",")+probability;
            he = aa+he;
            if (id.equals("1")){
                wan = aa ;
            } else if(id.equals("2")){
                two = aa;
            } else if(id.equals("3")){
                three = aa ;
            } else if(id.equals("4")){
                four = aa;
            } else if(id.equals("5")){
                five = aa;
            } else if(id.equals("6")){
                six = aa;
            } else if(id.equals("7")){
                seven = aa ;
            } else if(id.equals("8")){
                eight = aa;
            } else if(id.equals("9")){
                nine = aa;
            } else if(id.equals("10")){
                ten = aa;
            } else {
                System.out.println("哈哈，恭喜报错");
            }
            //  System.out.println(id);
            //   System.out.println(name);
            //   System.out.println(probability);//求出字符串（概率）
        }
        //  System.out.println(ids);
        //  System.out.println(names);
        //   System.out.println(probabilitys);
        /// System.out.println(he); //概率求出了和
       /* System.out.println(wan);
        System.out.println(wanwan);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);
        System.out.println(six);
        System.out.println(seven);
        System.out.println(eight);
        System.out.println(nine);
        System.out.println(ten);
*/
        huo = wan + two + three + four + five + six + seven + eight + nine + ten ;

        double game = (double)(Math.random() * 1);

        if (game < ( wan / huo ) ){ // 60%
            // do something
            jieguo = 9 ;
            System.out.println("1");
        }
        else if (game <  ( (wan + two) / huo ) ){ //0.1
            jieguo = 330;
            System.out.println("2");

        }
        else if (game <  ( (wan + two + three ) / huo )){ // 14%
            jieguo = 285;
            System.out.println("3");

        }
        else if (game <  ( (wan + two + three + four ) / huo )){ // 14%
            jieguo = 250;
            System.out.println("4");

        }
        else if (game <  ( (wan + two + three + four + five ) / huo )){ // 14%
            jieguo = 225;
            // System.out.println("5");


        }
        else if (game <  ( (wan + two + three + four + five + six ) / huo )){ // 14%
            jieguo = 175;
            // System.out.println("6");

        }
        else if (game <  ( (wan + two + three + four + five + six + seven ) / huo )){ // 14%
            jieguo = 145;
            //  System.out.println("7");

        }
        else if (game <  ( (wan + two + three  + four + five + six + seven + eight ) / huo )){ // 14%
            jieguo = 100  ;
            //  System.out.println("8");

        }
        else if (game <  ( (wan + two + three  + four + five + six + seven + eight +  nine) / huo )){ // 14%
            jieguo = 70;
            // System.out.println("9");

        }
        else { //0.3
            jieguo = 27;
            //  System.out.println("10");
        }
        System.out.println(jieguo);
        return jieguo;
    }




}
