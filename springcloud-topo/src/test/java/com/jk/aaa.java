package com.jk;

import java.util.Random;

/**
 * @Auther: 王嘟嘟
 * @Date: 2018/10/19 21:24
 * @Description:
 */
public class aaa {
    public static void main(String[] arge) {

   /*   for (int i = 0; i < 10; i++) {
            int arr[] = {1,2,3,4,6,7,8,9};
            int randomIndex = (int)(Math.random()*(arr.length-1));
            int random = arr[randomIndex];
            int aaa =    Math.random() < 0.3 ? 5 : random;
            System.out.println(aaa);

        }*/


        Random r = new Random();

        int game = r.nextInt(10);

        if (game < 6){ // 60%
            // do something
            System.out.println("1");
        }
        else if (game < 6.1){ //0.1
            System.out.println("2");
        }
        else if (game < 9){ // 14%
            System.out.println("3");
        }
        else { //0.3

            System.out.println("4");
        }

    }
}
