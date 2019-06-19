package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationTests(){
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData(before.get(before.size() -1).getId(),"ivan", "gorelkin", "+79039034141", "gorelkinivan94@gmail.com", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove (before.size() -1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
