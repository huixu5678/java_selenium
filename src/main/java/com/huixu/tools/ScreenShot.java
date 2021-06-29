package com.huixu.tools;


import com.huixu.drivers.SeleniumDrivers;
import com.huixu.log4j.Log4j;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2016/11/12.必须要继承SeleniumDrivers
 */
public class ScreenShot extends SeleniumDrivers {
    static final Log4j log = Log4j.getlogger(ScreenShot.class);
    static String path = System.getProperties().getProperty("user.dir") + "/error/";
    /**
     * 错误截图，通过日期命名的截图
     **/
    public static void screenShots() {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        //根据日期创建文件夹，CHECK_LOG_FORMAT = "yyyyMMdd";REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";
        File_my myFile=new File_my();
        String myPath=path+DateFormate.format(DateFormate.SDF_DAYS);
        System.out.print(myPath);
        myFile.createFile(myPath);
        try {
            String times= DateFormate.format(DateFormate.SDF_DAY_TIMES);
            FileUtils.copyFile(file,new File(myPath + "/" +times+".jpg"));
            } catch (IOException e) {
            log.err("截图失败！！");
            e.printStackTrace();
        }
    }
    /**
     * 错误截图,通过传入name来给截图命名
     **/
    public static void screenShots1(String name) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            File_my myFile = new File_my();
            myFile.createFile(path + DateFormate.format(DateFormate.SDF_DAYS));
            log.info(DateFormate.format(DateFormate.SDF_DAYS));
            FileUtils.copyFile(file,new File(path + DateFormate.format(DateFormate.SDF_DAYS) + "/" + name + ".jpg"));
        } catch (IOException e) {
            log.err("截图失败！！");
            e.printStackTrace();
        }
    }

    /**
     * 判断文件夹，没有就新建
     */
/*    public static void fileExists() {
        String fileName = DateUtil.format(DateUtil.CHECK_LOG_FORMAT);
        File file = new File(path + fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
    }*/
}
