package AddressBookService;

import AddressBookModel.PersonInfo;
import DAO.AddressBookDAO;
import java.util.Hashtable;

public class AddressBook implements AddressBookInterface{
    AddressBookDAO addressBookDAO = new AddressBookDAO();
    @Override
    public Hashtable<String, PersonInfo> insertContactDetails() {
        return addressBookDAO.insertContactDetails();
    }

    @Override
    public void updateContact(String replacedContact, Hashtable<String, PersonInfo> personInfoDict) {
        addressBookDAO.updateContact(replacedContact, personInfoDict);
    }

    @Override
    public boolean findContact(String contact, Hashtable<String, PersonInfo> personInfoDict) {
        return addressBookDAO.findContact(contact, personInfoDict);
    }

    @Override
    public void editContactDetails(String replacedContact, Hashtable<String, PersonInfo> personInfoDict) {
        addressBookDAO.editContactDetails(replacedContact, personInfoDict);
    }

    @Override
    public void deleteContact(String deletedName, Hashtable<String, PersonInfo> personInfoDict) {
        addressBookDAO.deleteContact(deletedName, personInfoDict);
    }

    @Override
    public void displayCompanyContacts(Hashtable<String, PersonInfo> personInfoDict) {
        addressBookDAO.displayCompanyContacts(personInfoDict);
    }

}
