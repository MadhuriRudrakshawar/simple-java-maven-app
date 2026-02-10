package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearchIT {

    @Test
    void googleHomePageTitle_shouldContainGoogle() throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.google.com");
            assertTrue(driver.getTitle().contains("Google"));

        } catch (Throwable t) {

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Path.of("target/screenshots"));
            FileUtils.copyFile(screenshot, new File("target/screenshots/failure.png"));

            throw t;

        } finally {
            driver.quit();
        }
    }
}
