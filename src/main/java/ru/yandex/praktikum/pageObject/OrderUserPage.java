package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderUserPage {

  private WebDriver driver;
  private final By orderHeader = By.className("Order_Header__BZXOb");
  private final By name = By.xpath(".//input[@placeholder='* Имя']");
  private final By surname = By.xpath(".//input[@placeholder='* Фамилия']");
  private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
  private final By metroStation = By.className("select-search__input");
  private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
  private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

  private final String changeMetroStation = ".//button[@value='%s']";


  public OrderUserPage(WebDriver driver) {
    this.driver = driver;
  }

  //метод ожидания загруки страницы заказа
  public OrderUserPage waitForLoadOrderPage() {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
            && !driver.findElement(orderHeader).getText().isEmpty()
    ));
    return this;
  }

  //метод ввода имени заказчика
  public OrderUserPage inputName(String newName) {
    driver.findElement(name).sendKeys(newName);
    return this;
  }

  //метод ввода фамилии заказчика
  public OrderUserPage inputSurname(String newSurname) {
    driver.findElement(surname).sendKeys(newSurname);
    return this;
  }

  //метод ввода адреса заказчика
  public OrderUserPage inputAddress(String newAddress) {
    driver.findElement(address).sendKeys(newAddress);
    return this;
  }

  //метод выбора станции метро
  public OrderUserPage changeMetroStation(int stationNumber) {
    driver.findElement(metroStation).click();
    By newMetroStation = By.xpath(String.format(changeMetroStation, stationNumber));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newMetroStation));
    driver.findElement(newMetroStation).click();
    return this;
  }

  //метод ввода телефона заказчика
  public OrderUserPage inputPhone(String newTelephone) {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(phoneNumber));
    driver.findElement(phoneNumber).sendKeys(newTelephone);
    return this;
  }

  //метод нажатия на кнопку «Далее»
  public void clickNextButton() {
    driver.findElement(nextButton).click();
  }

}