package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                    .withEmail("test@qatest.com").withMobile("+7951000000").withGroup("test1");
            app.contact().createContact(contact, true);
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        //if tests are executed in FireFox browser, then below sleep needed as deleted element is still on the page for short time according to debug (however it's not seen visually, maybe cashed css
        //Thread.sleep(5000);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
