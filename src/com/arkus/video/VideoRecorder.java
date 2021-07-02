package com.arkus.video;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder {

    String chromePath = System.getProperty("user.dir") + "\\lib\\driver\\chromedriver.exe";

    private ScreenRecorder screenRecorder;

    private void stopRecording() throws IOException{
        this.screenRecorder.stop();
    }

    private void starRecording(String videoPath) throws IOException, AWTException {
        File file = new File(videoPath);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        this.screenRecorder = new GrabarPantalla(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE,MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey,MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                QualityKey,1.0f, KeyFrameIntervalKey, 15*60),
                new Format(MediaTypeKey,MediaType.VIDEO, EncodingKey, "black",FrameRateKey, Rational.valueOf(30)),
                        null, file, "ScreenRecordPedro");

        this.screenRecorder.start();

    }

    @Test
    public void videoTest() throws IOException, AWTException {
        VideoRecorder vr = new VideoRecorder();
        System.setProperty("webdriver.chrome.driver",chromePath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        vr.starRecording(System.getProperty("user.dir" + "\\video\\"));

        driver.get("https://www.google.com/");

        WebElement txtSearch =  driver.findElement(By.name("q"));
        txtSearch.sendKeys("Tester Fabrik");
        txtSearch.submit();
        System.out.println("The Page title is: " + driver.getTitle());
        driver.quit();

        vr.stopRecording();
    }


}
