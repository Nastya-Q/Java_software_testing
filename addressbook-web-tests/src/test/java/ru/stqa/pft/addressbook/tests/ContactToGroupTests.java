package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
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
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contactsBefore.iterator().next();
        GroupData group = null;
        //find any not assigned group
        group = findNotAssignedGroup(groups, contact);
        //if contact is already in all groups, then create new group:
        if (group == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
            Groups refreshedGroups = app.db().groups();
            group = findNotAssignedGroup(refreshedGroups, contact);
        }
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, group);
        Contacts contactsAfter = app.db().contacts();
        ContactData updatedContact = contactsAfter.stream().filter((c) -> c.getId() == contact.getId())
                .findAny().orElse(null);
        Boolean result = checkIfContactInGroup(updatedContact, group);
        Assert.assertTrue(result);
    }

    private GroupData findNotAssignedGroup(Groups groups, ContactData contact) {
        GroupData group = null;
        for (GroupData groupToCheck: groups) {
            if (!checkIfContactInGroup(contact, groupToCheck)) {
                group = groupToCheck;
                break;
            }
        }
        return group;
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contactsBefore.iterator().next();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().deleteContactFromGroup(contact, group);
        Contacts contactsAfter = app.db().contacts();
        ContactData updatedContact = contactsAfter.stream().filter((c) -> c.getId() == contact.getId())
                .findAny().orElse(null);
        Boolean result = checkIfContactInGroup(updatedContact, group);
        Assert.assertFalse(result);
    }

    private Boolean checkIfContactInGroup(ContactData contact, GroupData group) {
        Groups groupsOfContact = contact.getGroups();
        return groupsOfContact.stream().anyMatch((g) -> g.getId() == group.getId());
    }
}
