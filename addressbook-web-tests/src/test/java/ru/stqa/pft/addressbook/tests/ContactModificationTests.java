package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ana", "Test", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", "test1"), true);
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().openEditContactPage(before.get(before.size()-1).getId());// Open Edit page for last contact on the page
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Ana-changed", "Test-changed", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()); //using Lambda function
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
