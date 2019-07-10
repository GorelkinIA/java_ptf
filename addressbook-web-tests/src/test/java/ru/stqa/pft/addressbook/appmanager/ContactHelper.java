package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoNewContactPage() {
    click(By.linkText("add new"));
    }
  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
  }

  public void submitContactForm() {
     click(By.xpath("(//input[@name='submit'])[2]"));
   }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getFirstname());
    type(By.name("mobile"), contactData.getMobileTelephone());
    type(By.name("email"), contactData.getMail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
   }


  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
   // WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
   // WebElement row = checkbox.findElement(By.xpath("./../../"));
   // List<WebElement> cells = row.findElements(By.tagName("td"));
   // cells.get(7).findElement(By.tagName("a")).click();

   // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));

  }

  public void create(ContactData contact, boolean b) {
    gotoNewContactPage();
    fillContactForm(contact, b);
    submitContactForm();
    contactCash = null;
    returnToHomePage();
  }

  public void homePage() {
    click(By.linkText("home"));
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCash = null;
    homePage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCash = null;
    homePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCash = null;

  public Contacts all() {
    if(contactCash != null) {
      return new Contacts(contactCash);
    }
    contactCash = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.xpath(".//td[3]")).getText();
      String firstname = element.findElement(By.xpath(".//td[2]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCash.add(new ContactData().withId(id).withName(name).withFirstname(firstname));
    }
    return new Contacts(contactCash);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String firstname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(name).withFirstname(firstname).
            withHomePhone(home).withMobileTelephone(mobile).withWorkPhone(work);
  }
}
