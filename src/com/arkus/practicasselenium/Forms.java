package com.arkus.practicasselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Forms {
    static WebDriver driver;


    public static void main(String[] args) {
        String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";
        String baseUrl = "http://demo.guru99.com/test/newtours/";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

        try{
            driver.findElement(By.linkText("REGISTER")).click();

            WebElement inputFirstName = driver.findElement(By.name("firstName"));
            inputFirstName.sendKeys("Pedro");

            driver.findElement(By.name("address1")).sendKeys("Direccion de prueba");

            Select dropCountry = new Select(driver.findElement(By.name("country")));
            Thread.sleep(1500);
            dropCountry.selectByVisibleText("MEXICO");

            driver.findElement(By.id("email")).sendKeys("ElRocs88");
            driver.findElement(By.name("password")).sendKeys("Testing01");
            WebElement inputConfirmPass = driver.findElement(By.name("confirmPassword"));
            inputConfirmPass.sendKeys("Testing01");
            driver.findElement(By.name("submit")).click();
            Thread.sleep(1500);

            System.out.println("Test Paso: " + driver.findElement(By.xpath("//*[contains(text(),'Note')]")).getText());

        }catch (NoSuchElementException ne){
            System.err.println("Elemento no encontrado: " + ne.getMessage());
        }catch (WebDriverException we){
            System.err.println("Error en WebDriver: " + we.getMessage());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
