package AddressBookService;

import AddressBookModel.PersonInfo;

import java.util.ArrayList;
import java.util.Hashtable;

public interface AddressBookInterface {
    abstract Hashtable<String, PersonInfo> insertContactDetails();
    abstract void updateContact(String replacedContact, Hashtable<String, PersonInfo> personInfoDict);
    abstract boolean findContact(String contact, Hashtable<String, PersonInfo> personInfoDict);
    abstract void editContactDetails(String replacedContact, Hashtable<String, PersonInfo> personInfoDict);
    abstract void deleteContact(String deletedName, Hashtable<String, PersonInfo> personInfoDict);
    abstract void displayCompanyContacts(Hashtable<String, PersonInfo> personInfoDict);
}
