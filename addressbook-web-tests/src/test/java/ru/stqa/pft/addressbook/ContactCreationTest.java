package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    gotoContactEditPage();
    fillContactForm(new ContactData("Ana", "Test", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000"));
    viewCreatedContact();
  }

}
