package com.huixu.cases;

import com.huixu.actions.aboutElements;
import com.huixu.drivers.SeleniumDrivers;
import com.huixu.excel.ExcelUtil;
import com.huixu.mysql.MysqlDBdriver;
import com.huixu.pages.indexPage;
import com.huixu.asserts.Assert_my;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;

public class WebtoursTest {

    WebDriver driver;
    @BeforeMethod
    @Parameters({"url"})
    public void startBrowser(String url){
        driver=SeleniumDrivers.loadBrowser("chrome");
//        SeleniumDrivers.openWeb("localhost:1080/webtours/");
        SeleniumDrivers.openWeb(url);

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
