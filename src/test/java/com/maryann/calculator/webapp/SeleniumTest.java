package com.maryann.calculator.webapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

    @LocalServerPort
    private int port;
    private WebDriver driver;

    private String base;

    @Value("${chrome.driver}")
    private String chDriver;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", chDriver);
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
    }

    @Test
    public void testTest() throws Exception {
        driver.get(base + "/home");
        driver.findElement(By.id("b7")).click();
        Thread.sleep(500);
        driver.findElement(By.id("b*")).click();
        Thread.sleep(500);
        driver.findElement(By.id("b7")).click();
        Thread.sleep(500);
        driver.findElement(By.id("b=")).click();
        Thread.sleep(1000);
        String res = driver.findElement(By.id("result")).getAttribute("value");
        Assert.assertEquals("49", res);
        driver.close();
    }

}
