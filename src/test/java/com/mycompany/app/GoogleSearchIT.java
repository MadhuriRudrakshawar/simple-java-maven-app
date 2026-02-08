package com.mycompany.app;

<<<<<<< HEAD
=======

>>>>>>> 86a1593cee4f7f0a68ed2be8a03ec17b40c5bb01
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchIT {

    @Test
    void verifyGoogleHomePageLoads() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        assertTrue(driver.getTitle().contains("Google"));

        driver.quit();
    }
}
