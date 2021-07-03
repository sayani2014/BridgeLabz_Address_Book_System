package AddressBookService;

import AddressBookModel.PersonInfo;
import DAO.AddressBookDAO;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook implements AddressBookInterface{
    AddressBookDAO addressBookDAO = new AddressBookDAO();
    @Override
    public Hashtable<String, ArrayList<PersonInfo>> insertContactDetails() {
        return addressBookDAO.insertContactDetails();
    }

    @Override
    public void updateContact(String replacedContact, Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        addressBookDAO.updateContact(replacedContact, personInfoDict);
    }

    @Override
    public boolean findContact(String contact, Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        return addressBookDAO.findContact(contact, personInfoDict);
    }

    @Override
    public void editContactDetails(String replacedContact, Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        addressBookDAO.editContactDetails(replacedContact, personInfoDict);
    }

    @Override
    public void deleteContact(String deletedName, Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        addressBookDAO.deleteContact(deletedName, personInfoDict);
    }

    @Override
    public void displayCompanyContacts(Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        addressBookDAO.displayCompanyContacts(personInfoDict);
    }

    @Override
    public void searchPerson() {
        addressBookDAO.searchPerson();
    }
}
