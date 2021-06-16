/**
 * Ability to add multiple person to Address Book
 * Use Console to add person details one at a time
 * Use Collection Class to maintain multiple contact persons in Address Book
 *
 * @author: SAYANI KOLEY
 * @since: 16.06.2021
 */

import java.util.ArrayList;
import java.util.Scanner;

class PersonInfo {
    String first_name;
    String last_name;
    String address;
    String city;
    String state;
    int zip;
    String phone_number;
    String email;

    public PersonInfo(String first_name, String last_name, String address, String city, String state, int zip, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }
}
class AddressBook {
    ArrayList<PersonInfo> persons;
    PersonInfo p;
    Scanner input = new Scanner(System.in);

    public AddressBook() {
        persons = new ArrayList<PersonInfo>();
    }
    public void insertContactDetails() {

        System.out.print("Enter the First Name: ");
        String first_name = input.next().toString();
        System.out.print("Enter the Last Name: ");
        String last_name = input.next().toString();
        System.out.print("Enter the Address: ");
        String address = input.next().toString();
        System.out.print("Enter the City: ");
        String city = input.next().toString();
        System.out.print("Enter the State: ");
        String state = input.next().toString();
        System.out.print("Enter the Zip: ");
        int zip = input.nextInt();
        System.out.print("Enter the Phone Number: ");
        String phone_number = input.next().toString();
        System.out.print("Enter the Email: ");
        String email = input.next().toString();

        System.out.println();

        p = new PersonInfo(first_name, last_name, address, city, state, zip, phone_number, email);
        addData(first_name, last_name, address, city, state, zip, phone_number, email);
    }
    public void addData(String first_name, String last_name, String address, String city, String state, int zip, String phone_number, String email) {
        //add the above PersonInfo object to arraylist
        persons.add(p);
    }
    public void display(ArrayList<PersonInfo> persons) {
        for(int i=0; i < persons.size(); i++) {
            System.out.println("First Name: " + persons.get(i).first_name);
            System.out.println("Last Name: " + persons.get(i).last_name);
            System.out.println("Address: " + persons.get(i).address);
            System.out.println("City: " + persons.get(i).city);
            System.out.println("State: " + persons.get(i).state);
            System.out.println("Zip: " + persons.get(i).zip);
            System.out.println("Phone number: " + persons.get(i).phone_number);
            System.out.println("Email: " + persons.get(i).email);
            System.out.println();
        }
    }
    public void updateContact(String replacedContact) {
        int index = findContact(replacedContact);
        if (index >= 0) {
            editContactDetails(index, persons, persons.get(index).first_name,persons.get(index).last_name, persons.get(index).address, persons.get(index).city, persons.get(index).state, persons.get(index).zip, persons.get(index).phone_number, persons.get(index).email);
        }
        else {
            System.out.println("No such contact found to update");
        }
    }

    public int findContact(String contact) {
        int index = 0;
        for(int i=0; i < persons.size(); i++) {
            if(persons.get(i).first_name.equals(contact))
                index = persons.get(i).first_name.indexOf(p.first_name, persons.indexOf(i));
        }
        return index;
    }
    private void editContactDetails(int index, ArrayList<PersonInfo> persons, String first_name, String last_name, String address, String city, String state, int zip, String phone_number, String email) {
        System.out.println("Choose your edit option: ");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Address");
        System.out.println("4. City");
        System.out.println("5. State");
        System.out.println("6. Zip");
        System.out.println("7. Phone Number");
        System.out.println("8. Email");
        int editOption = input.nextInt();

        switch (editOption) {
            case 1:
                System.out.print("Enter new First Name: ");
                first_name = input.next().toString();
                break;
            case 2:
                System.out.println("Enter new Last Name: ");
                last_name = input.next().toString();
                break;
            case 3:
                System.out.println("Enter new Address: ");
                address = input.next().toString();
                break;
            case 4:
                System.out.println("Enter new City: ");
                city = input.next().toString();
                break;
            case 5:
                System.out.println("Enter new State: ");
                state = input.next().toString();
                break;
            case 6:
                System.out.println("Enter new Zip: ");
                zip = input.nextInt();
                break;
            case 7:
                System.out.println("Enter new Phone Number: ");
                phone_number = input.next().toString();
                break;
            case 8:
                System.out.println("Enter new Email: ");
                email = input.next().toString();
                break;

        }

        //updates the details of the persons
        persons.set(index, new PersonInfo(first_name, last_name, address, city, state, zip, phone_number, email));
    }
    public void deleteContact(String deletedName) {
        int index = findContact(deletedName);
        //checks if entered name matches first name of any person if yes then edit the persons details
        if(index >= 0) {
            if (deletedName.equals(persons.get(index).first_name)) {
                persons.remove(index);
                System.out.println("Contact in Address Book Deleted.");
            }
        }
    }
}
public class AddressBookSystem {
    private static final int ADD = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int DISPLAY = 4;
    private static final int QUIT = 5;
    static AddressBook add_Book = new AddressBook();

    public static void main(String args[]){
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int option;

        while (flag) {
            //Option to choose
            option = menu(input);

            //Add contact detail
            flag = performFunction(option, input);
        }
    }
    public static int menu(Scanner input) {
        System.out.println("Select option: ");
        System.out.println("1. Add a new contact in the address book.");
        System.out.println("2. Edit contact in the address book.");
        System.out.println("3. Delete contact in the address book.");
        System.out.println("4. Display all values from address book.");
        System.out.println("5. Quit.");
        int option = input.nextInt();

        return option;
    }
    public static boolean performFunction(int option, Scanner input) {
        boolean flag = true;
        switch(option) {
            case ADD:
                System.out.println("Enter the number of contacts you want to add: ");
                int no_of_contacts = input.nextInt();
                for(int i = 0; i < no_of_contacts; i++) {
                    //Insert contact details
                    add_Book.insertContactDetails();
                }
                flag = true;
                break;
            case EDIT:
                System.out.println("Enter the name of the contact that you want to replace");
                String replacedName = input.next().toString();
                add_Book.updateContact(replacedName);
                flag = true;
                break;
            case DELETE:
                System.out.println("Enter the name of the contact that you want to delete");
                String deletedName = input.next().toString();
                add_Book.deleteContact(deletedName);
                flag = true;
                break;
            case DISPLAY:
                //Display the values
                add_Book.display(add_Book.persons);
                flag = true;
                break;
            case QUIT:
                flag = false;
                System.out.println("Thank you for referring the address book.");
                break;
        }
        return flag;
    }
}
