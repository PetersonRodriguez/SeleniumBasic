package com.arkus.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionsExample {
    static WebDriver driver;

    public static void main(String[] args) {
        String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";
        String baseUrl = "https://www.facebook.com/";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

        try{
            WebElement txtemail = driver.findElement(By.id("email"));

            Actions builder = new Actions(driver);
            Action setOfActions = builder
                    .moveToElement(txtemail)
                    .click()
                    .keyDown(Keys.SHIFT)
                    .sendKeys("hello")
                    .keyUp(Keys.SHIFT)
                    .doubleClick()
                    .contextClick(txtemail)
                    .build();
            setOfActions.perform();

            Thread.sleep(2000);

            System.out.println("Test Pasado");

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
