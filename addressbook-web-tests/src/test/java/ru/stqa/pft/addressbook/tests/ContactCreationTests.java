package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().gotoAddContactPage();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData()
                .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                .withEmail("test@qatest.com").withMobilePhone("+7951000000").withGroup("test1")
                .withPhoto(photo);
        app.contact().createContact(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
