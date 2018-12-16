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
                    .withFirstName("Ana").withLastName("Test").withAddress("  Russian Federation \n Moscow,    Kremlin 4/56")
                    .withEmail("test@qatest.com").withMobilePhone("+7(951)000000").withGroup("test1");
            app.contact().createContact(contact, true);
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(formatAddress(contactInfoFromEditForm.getAddress())));
    }

    private String formatAddress(String address) {
        return Arrays.asList(address.split("\n"))
                .stream()
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"))
                .replaceAll("^\\n+", ""); //this is to remove all empty strings in beginning of Address
    }

    //remove leading and trailing spaces and replace several spaces with one between word
    public static String cleaned(String addressEntry) {
        return addressEntry.trim().replaceAll("\\s+", " ");
    }
}
