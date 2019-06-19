package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String name;
  private final String firstname;
  private final String mobileTelephone;
  private final String mail;
  private String group;


  public ContactData(String name, String firstname, String mobileTelephone, String mail, String group) {
    this.name = name;
    this.firstname = firstname;
    this.mobileTelephone = mobileTelephone;
    this.mail = mail;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMobileTelephone() {
    return mobileTelephone;
  }

  public String getMail() {
    return mail;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(name, that.name) &&
            Objects.equals(firstname, that.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, firstname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }
}