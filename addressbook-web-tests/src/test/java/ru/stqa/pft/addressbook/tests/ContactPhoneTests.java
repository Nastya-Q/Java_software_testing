package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456").withEmail("test@qatest.com")
                    .withMobile("+7951000000").withHomePhone("134").withWorkPhone("456").withGroup("test1");
            app.contact().createContact(contact, true);
        }
    }


//    @Test
//    public void testContactPhones() {
//        app.goTo().homePage();
//        ContactData contact = app.contact().all().iterator().next();
//        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
//    }
}
