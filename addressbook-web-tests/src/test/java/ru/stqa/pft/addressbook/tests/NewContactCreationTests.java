package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {
   @Test
  public void testNewContactCreation() throws Exception {
    app.getNavigationHelper().gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com"));
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().gotoHomePage();
  }
}
