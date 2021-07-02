package com.arkus.practicasselenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ejemplo2 {
    static WebDriver driver;

    public static void main(String[] args) {
        String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";
        String baseUrl = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait waitVar = new WebDriverWait(driver, 10);

        try{
            driver.switchTo().frame("iframeResult");

            WebElement btnTry = driver.findElement(By.xpath("//button[contains(text(),'Try it')]"));
            waitVar.until(ExpectedConditions.elementToBeClickable(btnTry));
            btnTry.click();
            Thread.sleep(2000);
            waitVar.until(ExpectedConditions.alertIsPresent());

            Alert alertWin = driver.switchTo().alert();
            String alertText = alertWin.getText();
            System.out.println(alertText);
            alertWin.sendKeys("Pedro Rdz");
            alertWin.accept();

            String finalText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
            System.out.println(finalText.contains("Pedro")?finalText:"Prueba Fallida");


        }catch (NoSuchElementException ne){
            System.err.println("Elemento no encontrado: " + ne.getMessage());
        } catch (NoSuchFrameException nf){
            System.err.println("No se encontro el frame esperado: " + nf.getMessage());
        } catch (NoAlertPresentException na){
            System.err.println("No se encontro la alerta esperada: " + na.getMessage());
        }catch (TimeoutException te) {
            System.err.println("Tiempo de espera excedido: " + te.getMessage());
        }catch (WebDriverException we){
            System.err.println("Error en WebDriver: " + we.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            driver.quit();
        }
    }
}
