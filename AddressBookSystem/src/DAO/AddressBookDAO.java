package DAO;

import AddressBookModel.PersonInfo;
import AddressBookService.AddressBookInterface;
import java.util.Hashtable;
import java.util.Scanner;

public class AddressBookDAO implements AddressBookInterface {
    Scanner input = new Scanner(System.in);
    Hashtable<String, PersonInfo> personInfoDict = new Hashtable<>();
    PersonInfo p = null;

    @Override
    public Hashtable<String, PersonInfo> insertContactDetails() {
        p = new PersonInfo();
        System.out.print("Enter the Address Book Name: ");
        String addressBookName = input.next();

        System.out.print("Enter the First Name: ");
        p.setFirst_name(input.next().toString());
        System.out.print("Enter the Last Name: ");
        p.setLast_name(input.next().toString());
        System.out.print("Enter the Address: ");
        p.setAddress(input.next().toString());
        System.out.print("Enter the City: ");
        p.setCity(input.next().toString());
        System.out.print("Enter the State: ");
        p.setState(input.next().toString());
        System.out.print("Enter the Zip: ");
        p.setZip(input.nextInt());
        System.out.print("Enter the Phone Number: ");
        p.setPhone_number(input.next().toString());
        System.out.print("Enter the Email: ");
        p.setEmail(input.next().toString());

        if(!personInfoDict.containsKey(addressBookName))
            personInfoDict.put(addressBookName, p);
        else
            System.out.println("Duplicate Address Book Name");

        return personInfoDict;
    }

    @Override
    public void updateContact(String addressBookName, Hashtable<String, PersonInfo> personInfoDict) {
        boolean flag = findContact(addressBookName, personInfoDict);
        if (flag == true) {
            editContactDetails(addressBookName, personInfoDict);
        } else {
            System.out.println("No such contact found to update");
        }
    }

    @Override
    public boolean findContact(String addressBookName, Hashtable<String, PersonInfo> personInfoDict) {
        for (int i = 0; i < personInfoDict.size(); i++) {
            if (personInfoDict.containsKey(addressBookName))  //.get(i)).getFirst_name().equals(contact))
                return true;
        }
        return false;
    }

    @Override
    public void editContactDetails(String addressBookName, Hashtable<String, PersonInfo> personInfoDict) {
        for(int i=0; i<=personInfoDict.size(); i++) {
            if(personInfoDict.containsKey(addressBookName)) {
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
                        p.setFirst_name(input.next().toString());
                        break;
                    case 2:
                        System.out.println("Enter new Last Name: ");
                        p.setLast_name(input.next().toString());
                        break;
                    case 3:
                        System.out.println("Enter new Address: ");
                        p.setAddress(input.next().toString());
                        break;
                    case 4:
                        System.out.println("Enter new City: ");
                        p.setCity(input.next().toString());
                        break;
                    case 5:
                        System.out.println("Enter new State: ");
                        p.setState(input.next().toString());
                        break;
                    case 6:
                        System.out.println("Enter new Zip: ");
                        p.setZip(input.nextInt());
                        break;
                    case 7:
                        System.out.println("Enter new Phone Number: ");
                        p.setPhone_number(input.next().toString());
                        break;
                    case 8:
                        System.out.println("Enter new Email: ");
                        p.setEmail(input.next().toString());
                        break;
                }
                System.out.println("Updated successfully!");
                break;
            }
            else
                System.out.println("No Address Book Found!");
        }
    }

    @Override
    public void deleteContact(String deletedName, Hashtable<String, PersonInfo> personInfoDict) {
        boolean flag = findContact(deletedName, personInfoDict);

        if (flag == true) {
            for (int i = 0; i < personInfoDict.size(); i++) {
                if(personInfoDict.containsKey(deletedName)) {
                    personInfoDict.remove(deletedName);
                    System.out.println("Contact in Address Book Deleted.");
                }
                else
                    System.out.println("No contacts found in the Address Book.");
            }
        }
        else
            System.out.println("No contacts found in the Address Book.");
    }

    @Override
    public void displayCompanyContacts(Hashtable<String, PersonInfo> personInfoDict) {
        /*System.out.println(personInfoDict.keySet());
        personInfoDict.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + "->" + entry.getValue() + "\n");
        });*/
        personInfoDict.keySet().forEach(entry -> {
            System.out.println(entry + "->" + personInfoDict.get(entry)+ "\n");
        });
    }
}