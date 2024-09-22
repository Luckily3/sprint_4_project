package ru.yandex.praktikum.Tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageObject.HomePageScooter;
import ru.yandex.praktikum.pageObject.OrderScooterPage;
import ru.yandex.praktikum.pageObject.OrderUserPage;
import ru.yandex.praktikum.pageObject.OrderConfirm;


import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.pageObject.constants.RentDuration.*;
import static ru.yandex.praktikum.pageObject.constants.ScooterColour.*;



@RunWith(Parameterized.class)
public class OrderScooterTest {
  private WebDriver driver;
  private final String url = "https://qa-scooter.praktikum-services.ru/";
  private final String name;
  private final String surname;
  private final String address;
  private final int metroStationNumber;
  private final String phoneNumber;
  private final String date;
  private final String duration;
  private final By colour;
  private final String comment;
  private final String expectedHeader = "Заказ оформлен";



  public OrderScooterTest(String name, String surname, String address, int metroStationNumber, String phoneNumber,
                          String date, String duration, By colour, String comment) {

    this.name = name;
    this.surname = surname;
    this.address = address;
    this.metroStationNumber = metroStationNumber;
    this.phoneNumber = phoneNumber;
    this.date = date;
    this.duration = duration;
    this.colour = colour;
    this.comment = comment;
  }

  @Parameterized.Parameters
  public static Object[][] getParameters() {
    return new Object[][]{
            {"Сергей", "Певцов", "Москва, Улица Строителей дом 50", 1, "+79538276058", "30.09.2024", ONE_DAY, BLACK, "Позвонить за час"},
            {"Александр", "Савельев", "г. Москва, Озерная ул., д. 23 кв.108", 11, "+79835564357", "01.10.2024", TWO_DAYS, GREY, "Не звонить"},
            {"Анна", "Петрова", "ул. 17 Сентября, д. 13 кв.31, Москва", 3, "+79864758640", "02.10.2024", THREE_DAYS, BLACK, "Домофон 33, этаж 6"},
            {"Инга", "Васильева", "Улица Пролетарская дом 9, г. Москва", 13, "+79087215838", "25.09.2024", FOUR_DAYS, GREY, "Около подъезда"},
            {"Саша", "Саша", "город Москва, Лесная ул., д. 19 кв.117", 123, "+79092858657", "29.09.2024", FIVE_DAYS, BLACK, "Набрать у проходной"},
            {"Иван", "Иванов", "Москва, Школьная ул., д. 11 кв.80", 16, "+79053256047", "05.10.2024", SIX_DAYS, GREY, "no comments"},
            {"Павел", "Дуров", "Москва Петровский бульвар", 10, "+79865971575", "30.09.2024", SEVEN_DAYS, BLACK, ""},
    };
  }

  @Before
  public void startUp() {
      driver = new FirefoxDriver();
      driver.get(url);
  }

  @After
  public void teardown() {
    driver.quit();
  }


  @Test
  public void testCreateOrderHeaderButton() {
    new HomePageScooter(driver)
            .waitForLoadHomePage()
            .clickCookieButton()
            .clickHeaderOrderButton();

    new OrderUserPage(driver)
            .waitForLoadOrderPage()
            .inputName(name)
            .inputSurname(surname)
            .inputAddress(address)
            .changeMetroStation(metroStationNumber)
            .inputPhone(phoneNumber)
            .clickNextButton();

    new OrderScooterPage(driver)
            .waitForLoadRentHeader()
            .inputDate(date)
            .inputDuration(duration)
            .changeColour(colour)
            .inputComment(comment)
            .clickButtonCreateOrder();

    OrderConfirm orderConfirm = new OrderConfirm(driver);
    orderConfirm.clickYesButton();

    assertTrue(orderConfirm.getHeaderAfterOrderCreation().contains(expectedHeader));
  }



  @Test
  public void testCreateOrderMiddleButton() {
    new HomePageScooter(driver)
            .waitForLoadHomePage()
            .clickCookieButton()
            .clickMiddleOrderButton();

    new OrderUserPage(driver)
            .waitForLoadOrderPage()
            .inputName(name)
            .inputSurname(surname)
            .inputAddress(address)
            .changeMetroStation(metroStationNumber)
            .inputPhone(phoneNumber)
            .clickNextButton();

    new OrderScooterPage(driver)
            .waitForLoadRentHeader()
            .inputDate(date)
            .inputDuration(duration)
            .changeColour(colour)
            .inputComment(comment)
            .clickButtonCreateOrder();

    OrderConfirm orderConfirm = new OrderConfirm(driver);
    orderConfirm.clickYesButton();

    assertTrue(orderConfirm.getHeaderAfterOrderCreation().contains(expectedHeader));
  }
}