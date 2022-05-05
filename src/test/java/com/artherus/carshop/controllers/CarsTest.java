package com.artherus.carshop.controllers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarsTest {

    @Test
    void addCar() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/cars");
        assertEquals("Автомобили", driver.getTitle());
        WebElement addCar = driver.findElement(By.id("addCarButton"));
        addCar.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавить автомобиль", driver.getTitle());
        driver.quit();
    }

    @Test
    void addAndDeleteCar() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/cars");
        int carsNumber = driver.findElementsByClassName("deleteCarButton").size();
        WebElement addCar = driver.findElement(By.id("addCarButton"));
        addCar.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавить автомобиль", driver.getTitle());
        WebElement carModelStr = driver.findElement(By.id("carModelStr"));
        WebElement carColor = driver.findElement(By.id("carColor"));
        WebElement carPrice = driver.findElement(By.id("carPrice"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));
        carModelStr.sendKeys("X5");
        carColor.sendKeys("carColor");
        carPrice.sendKeys("12");
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Автомобили", driver.getTitle());
        List<WebElement> deleteButtons = driver.findElementsByClassName("deleteCarButton");
        assertEquals(carsNumber + 1, deleteButtons.size());
        WebElement deleteButton = deleteButtons.stream().min(new ClientsTest.WebElementYComparator()).get();
        deleteButton.click();
        assertEquals(carsNumber, driver.findElementsByClassName("deleteCarButton").size());
        driver.quit();
    }

}
