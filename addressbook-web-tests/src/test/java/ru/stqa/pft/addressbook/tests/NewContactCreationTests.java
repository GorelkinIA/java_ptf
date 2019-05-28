package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {
   @Test
  public void testNewContactCreation() throws Exception {
    app.gotoNewContactPage();
    app.fillContactForm(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com"));
    app.submitContactForm();
    app.gotoHomePage();
  }
}
