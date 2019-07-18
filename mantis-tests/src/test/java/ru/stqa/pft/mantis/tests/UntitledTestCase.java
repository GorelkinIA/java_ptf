package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UntitledTestCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://localhost/mantisbt-2.21.1/login_page.php");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("administrator");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Warning:'])[1]/preceding::input[1]")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("root");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Only allow your session to be used from this IP address.'])[1]/following::input[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='summary_link'])[1]/following::i[1]")).click();
    driver.findElement(By.linkText("manage_users_link")).click();
    driver.findElement(By.linkText("user2")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notify user of this change'])[1]/following::input[3]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
