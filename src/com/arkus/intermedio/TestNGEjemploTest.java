package com.arkus.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGEjemploTest {
    String baseURl = "http://demo.guru99.com/test/newtours/";
    WebDriver driver;
    String expectedResult = "";
    String actualResult = "";
    String chromepath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";

    @BeforeTest
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver", chromepath);
        driver = new ChromeDriver();
        driver.get(baseURl);
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void verifyHomePageTitle(){
        expectedResult = "Welcome: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult, "Title is not the same");
    }

    @AfterMethod
    public void goBackToHomePage(){
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void register(){
        driver.findElement(By.linkText("REGISTER")).click();
        expectedResult = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(expectedResult,actualResult, "Page Title is not the expected");
    }

    @Test
    public void support(){
        driver.findElement(By.linkText("SUPPORT")).click();
        expectedResult = "Under Construction: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(expectedResult,actualResult, "Page Title is not the expected");
    }
}