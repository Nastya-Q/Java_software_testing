package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                    .withEmail("test@qatest.com").withMobile("+7951000000").withGroup("test1");
            app.contact().createContact(contact, true);
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().delete(index);
        //if tests are executed in FireFox browser, then below sleep needed as deleted element is still on the page for short time according to debug (however it's not seen visually, maybe cashed css
        //Thread.sleep(5000);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
