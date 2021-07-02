package com.arkus.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScript {

    private WebDriver driver;
    String expectedResult = null;
    String actualResult = null;
    String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";
    String baseURL = "http://demo.guru99.com/test/newtours/";
    private JavascriptExecutor js;
    String pageLoadStatus = "";

    private boolean highLight(WebElement element){
        js = (JavascriptExecutor) driver;
        for (int iCnt =0; iCnt<3; iCnt++){
            try{
                js.executeScript("arguments[0].setAttribute('style', 'background:red')",element);
                Thread.sleep(2000);
                js.executeScript("arguments[0].setAttribute('style', 'background:')",element);
            }catch (Exception e) {
                System.err.println("JavaScript method highlight | Exception desc: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    private boolean scrollWindow(){
        try{
            js = (JavascriptExecutor)driver;
            //scrollDown (0,250) scrollUp(0,-250)
            js.executeScript("window.scrollBy(0,250)");
        }catch (Exception e){
            System.err.println("JavaScript Method: scrollWindow | Exception desc: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean waitForPageToLoad(){
        try{
            do{
                js = (JavascriptExecutor)driver;
                pageLoadStatus = (String)js.executeScript("return document.readyState");
            }while(!pageLoadStatus.equals("complete"));

        }catch (Exception e){
            System.err.println("JavaScript Method: waitForPageToLoad | Exception desc: " + e.getMessage());
            return false;
        }
        return true;
    }

    @BeforeTest
    public void launchBrowser(){
        System.setProperty("webdriver.chrome.driver",chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        waitForPageToLoad();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 0)
    public void registerPage(){
        WebElement lnkRegister = driver.findElement(By.linkText("REGISTER"));
        Assert.assertTrue(highLight(lnkRegister));
        js.executeScript("arguments[0].click();", lnkRegister);
        expectedResult = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult);
        Assert.assertTrue(scrollWindow());
    }

    @Test(priority = 1)
    public void registerUser(){
        try{
            WebElement txtFirstName = driver.findElement(By.name("firstName"));
            highLight(txtFirstName);
            txtFirstName.sendKeys("Peterson");

            WebElement ddlCountry = driver.findElement(By.name("country"));
            highLight(ddlCountry);
            new Select(ddlCountry).selectByVisibleText("AUSTRIA");

            highLight(driver.findElement(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("nohaycorreo@mail.com");

            highLight(driver.findElement(By.name("password")));
            driver.findElement(By.name("password")).sendKeys("4152");

            WebElement txtConfirmPass = driver.findElement(By.name("confirmPassword"));
            highLight(txtConfirmPass);
            txtConfirmPass.sendKeys("4152");
            driver.findElement(By.name("submit")).click();

            Assert.assertTrue(waitForPageToLoad());

            highLight(driver.findElement(By.xpath("//b[contains(text(),'Note: Your user name is')]")));

        }catch (NoSuchElementException ne){
            Assert.fail("Test Failed: Element not found");
        }catch (Exception e){
            Assert.fail("Test Failed" + e.getMessage());
        }
    }

}
