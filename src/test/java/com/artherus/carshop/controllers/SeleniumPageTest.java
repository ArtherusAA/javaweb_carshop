package com.artherus.carshop.controllers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumPageTest {

    @Test
    void MainPage() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals("Автосалон", driver.getTitle());
        driver.quit();
    }

    @Test
    void HeaderTest() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        WebElement clientsButton = driver.findElement(By.id("clientsListLink"));
        clientsButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Клиенты", driver.getTitle());

        WebElement carsButton = driver.findElement(By.id("carsListLink"));
        carsButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Автомобили", driver.getTitle());

        WebElement addOrderButton = driver.findElement(By.id("addOrder"));
        addOrderButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавить заказ", driver.getTitle());

        WebElement mainPageButton = driver.findElement(By.id("rootLink"));
        mainPageButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Автосалон", driver.getTitle());

        driver.quit();
    }

}
