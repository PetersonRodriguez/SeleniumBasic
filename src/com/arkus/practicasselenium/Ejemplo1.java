package com.arkus.practicasselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejemplo1 {
    public static void main(String[] args) {
        //iniciar objeto webdriver
        WebDriver driver;

        //variables
        String baseUrl= "http://demo.guru99.com/test/newtours/";
        String actualResult = "";
        String expectedResult = "Welcome: Mercury Tours";

        //setear driver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\driver\\chromedriver.exe");

        //Abrir navegador
        driver = new ChromeDriver();
        driver.get(baseUrl);

        //obtener nombre pagina web
        actualResult = driver.getTitle();

        System.out.println(actualResult.contentEquals(expectedResult)?"Test Passed: " + actualResult : "Test Fallado");


        driver.close();
    }
}
