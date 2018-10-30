package com.jk.controller;

import com.jk.model.ProbabilityBean;
import com.jk.model.StockBean;
import com.jk.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TopController {
    @Autowired
    private TopService topService;

    @GetMapping("topo/userList2")
    public String userList2() {
        return "/topo/aaa";
    }

    @GetMapping("topo/userList1")
    public String userList1() {
        return "/topo/userListPage";
    }

    @RequestMapping("topo/getUserCount")
    @ResponseBody
    public int getUserCount(){
        return topService.getUserCount();
    }
    @RequestMapping("topo/getUserList")
    @ResponseBody
    public List<StockBean> getUserList(String name, int page, int limit){
        return topService.getUserList(name,page,limit);
    }

    @RequestMapping("topo/getlunpanCount")
    @ResponseBody
    public int getlunpanCount(){
        return topService.getlunpanCount();
    }
    @RequestMapping("topo/getLunpanList")
    @ResponseBody
    public List<ProbabilityBean> getLunpanList(String name, int page, int limit){
        return topService.getLunpanList(name,page,limit);
    }

    /**
     *
     * @param stockid
     * @return
     */
    @RequestMapping("/topo/delete")
    @ResponseBody
    public Integer delete(@RequestParam(value="stockid")Integer stockid) {
        int num = topService.delete(stockid);
        return num;
    }

    @GetMapping("topo/tozhuan")
    public String tozhuan() {
        return "/zhuanpan/demo1";
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
        System.out.println(two);
        System.out.println(three);
        System.out.println(four);
        System.out.println(five);
        System.out.println(six);
        System.out.println(seven);
        System.out.println(eight);
        System.out.println(nine);
        System.out.println(ten);
*/

            double game = (double)(1+Math.random()*10);



       if (game < ( wan / 10 ) ){ // 60%
            // do something
           jieguo = 1;
            System.out.println("1");
        }
        else if (game <  ( (wan + two) / 10 ) ){ //0.1
           jieguo = 2;
            System.out.println("2");

        }
        else if (game <  ( (wan + two + three ) / 10 )){ // 14%
           jieguo = 3;
            System.out.println("3");

        }
       else if (game <  ( (wan + two + three + four ) / 10 )){ // 14%
           jieguo = 4;
           System.out.println("4");

       }
       else if (game <  ( (wan + two + three + four + five ) / 10 )){ // 14%
           jieguo = 5;
          // System.out.println("5");


       }
       else if (game <  ( (wan + two + three + four + five + six ) / 10 )){ // 14%
           jieguo = 6;
          // System.out.println("6");

       }
       else if (game <  ( (wan + two + three + four + five + six + seven ) / 10 )){ // 14%
           jieguo = 7;
         //  System.out.println("7");

       }
       else if (game <  ( (wan + two + three  + four + five + six + seven + eight ) / 10 )){ // 14%
           jieguo = 8;
         //  System.out.println("8");

       }
       else if (game <  ( (wan + two + three  + four + five + six + seven + eight +  nine) / 10 )){ // 14%
           jieguo = 9;
          // System.out.println("9");

       }
        else { //0.3
           jieguo = 10;
          //  System.out.println("10");
        }
        System.out.println(jieguo);
        return jieguo;
    }

    /**
     * 去修改界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/topo/toUpdate")
    @ResponseBody
    public ProbabilityBean toUpdate(Integer id, Model model) {
        ProbabilityBean user = topService.getUserById(id);
        return  user ;
    }

    @RequestMapping("/topo/userUpdate")
    @ResponseBody
    public Integer  userUpdate(String  id,String name,String probability){
        ProbabilityBean probabilityBean = new ProbabilityBean();
        probabilityBean.setId(id);
        probabilityBean.setName(name);
        probabilityBean.setProbability(probability);
        int num = topService.update(probabilityBean);
        return num;
    }

}

