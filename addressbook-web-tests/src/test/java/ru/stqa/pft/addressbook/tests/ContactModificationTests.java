package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ana", "Test", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", "test1"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().openEditContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Ana-changed", "Test-changed", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
