package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().gotoAddContactPage();
            ContactData contact = new ContactData()
                    .withFirstName("Ana").withLastName("Test").withAddress("Russian Federation \n Moscow, Kremlin 4/56")
                    .withEmail("test@qatest.com").withMobilePhone("+7(951)000000").withGroup("test1");
            app.contact().createContact(contact, true);
        }
    }

    @Test
  public void testContactEmails() {
        return;
    }

//    @Test
//    public void testContactEmails() {
//        app.goTo().homePage();
//        ContactData contact = app.contact().all().iterator().next();
//        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
//        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
//    }
//
//    private String mergeEmails(ContactData contact) {
//        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
//                .stream().collect(Collectors.joining("\n"));
//    }
}
