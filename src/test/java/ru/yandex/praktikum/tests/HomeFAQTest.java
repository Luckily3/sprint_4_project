package ru.yandex.praktikum.tests;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.pageobject.HomePageScooter;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.pageobject.constants.HomeFAQ.*;
import static ru.yandex.praktikum.pageobject.constants.ScooterURL.*;


@RunWith(Parameterized.class) //аннотация параметризованного теста
public class HomeFAQTest {
  private static WebDriver driver;
  private final By question;
  private final By answer;
  private final By result;
  private final String expected;

  public HomeFAQTest(By question, By answer, By result, String expected) {
    this.question = question;
    this.answer = answer;
    this.result = result;
    this.expected = expected;
  }

  @Parameterized.Parameters // параметры
  public static Object[][] getCredentials() {
    return new Object[][]{
            {QUESTION_0, ANSWER_0, FAQ_ANSWER_0, EXPECTED_ANSWER_0},
            {QUESTION_1, ANSWER_1, FAQ_ANSWER_1, EXPECTED_ANSWER_1},
            {QUESTION_2, ANSWER_2, FAQ_ANSWER_2, EXPECTED_ANSWER_2},
            {QUESTION_3, ANSWER_3, FAQ_ANSWER_3, EXPECTED_ANSWER_3},
            {QUESTION_4, ANSWER_4, FAQ_ANSWER_4, EXPECTED_ANSWER_4},
            {QUESTION_5, ANSWER_5, FAQ_ANSWER_5, EXPECTED_ANSWER_5},
            {QUESTION_6, ANSWER_6, FAQ_ANSWER_6, EXPECTED_ANSWER_6},
            {QUESTION_7, ANSWER_7, FAQ_ANSWER_7, EXPECTED_ANSWER_7},
    };
  }

  @BeforeClass
  public static void startUp() {
    driver = new ChromeDriver();
    driver.get(URL);
  }

  @Test
  public void checkQuestions() {
    new HomePageScooter(driver)
            .waitForLoadHomePage()
            .scrollToQuestions()
            .clickQuestion(question)
            .waitLoadAfterClickQuestion(result);
    String result = driver.findElement(answer).getText();

    assertEquals(expected, result);
  }

  @AfterClass
  public static void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }
}

