/**
 * Ability to add a new Contact to Address Book
 * Use Console to add person details from AddressBookMain class
 * Use Object Oriented Concepts to manage relationship between AddressBook and Contact Person
 *
 * @author: SAYANI KOLEY
 * @since: 15/06/2021
 */

import java.util.Hashtable;
import java.util.Scanner;

class Contact_Person {
    String first_name;
    String last_name;
    String address;
    String city;
    String state;
    int zip;
    String phone_number;
    String email;

    public Hashtable<String, String> insertContactDetails(Scanner input, Hashtable<String, String> totalContact) {
        System.out.print("Enter the First Name: ");
        this.first_name = input.next().toString();
        totalContact.put("first_name", first_name);
        System.out.print("Enter the Last Name: ");
        this.last_name = input.next().toString();
        totalContact.put("last_name", last_name);
        System.out.print("Enter the Address: ");
        this.address = input.next().toString();
        totalContact.put("address", address);
        System.out.print("Enter the City: ");
        this.city = input.next().toString();
        totalContact.put("city", city);
        System.out.print("Enter the State: ");
        this.state = input.next().toString();
        totalContact.put("state", state);
        System.out.print("Enter the Zip: ");
        this.zip = input.nextInt();
        totalContact.put("zip", String.valueOf(zip));
        System.out.print("Enter the Phone Number: ");
        this.phone_number = input.next().toString();
        totalContact.put("phone_number", phone_number);
        System.out.print("Enter the Email: ");
        this.email = input.next().toString();
        totalContact.put("email", email);

        System.out.println();
        return totalContact;
    }
    public void display(Hashtable<String,String> totalContact) {
        System.out.println("First Name: " +totalContact.get("first_name"));
        System.out.println("Last Name: " +totalContact.get("last_name"));
        System.out.println("Address: " +totalContact.get("address"));
        System.out.println("City: " +totalContact.get("city"));
        System.out.println("State: " +totalContact.get("state"));
        System.out.println("Zip: " +totalContact.get("zip"));
        System.out.println("Phone number: " +totalContact.get("phone_number"));
        System.out.println("Email: " +totalContact.get("email"));
        System.out.println();
    }
}
public class AddressBookSystem {
     private static final int ADD = 1;
     private static final int QUIT = 2;

     public static void main(String args[]){
         Scanner input = new Scanner(System.in);

         //Option to choose
         int option = menu(input);

         //Add contact detail
         performFunction(option, input);
    }
     public static void performFunction(int option, Scanner input) {
        Hashtable<String, String> totalContact = new Hashtable();

        //Create object and pass the values
        Contact_Person contact = new Contact_Person();

         switch(option) {
             case ADD:
                 System.out.println("Enter the number of contacts you want to add: ");
                 int no_of_contacts = input.nextInt();
                 for(int i = 0; i < no_of_contacts; i++) {
                     //Insert contact details
                     contact.insertContactDetails(input, totalContact);
                     //Display the values
                     contact.display(totalContact);
                 }
                break;
             case QUIT:
                 System.out.println("Thank you for referring the address book.");
                 break;
             default:
                 System.out.println("Wrong Option! Please check for the option correctly!");
                 break;
         }
    }
    public static int menu(Scanner input) {
        System.out.println("Select option: ");
        System.out.println("1. Add a new contact to your address book.");
        System.out.println("2. Quit.");
        int option = input.nextInt();

        return option;
    }
}
