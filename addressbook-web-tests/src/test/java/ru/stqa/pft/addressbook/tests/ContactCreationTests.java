package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
   @Test
  public void testNewContactCreation() throws Exception {
     app.getNavigationHelper().gotoHomePage();
     int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com", "test1"), true);
     int after = app.getContactHelper().getContactCount();
     Assert.assertEquals(after, before + 1);
  }
}
