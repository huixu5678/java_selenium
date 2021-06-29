package com.huixu.drivers;

import com.huixu.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class SeleniumDrivers {

    public static WebDriver driver;

    static Log4j logger= Log4j.getlogger(SeleniumDrivers.class);
    public static WebDriver loadBrowser(String browser){
        String driverPath=System.getProperty("user.dir");
        if(browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",driverPath+"/Drivers/chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",driverPath+"/Drivers/geckodriver.exe");
            System.setProperty("webdriver.firefox.bin", "D:/Firefox/firefox.exe");
            driver=new FirefoxDriver();
            driver.manage().window().maximize();
        }else if(browser.equals("ie")){
            System.setProperty("webdriver.ie.driver",driverPath+"/Drivers/IEDriverServer.exe");
            driver=new InternetExplorerDriver();
            driver.manage().window().maximize();
        }else{
            logger.err("您提供的浏览器名称有误");

        }
        return driver;
    }

    public static void openWeb(String url){
//        String reg="^(?!http).*$";
//        Pattern pattern= Pattern.compile(reg);
//        Matcher matcher=pattern.matcher(url);
//        Boolean bool=matcher.matches();
//        if(bool){url="http://"+url;}
//        driver.get(url);
        if(!url.startsWith("http://")){
            url="http://"+url;
        }
        driver.get(url);
        logger.info("打开网址："+url);
    }

    public static WebDriver loadBrowser_grid(String browser,String url)throws Exception{
        DesiredCapabilities dc=null;
        if(browser.equals("ie")){
            dc=DesiredCapabilities.internetExplorer();
        }else if(browser.equals("chrome")){
            dc=DesiredCapabilities.chrome();
        }else if(browser.equals("firefox")){
            dc=DesiredCapabilities.firefox();

        }
        driver=new RemoteWebDriver(new URL(url),dc);
        return driver;
    }
    public static void closeBrowser(){
        driver.quit();
    }
}
