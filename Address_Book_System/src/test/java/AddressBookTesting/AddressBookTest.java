/**
 * UC16 : Ability for the AddressBook Service to retrieve all the Entries from the DB
 * UC17 : Ability to update the Contact Information in the address book for a person and ensure that the
 *        Contact Information in the memory is in Sync with the DB
 *
 * @author : SAYANI KOLEY
 * @since : 19.07.2021
 */

package AddressBookTesting;

import AddressBookModel.PersonInfo;
import AddressBookService.AddressBookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {
    AddressBookService addressBookService;

    @Before
    public void setUp() throws Exception {
        addressBookService = new AddressBookService();
    }

    /**
     * Purpose : To test whether the number of records present in the database matches with the expected value
     */

    @Test
    public void givenPersonInfoInDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        List<PersonInfo> personInfoData = addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(3, personInfoData.size());
    }

    /**
     * Purpose : To test whether the state is updated in the database and is synced with the DB
     *           - Read the values from the database
     *           - Update the state in the database
     *           - Test whether the database is correctly synced or not
     */

    @Test
    public void givenNewStateForPerson_WhenUpdated_ShouldSyncWithDB() {
        addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        addressBookService.updatePersonInfo("Sayani", "West Bengal");
        boolean result = addressBookService.checkPersonInfoInSyncWithDB("Sayani");
        Assert.assertTrue(result);
    }

}
