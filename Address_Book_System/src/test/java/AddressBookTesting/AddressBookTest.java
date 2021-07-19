/**
 * UC16 : Ability for the AddressBook Service to retrieve all the Entries from the DB
 * UC17 : Ability to update the Contact Information in the address book for a person and ensure that the
 *        Contact Information in the memory is in Sync with the DB
 * UC18 : Ability to Retrieve Contacts from the Database that were added in a particular period
 * UC19 : Ability to Retrieve number of Contacts in the Database by City or State
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

import java.time.LocalDate;
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
    public void givenPersonInfoInDB_WhenRetrieved_ShouldMatchPersonCount() {
        List<PersonInfo> personInfoData = addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(4, personInfoData.size());
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

    /**
     * Purpose : To test whether the count of the retrieved data who have been added in the address book
     *           in a particular data range matches with the expected value
     */

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchThePersonCount() {
        addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        LocalDate startDate = LocalDate.of(2019, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<PersonInfo> personInfoData = addressBookService.readPersonInfoForDateRange
                                                            (AddressBookService.IOService.DB_IO, startDate, endDate);
        Assert.assertEquals(3, personInfoData.size());
    }

    /**
     * Purpose : To test whether number of Contacts searched in the Database by State matches the expected value
     */

    @Test
    public void givenStateNameInDB_WhenRetrieved_ShouldMatchPersonCount() {
        addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        List<PersonInfo> personInfoData = addressBookService.readPersonInfoForProvidedState
                                                (AddressBookService.IOService.DB_IO, "West Bengal");
        Assert.assertEquals(2, personInfoData.size());
    }

    /**
     * Purpose : To test whether number of Contacts searched in the Database by City matches the expected value
     */

    @Test
    public void givenCityNameInDB_WhenRetrieved_ShouldMatchPersonCount() {
        addressBookService.readPersonInfoData(AddressBookService.IOService.DB_IO);
        List<PersonInfo> personInfoData = addressBookService.readPersonInfoForProvidedCity
                                                        (AddressBookService.IOService.DB_IO, "Howrah");
        Assert.assertEquals(1, personInfoData.size());
    }
}
