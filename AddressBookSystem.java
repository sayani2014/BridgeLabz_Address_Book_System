/**
 * Ability to create Contacts in Address Book with first and last names, address, city, state, zip, phone number and email.
 * Creation of constructor to bind the values in an object.
 *
 * @author: SAYANI KOLEY
 * @since: 15/06/2021
 */

public class AddressBookSystem {
    String first_name;
    String last_name;
    String address;
    String city;
    String state;
    int zip;
    String phone_number;
    String email;

    //Create a parameterized constructor
    AddressBookSystem(String first_name, String last_name, String address, String city, String state, int zip, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone_number = phone_number;
        this.email = email;
    }

    public static void main(String args[]){

        //Create object and pass the values
        AddressBookSystem obj = new AddressBookSystem("SAYANI","KOLEY","Howrah", "Howrah", "West Bengal", 711101, "7908904096", "sayanikoley8@gmail.com");

        //Display the values of object
        obj.display();
    }

    public void display() {
        System.out.println("First Name: " +first_name);
        System.out.println("Last Name: " +last_name);
        System.out.println("Address: " +address);
        System.out.println("City: " +city);
        System.out.println("State: " +state);
        System.out.println("Zip: " +zip);
        System.out.println("Phone number: " +phone_number);
        System.out.println("Email: " +email);
    }
}
