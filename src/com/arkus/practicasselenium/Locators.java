package com.arkus.practicasselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
    public static void main(String[] args) {

        WebDriver driver;
        String baseUrl = "http://live.demoguru99.com/index.php/tv.html";
        String actualResult = "";
        String expectedResult = "$615.00";
        String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

        driver.findElement(By.linkText("TV")).click();
        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span")).click();
        //driver.findElement(By.xpath("//span[@class = 'price']")).click();
        //  //span[contains(text(),'Subscribe')] //span[text() = 'Update Shopping Cart']

        actualResult = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-total > span > span")).getText();

        if(actualResult.contentEquals(expectedResult)){
            System.out.println("Test Passed: " + actualResult + " es igual a: " + expectedResult);

        }else{
            System.out.println("Test Fallado: " + actualResult + " no es igual a: " + expectedResult);
        }

        driver.close();

    }
}
