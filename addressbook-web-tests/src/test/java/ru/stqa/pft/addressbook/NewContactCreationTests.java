package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class NewContactCreationTests extends TestBase {
   @Test
  public void testNewContactCreation() throws Exception {
    gotoNewContactPage();
    fillContactForm(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com"));
    submitContactForm();
    gotoHomePage();
  }
}
