package AddressBookIOOperations;

import AddressBookModel.PersonInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class ReadWriteOperations {
    String filePath = "src/AddressBookIOOperations/";

    /**
     * Purpose : Ability to Write the Address Book with Persons Contact into a File using File IO
     *           Create a .txt file based on the CompanyName and insert data specific to the CompanyName
     *
     * @param : personInfoDict
     * @since : 11.07.2021
     */

    public void writeInAddressBook(Hashtable<String, ArrayList<PersonInfo>> personInfoDict) {
        personInfoDict.forEach( (companyName, personInfos) -> {
            String filePathWrite = filePath + companyName + ".txt";
            try {
                Files.write(Paths.get(filePathWrite) , personInfos.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Purpose : Ability to read the data from a .txt file
     *           Ask user for the CompanyName and display the data specific to the CompanyName
     *
     * @param : personInfoDict
     * @since : 11.07.2021
     */

    public void readFromAddressBook() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nReading Data from .txt file");
        System.out.print("Enter the Company Name you want to read the details from : ");
        String companyName = input.next();

        String filePathRead = filePath + companyName +".txt";

        try {
            Files.lines(new File(filePathRead).toPath())
                    .forEach(System.out::println);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
