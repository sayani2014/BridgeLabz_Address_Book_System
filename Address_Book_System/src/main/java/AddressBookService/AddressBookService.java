package AddressBookService;

import AddressBookModel.PersonInfo;

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
}
