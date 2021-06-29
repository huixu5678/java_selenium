package com.huixu.tools;

import java.util.Random;

public class RandomNum {


    //    生成N位数的随机数
    public static String getNumRandom(int length){


//        Math.rondom()会生成一个【0,1）的double随机数
          double num1=Math.random();
          String num=String.valueOf((long)(num1*(Math.pow(10,length))));

          return num;
    }
    //生成 【n-m】之间的数
    public static int getNum2(int min,int max){
        Random random=new Random();
        int num=random.nextInt(max-min+1)+min;
        return num;
    }

    //生成N位随机字符串 包含数字he字母大小写
    public static String getStrRandom(int length){
        String val="";
        Random random = new Random();
        for(int i=0;i<length;i++){
            String str=random.nextInt(2)%2==0 ? "char":"num";
            if(str.equals("char")){
                int tempnum=random.nextInt(2)%2==0? 65:97;
                val+=(char)(random.nextInt(26)+tempnum);
            }
            else{
                val+=String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}
