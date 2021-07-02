package com.arkus.practicasselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElements {
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

        WebElement linkTv = driver.findElement(By.linkText("TV"));
        linkTv.click();
        WebElement btnAddToCart = driver.findElement(By.xpath("//span[contains(text(), 'Add to Cart')]"));
        btnAddToCart.click();
        driver.findElement(By.xpath("//span[@class = 'price']")).click();

        WebElement lblSubtotal = driver.findElement(By.xpath("(//span[@class= 'price']) [4]"));
        actualResult = lblSubtotal.getText();

        if(actualResult.contentEquals(expectedResult)){
            System.out.println("Test Passed: " + actualResult + " es igual a: " + expectedResult);

        }else{
            System.out.println("Test Fallado: " + actualResult + " no es igual a: " + expectedResult);
        }

        driver.close();

    }
}
