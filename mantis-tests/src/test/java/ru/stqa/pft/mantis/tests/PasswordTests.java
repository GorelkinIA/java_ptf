package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;
import java.util.List;

public class PasswordTests extends TestBase{

  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
    HttpSession session = app.newSession();
  }

   @Test
  public void testPassword() {
    //увести в отдельный метод как логин админа и возможно сделать его параметризированным
     app.getDriver().findElement(By.id("username")).sendKeys("administrator");
     app.getDriver().findElement(By.cssSelector("input[value='Login']")).click();
     app.getDriver().findElement(By.id("password")).sendKeys("root");
     app.getDriver().findElement(By.cssSelector("input[value='Login']")).click();

     //Переход на страницу с manage_link
     List<WebElement> elements = app.getDriver().findElements(By.id("sidebar"));
     for (WebElement element : elements) {
       element.findElement(By.xpath(".//ul//li[6]")).click();
     }

     //увести в отдельный метод как переход на опр. вкладку. В данном случае- список пользователей
     app.getDriver().findElement(By.linkText("manage_users_link")).click();

     //выбор опр. пользователя. Надо реализовать через DB хелпер, чтобы в дальнейшем выбирался любой пользователь
     app.getDriver().findElement(By.linkText("user2")).click();

     // кликаем на изменить пароль
     app.getDriver().findElement(By.cssSelector("input[value='Reset Password']")).click();

     // До всего этого надо было получить информацию по всем пользователям в базе данных и выбрать одного
     // уже зная информацию о нем
     // далее, по идее, нужно проверить почту конкретного пользователя, перейти по ссылке и изменить пароль.
     // и затем надо войти в систему под НОВЫМ паролем через протокол http
     // да фигня! ЛЕГКО!
  }
}
