/**
 * Refactor to add multiple Address Book to the System.
 * Each Address Book has a unique Name
 * Use Console to add new Address Book
 * Maintain Dictionary of Address Book Name to Address Book
 *
 * @author: SAYANI KOLEY
 * @since: 16.06.2021
 */

package AddressBookController;

import AddressBookModel.PersonInfo;
import AddressBookService.AddressBook;
import Util.UserInputOutput;

import java.util.*;

public class AddressBookSystem {
    private static final int ADD = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int DISPLAY = 4;
    private static final int SEARCH_CITY = 5;
    private static final int QUIT = 6;
    static AddressBook add_Book = new AddressBook();
    static Scanner input = new Scanner(System.in);

    public static void main(String args[]){
        Hashtable<String, ArrayList<PersonInfo>> personInfoDict = new Hashtable<>();

        boolean flag = true;
        int option;
        while(flag) {
            option = UserInputOutput.menu();
            switch (option) {
                case ADD:
                    System.out.println("\n" + "Add a new Address Book");
                    personInfoDict = add_Book.insertContactDetails();
                    System.out.println(personInfoDict + "\n");
                    break;
                case EDIT:
                    System.out.print("\n" + "Enter the name of the Address Book that you want to replace: ");
                    String companyName = input.next();

                    add_Book.updateContact(companyName, personInfoDict);
                    break;
                case DELETE:
                    System.out.print("\n" + "Enter the name of the Address Book that you want to delete: ");
                    String deletedName = input.next();
                    add_Book.deleteContact(deletedName, personInfoDict);
                    break;
                case DISPLAY:
                    System.out.println("\n" + "Display all contacts in the Address Book");
                    add_Book.displayCompanyContacts(personInfoDict);
                    break;
                case SEARCH_CITY:
                    System.out.println("\n" + "Search Address Book based on City");
                    add_Book.searchPerson();
                    flag = true;
                    break;
                case QUIT:
                    flag = false;
                    System.out.println("\n" + "Thank you for referring the address book.");
                    break;
            }
        }
    }
}
