package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData()
                .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                .withEmail("test@qatest.com").withMobile("+7951000000").withGroup("test1");
        app.contact().createContact(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
  }
}
