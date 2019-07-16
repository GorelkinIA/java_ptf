package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase{

  @BeforeClass
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      File photo = new File("src/test/resources/Ferma.png");
      app.goTo().homePage();
      app.contact().create(new ContactData().withName("ivan")
              .withFirstname("Gorelkin").withPhoto(photo), true);
      app.contact().selectContact();
      app.contact().pressAdd();
      app.goTo().homePageAfterAddedGroup();
      app.contact().showAllContact();
    }
    if (app.db().contactInGroup().size() == 0) {
      app.goTo().homePage();
      app.contact().selectContact();
      app.contact().pressAdd();
      app.goTo().homePageAfterAddedGroup();
      app.contact().showAllContact();
    }
  }

  @Test
  public void testContactRemoveFromGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactRemoveFromGroup = contacts.iterator().next();
    Groups beforeRemove = contactRemoveFromGroup.getGroups();
    ContactData contact = new ContactData().withId(contactRemoveFromGroup.getId())
            .withName(contactRemoveFromGroup.getName())
            .withFirstname(contactRemoveFromGroup.getFirstname())
            .withMobileTelephone(contactRemoveFromGroup.getMobileTelephone())
            .withMail(contactRemoveFromGroup.getMail())
            .withMail2(contactRemoveFromGroup.getMail2())
            .withMail3(contactRemoveFromGroup.getMail3())
            .withHomePhone(contactRemoveFromGroup.getHomePhone())
            .withWorkPhone(contactRemoveFromGroup.getWorkPhone())
            .withAddress(contactRemoveFromGroup.getAddress())
            .inGroup(groups.iterator().next());

    app.goTo().homePage();
    app.contact().removeContactFromGroup();
    app.goTo().homePage();
    app.contact().showAllContact();

    Contacts contactsAfter = app.db().contacts();
    ContactData contactAfterRemoveFromGroup = contactsAfter.iterator().next();
    Groups afterRemove = contactAfterRemoveFromGroup.getGroups();
    assertThat(afterRemove.size(), equalTo(beforeRemove.size() - 1));

    assertThat(app.contact().count(), equalTo(contacts.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(contacts.without(contactRemoveFromGroup).withAdded(contact)));
    verifyContactListInUI();
  }
}
