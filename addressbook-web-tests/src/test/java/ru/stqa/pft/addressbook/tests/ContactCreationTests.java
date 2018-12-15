package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().gotoAddContactPage();
        ContactData contact = new ContactData()
                .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                .withEmail("test@qatest.com").withMobile("+7951000000").withGroup("test1");
        app.contact().createContact(contact, true);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
