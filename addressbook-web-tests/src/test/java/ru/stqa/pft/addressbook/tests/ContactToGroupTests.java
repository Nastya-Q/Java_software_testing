package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Random;

public class ContactToGroupTests extends TestBase {

    ContactData contact;
    GroupData group;

    @BeforeClass
    public void ensurePreconditions() {

        //1. create group and contact if there is no one of them
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Moscow, Kremlin 456")
                    .withEmail("test@qatest.com").withMobilePhone("+7951000000");
            app.contact().createContact(contact, true);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }

        // 2. Find any group not assigned to selected contact. If no such group, then create new group
        Contacts contactsBefore = app.db().contacts();
        Groups groups = app.db().groups();
        contact = contactsBefore.iterator().next();
        group = null;
        //find any not assigned group
        group = findNotAssignedGroup(groups, contact);
        //if contact is already in all groups, then create new group:
        if (group == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test" + new Random().nextInt(500)));
            Groups refreshedGroups = app.db().groups();
            group = findNotAssignedGroup(refreshedGroups, contact);
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, group);
        Contacts contactsAfter = app.db().contacts();
        ContactData updatedContact = contactsAfter.stream().filter((c) -> c.getId() == contact.getId())
                .findAny().orElse(null);
        Boolean result = checkIfContactInGroup(updatedContact, group);
        Assert.assertTrue(result);
    }

    @Test (dependsOnMethods = "testAddContactToGroup") //this test uses as precondition previous test and will not run if testAddContactToGroup failed
    public void testDeleteContactFromGroup() {
        app.goTo().homePage();
        app.contact().deleteContactFromGroup(contact, group); //contact and group were assigned in previous @Test: testAddContactToGroup
        Contacts contactsAfter = app.db().contacts();
        ContactData updatedContact = contactsAfter.stream().filter((c) -> c.getId() == contact.getId())
                .findAny().orElse(null);
        Boolean result = checkIfContactInGroup(updatedContact, group);
        Assert.assertFalse(result);
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

    private Boolean checkIfContactInGroup(ContactData contact, GroupData group) {
        Groups groupsOfContact = contact.getGroups();
        return groupsOfContact.stream().anyMatch((g) -> g.getId() == group.getId());
    }
}
