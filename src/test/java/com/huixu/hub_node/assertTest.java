package com.huixu.hub_node;


import com.huixu.asserts.Assert_my;
import com.huixu.drivers.SeleniumDrivers;
import com.huixu.reports.TestReport;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestReport.class})
public class assertTest {
    WebDriver driver= SeleniumDrivers.loadBrowser("chrome");

    @Test
    public void testBidu(){
        SeleniumDrivers.openWeb("http://www.baidu.com");
        System.out.println("title is ------"+driver.getTitle());
        Assert_my.assertEquals(driver.getTitle(),"百度一下，你就知" +
                "","校验百度");
    }
}
