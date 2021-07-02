package com.arkus.reports;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(JyperionListener.class)
public class PdfEmail extends BaseClass{
    WebDriver driver = getDriver();

    @Test
    public void testOne(){
        driver.get("https://www.google.com");
        Assert.assertTrue(false);
    }

    @Test
    public void testTwo(){
        driver.get("https://www.facebook.com/");
        Assert.assertTrue(true);
    }

    @Test
    public void testThree(){
        driver.get("http://demo.guru99.com/test/newtours/");
        Assert.assertTrue(false);
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

    @AfterSuite
    public void sendEmail(){
        sendReportByEmail("pedro.rdz777@gmail.com","", "pedro_rdz777@hotmail.com", "PDF Test execution report", "Plese find attahed the pdf execution report");
    }
}
