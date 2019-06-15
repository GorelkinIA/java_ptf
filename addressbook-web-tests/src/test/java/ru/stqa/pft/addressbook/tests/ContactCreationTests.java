package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {
   @Test
  public void testNewContactCreation() throws Exception {
     app.getNavigationHelper().gotoHomePage();
     List<ContactData> before = app.getContactHelper().getContactList();
     app.getContactHelper().createContact(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com", "test1"), true);
     app.getNavigationHelper();
     List<ContactData> after = app.getContactHelper().getContactList();
     Assert.assertEquals(after.size(), before.size() + 1);
  }
}
