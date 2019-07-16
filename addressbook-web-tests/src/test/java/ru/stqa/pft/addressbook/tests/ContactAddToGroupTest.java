package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

  @BeforeClass
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("testPups"));
    }

    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/Ferma.png");
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("ivan")
              .withFirstname("Gorelkin").withPhoto(photo), true);
    }
  }


  @Test
  public void testContactAddToGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactAddToGroup = contacts.iterator().next();
    Groups beforeAdd = contactAddToGroup.getGroups();
    ContactData contact = new ContactData().withId(contactAddToGroup.getId())
            .withName(contactAddToGroup.getName())
            .withFirstname(contactAddToGroup.getFirstname())
            .withMobileTelephone(contactAddToGroup.getMobileTelephone())
            .withMail(contactAddToGroup.getMail())
            .withMail2(contactAddToGroup.getMail2())
            .withMail3(contactAddToGroup.getMail3())
            .withHomePhone(contactAddToGroup.getHomePhone())
            .withWorkPhone(contactAddToGroup.getWorkPhone())
            .withAddress(contactAddToGroup.getAddress())
            .inGroup(groups.iterator().next());

    app.goTo().homePage();
    app.contact().addToGroup(contactAddToGroup, groups.iterator().next());
    app.goTo().homePage();
    app.contact().showAllContact();

    Contacts contactsAfter = app.db().contacts();
    ContactData contactAfterAddToGroup = contactsAfter.iterator().next();
    Groups afterAdd = contactAfterAddToGroup.getGroups();

    assertThat(afterAdd.size(), equalTo(beforeAdd.size() + 1));
    assertThat(app.contact().count(), equalTo(contacts.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(contacts.without(contactAddToGroup).withAdded(contact)));
    verifyContactListInUI();
  }
}
