/**
 * UC16 : Ability for the AddressBook Service to retrieve all the Entries from the DB
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
}
