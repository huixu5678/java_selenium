package com.huixu.hub_node;

import com.huixu.actions.aboutElements;
import com.huixu.asserts.Assert_my;
import com.huixu.drivers.SeleniumDrivers;
import com.huixu.mysql.MysqlDBdriver;
import com.huixu.pages.indexPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;

public class WebtoursTest {


    @BeforeMethod
    public void startBrowser()throws Exception{
        SeleniumDrivers.loadBrowser_grid("chrome","http://192.168.0.120:6666/wd/hub");
        SeleniumDrivers.openWeb("localhost:1080/webtours/");
    }
    @Test(dataProvider = "datafrommysql",dataProviderClass = MysqlDBdriver.class, priority = 0)
    public void loginTest(String[] data)throws Exception {
        aboutElements.switchtoFrame("body");
        aboutElements.switchtoFrame("navbar");
        aboutElements.sendText(indexPage.InputUsername,data[0]);
        Thread.sleep(3000);
        aboutElements.sendText(indexPage.InputPassword,data[1]);
        Thread.sleep(3000);
        aboutElements.click(indexPage.ButtonLogin);
        aboutElements.switchtoDefaultContent();
        aboutElements.switchtoFrame("body");
        aboutElements.switchtoFrame("info");
        System.out.println("body的内容是 body的内容是："+aboutElements.getText(indexPage.TagBody));
        Assert_my.assertInclude(aboutElements.getText(indexPage.TagBody),data[2]);
    }
    @AfterMethod
    public void closeBlowser(){
        SeleniumDrivers.closeBrowser();
    }
}
