package com.huixu.actions;

import com.huixu.drivers.SeleniumDrivers;
import com.huixu.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class aboutElements extends SeleniumDrivers
 {
     static Log4j log= Log4j.getlogger(aboutElements.class);

     public static WebElement findElement(final By by){


         try {
             WebDriverWait wait = new WebDriverWait(driver, 10);
             wait.until(ExpectedConditions.presenceOfElementLocated(by));
         }catch(Exception e){
             log.err("元素："+by+"查找超时！！！");
             e.printStackTrace();
         }

         return driver.findElement(by);

     }
     public static List<WebElement> findElements(final By by){


         try {
             WebDriverWait wait = new WebDriverWait(driver, 10);
             wait.until(ExpectedConditions.presenceOfElementLocated(by));
         }catch(Exception e){
             log.err("元素："+by+"查找超时！！！");
             e.printStackTrace();
         }

         return driver.findElements(by);

     }
     public static void switchtoFrame(String idorName){
         driver.switchTo().frame(idorName);
     }
     public static void switchtoDefaultContent(){
         driver.switchTo().defaultContent();
     }
     public static void sendText(By by,String text){
         WebElement element=aboutElements.findElement(by);
         element.clear();
         element.sendKeys(text);
         log.info("在文本框"+by+"中输入文本："+text);
     }

     public static String getText(By by){
         String str=aboutElements.findElement(by).getText();
         return str;
     }
     public static void click(By by){

         aboutElements.findElement(by).click();
         log.info("点击："+by);
     }
}
