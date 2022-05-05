package com.artherus.carshop.controllers;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientsTest {

    @Test
    void addClient() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/clients");
        assertEquals("Клиенты", driver.getTitle());
        WebElement addClient = driver.findElement(By.id("addClientButton"));
        addClient.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавить клиента", driver.getTitle());
        driver.quit();
    }

    @Test
    void addAndDeleteClient() {
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/clients");
        int clientsNumber = driver.findElementsByClassName("deleteClientButton").size();
        WebElement addClient = driver.findElement(By.id("addClientButton"));
        addClient.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Добавить клиента", driver.getTitle());
        WebElement clientName = driver.findElement(By.id("clientName"));
        WebElement clientAddress = driver.findElement(By.id("clientAddress"));
        WebElement clientPhone = driver.findElement(By.id("clientPhone"));
        WebElement clientEmail = driver.findElement(By.id("clientEmail"));
        WebElement submitButton = driver.findElement(By.id("submitButton"));
        clientName.sendKeys("clientName");
        clientAddress.sendKeys("clientAddress");
        clientPhone.sendKeys("clientPhone");
        clientEmail.sendKeys("clientEmail");
        submitButton.click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Клиенты", driver.getTitle());
        List<WebElement> deleteButtons = driver.findElementsByClassName("deleteClientButton");
        assertEquals(clientsNumber + 1, deleteButtons.size());
        WebElement deleteButton = deleteButtons.stream().min(new WebElementYComparator()).get();
        deleteButton.click();
        assertEquals(clientsNumber, driver.findElementsByClassName("deleteClientButton").size());
        driver.quit();
    }

    static class WebElementYComparator implements Comparator<WebElement> {
        @Override
        public int compare(WebElement o1, WebElement o2) {
            return o1.getLocation().getY() - o2.getLocation().getY();
        }
    }

}
