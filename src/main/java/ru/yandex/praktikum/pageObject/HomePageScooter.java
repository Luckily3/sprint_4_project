package ru.yandex.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter {

  private WebDriver driver;

  // Заголовок страницы
  private final By homeHeader = By.className("Home_Header__iJKdX");

  // Кнопка принятия куки «Да все привыкли»
  private final By cookieButton = By.id("rcc-confirm-button");

  // Кнопка «Заказать» (вверху страницы)
  private final By headerOrderButton = By.cssSelector(".Button_Button__ra12g");

  // Кнопка «Заказать»  (внизу страницы)
  private final By middleOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

  // Кнопка статус «Статус заказа»
  private final By orderState = By.xpath(".//button[@text()='Статус заказа']");

  // Строка Введите номер заказа
  private final By orderInput = By.xpath(".//input[@placeholder()='Введите номер заказа']");

  // Кнопка  «Go»
  private final By goButton = By.xpath(".//button[@text()='Статус заказа']");

  // Вопросы о важном
  private final By faqHeader = By.className("Home_FAQ__3uVm4");

  // Логотип «Самокат»
  private final By scooterButton = By.cssSelector(".img[alt='Yandex']");

  // Логотип «Яндекс»
  private final By yandexButton = By.cssSelector(".img[alt='Scooter']");


  public HomePageScooter(WebDriver driver){
    this.driver = driver;
  }

  // метод ожидания прогрузки главной страницы
  public HomePageScooter waitForLoadHomePage() {
    new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(homeHeader).getText() != null
            && !driver.findElement(homeHeader).getText().isEmpty()
    ));
    return this;
  }

  //метод принятия куки
  public HomePageScooter clickMiddleOrderButton() {
    driver.findElement(middleOrderButton).click();
    return this;
  }



  //метод прокрутки к разделу «Вопросы о важном»
  public HomePageScooter scrollToQuestions() {
    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(faqHeader));
    return this;
  }

// Метод, который нажимает на вопрос
  public HomePageScooter clickQuestion(By question) {
    new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(question))
            .click();
    return this;
  }

  //метод ожидания загрузки ответа на вопрос
  public void waitLoadAfterClickQuestion(By accordionLabel) {
    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
            && !driver.findElement(accordionLabel).getText().isEmpty()
    ));
  }

  public HomePageScooter clickHeaderOrderButton() {
    driver.findElement(headerOrderButton).click();
    return this;
  }

  public HomePageScooter clickCookieButton() {
    driver.findElement(cookieButton).click();
    return this;
  }




}


