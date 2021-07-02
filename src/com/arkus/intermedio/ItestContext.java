package com.arkus.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ItestContext {

    WebDriver driver;

    @BeforeTest(groups = {"A", "B"})
    public void setup(){
        String chromepath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromepath );
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    @AfterTest(groups = {"A"})
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "SearchProvider", groups = {"A"})
    public void testmethodA(String tester, String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        System.out.println("Welcome -> " + tester + " your search word is -> " + search);
        Thread.sleep(3000);

        String testValue = searchText.getAttribute( "value");
        System.out.println("Test value is -> " + testValue + " and is equals to " + search);
        searchText.clear();

        Assert.assertTrue(testValue.equals(search));
    }

    @Test(dataProvider = "SearchProvider", groups = {"B"})
    public void testmethodB(String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        Thread.sleep(3000);

        String testValue = searchText.getAttribute( "value");
        System.out.println("Test value is -> " + testValue + " and is equals to " + search);
        searchText.clear();

        Assert.assertTrue(testValue.equals(search));
    }

    @DataProvider(name = "SearchProvider")
    public Object [][] getDataFromDataProvider(ITestContext c) {
        Object[][] groupArray = null;
        for (String group: c.getIncludedGroups()) {
           if(group.equals("A")){
               groupArray = new Object[][]{
                       {"Isabel", "Google"},
                       {"Ernesto", "Yahoo"},
                       {"Gabriela", "Amazon"},
                       {"Pedro", "Gmail"}
               };
               break;
           }else if(group.equals("B")){
               groupArray = new Object[][]{
                       {"Mexico"},
                       {"Canada"},
                       {"Rusia"},
                       {"Japon"}
               };
           }
           break;
        }
        return groupArray;
    }

}
