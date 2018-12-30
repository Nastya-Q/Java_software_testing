package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                    .withEmail("test@qatest.com").withMobilePhone("+7951000000");
            app.contact().createContact(contact, true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, group);
    }
}
