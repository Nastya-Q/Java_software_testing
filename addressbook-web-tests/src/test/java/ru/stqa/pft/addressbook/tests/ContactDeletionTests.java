package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContactPage();
            app.getContactHelper().createContact(new ContactData("Ana", "Test", "Moscow, Kremlin 456", "test@qatest.com", "+7951000000", "test1"), true);
            app.getNavigationHelper().goToHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactsList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigationHelper().goToHomePage();
        /*if tests are executed in FireFox browser, then below sleep needed
        as deleted element is still on the page for short time according to debug (however it's not seen visually, maybe cashed css?*/
        //Thread.sleep(5000);
        List<ContactData> after = app.getContactHelper().getContactsList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
