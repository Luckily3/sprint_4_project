package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderScooterPage {
  WebDriver driver;
  private final By rentHeader = By.className("Order_Header__BZXOb");
  private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
  private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
  private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
  private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

  public OrderScooterPage(WebDriver driver) {
    this.driver = driver;
  }

  //метод ожидания загрузки страницы аренды
  public OrderScooterPage waitForLoadRentHeader() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
            && !driver.findElement(rentHeader).getText().isEmpty()
    ));
    return this;
  }

  //метод выбора даты привоза самоката
  public OrderScooterPage inputDate(String newDate) {
    driver.findElement(date).sendKeys(newDate);
    return this;
  }

  //метод выбора срока аренды
  public OrderScooterPage inputDuration(String newDuration) {
    driver.findElement(durationRent).click();
    new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu")));
    driver.findElement(By.xpath("//div[contains(@class, 'Dropdown-menu')]//div[text()='" + newDuration + "']")).click();
    return this;
  }

  //метод выбора цвета самоката
  public OrderScooterPage changeColour(By colour) {
    driver.findElement(colour).click();
    return this;
  }

  //метод выбора серового самоката


  //метод ввода комментария для курьера
  public OrderScooterPage inputComment(String newComment) {
    driver.findElement(comment).sendKeys(newComment);
    return this;
  }

  //метод нажатия на кнопку «Заказать»
  public void clickButtonCreateOrder() {
    driver.findElement(createOrderButton).click();
  }
}


