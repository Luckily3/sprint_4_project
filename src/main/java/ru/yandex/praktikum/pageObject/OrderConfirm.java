package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirm {
  WebDriver driver;
  private final By orderConfirmHeader = By.xpath(".//div[text()='Заказ оформлен']");
  private final By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

  public OrderConfirm(WebDriver driver) {
    this.driver = driver;
  }


  //метод нажатия на кнопку «Да»
  public void clickYesButton() {
    new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(yesButton)).click();
  }

  //метод ожидания появления окна «Заказ оформлен»
  public String getHeaderAfterOrderCreation() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderConfirmHeader).getText() != null
            && !driver.findElement(orderConfirmHeader).getText().isEmpty()
    ));
    return driver.findElement(orderConfirmHeader).getText();
  }


}
