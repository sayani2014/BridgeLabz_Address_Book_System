package AddressBookService;

import AddressBookModel.PersonInfo;

import java.time.LocalDate;
import java.util.List;

public class AddressBookService {
    private AddressBookDBService addressBookDBService;
    private List<PersonInfo> personInfoList;

    public enum IOService {
        DB_IO
    }

    public AddressBookService() {
        addressBookDBService = AddressBookDBService.getInstance();
    }

    /**
     * Purpose : To get the list of person info from the database
     *
     * @param ioService
     * @return
     */

    public List<PersonInfo> readPersonInfoData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.personInfoList = addressBookDBService.readData();
        return this.personInfoList;
    }

    /**
     * Purpose : To update the Person Info State in the database
     *           If the value is updated, the result value returned is greater than 0; else it is returned 0
     *           Match the given name with the PersonInfo list
     *           If found, assign the given state to the PersonInfo list
     *
     * @param name
     * @param state
     */

    public void updatePersonInfo(String name, String state) {
        int result = addressBookDBService.updatePersonInfo(name, state);
        if(result == 0)
            return;
        PersonInfo personInfoData = this.getPersonInfoData(name);
        if( personInfoData != null )
            personInfoData.state = state;
    }

    /**
     * Purpose : Check the PersonInfo list for the name
     *           If found, return the value else return null
     *
     * @param name
     * @return
     */

    private PersonInfo getPersonInfoData(String name) {
        return this.personInfoList.stream()
                .filter(personInfo -> personInfo.first_name.equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Purpose : To check whether the PersonInfo is in sync with the DB
     *           Use to equals() to compare the values
     *
     * @param name
     * @return
     */

    public boolean checkPersonInfoInSyncWithDB(String name) {
        List<PersonInfo> personInfoDataList = addressBookDBService.getPersonInfoData(name);
        return personInfoDataList.get(0).equals(getPersonInfoData(name));
    }

    /**
     * Purpose : Retrieve the data for a particular date range
     *
     * @param ioService
     * @param startDate
     * @param endDate
     * @return
     */

    public List<PersonInfo> readPersonInfoForDateRange(IOService ioService, LocalDate startDate, LocalDate endDate) {
        if( ioService.equals(IOService.DB_IO) )
            return addressBookDBService.getPersonInfoData(startDate, endDate);
        return null;
    }

}
