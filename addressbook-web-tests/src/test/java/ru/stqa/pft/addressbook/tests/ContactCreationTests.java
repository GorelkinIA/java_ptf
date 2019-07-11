package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

   @DataProvider
   public Iterator<Object[]> validContacts() {
      List<Object[]> list = new ArrayList<Object[]>();
      File photo = new File("src/test/resources/Ferma.png");
      list.add(new Object[]{new ContactData().withName("ivan1").withFirstname("Gorelkin1")
              .withMobileTelephone("887-551").withGroup("test1")});
      list.add(new Object[]{new ContactData().withName("ivan2").withFirstname("Gorelkin2")
              .withMobileTelephone("887-552").withGroup("test1")});
      list.add(new Object[]{new ContactData().withName("ivan3").withFirstname("Gorelkin3")
              .withMobileTelephone("887-553").withGroup("test1")});
      return list.iterator();
   }

   @Test (dataProvider = "validContacts")
  public void testNewContactCreation(ContactData contact) throws Exception {
     app.goTo().homePage();
     Contacts before = app.contact().all();
     app.contact().create(contact, true);
     assertThat(app.contact().count(), equalTo(before.size() + 1));
     Contacts after = app.contact().all();
     assertThat(after, equalTo(
             before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
