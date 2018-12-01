package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ana", "Test", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", "test1"), true);
            app.getNavigationHelper().goToHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();
    }
}
