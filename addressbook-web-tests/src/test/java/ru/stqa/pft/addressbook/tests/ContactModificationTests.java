package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withName("ivan").withFirstname("Gorelkin"), true);
    }
  }

  @Test
  public void testContactModificationTests(){
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/Ferma.png");
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withAddress(modifiedContact.getAddress()).withMail2(modifiedContact.getMail2())
            .withWorkPhone(modifiedContact.getWorkPhone()).withMail3(modifiedContact.getMail3())
            .withName("ivan").withFirstname("gorelkin").withMobileTelephone("+79039034141")
            .withMail("gorelkinivan94@gmail.com").withPhoto(photo);
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
