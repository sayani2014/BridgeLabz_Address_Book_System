package AddressBookService;

import AddressBookModel.PersonInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;
    private AddressBookDBService() {
    }

    /**
     * Purpose : For creating a singleton object
     *
     * @return
     */

    public static AddressBookDBService getInstance() {
        if ( addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    /**
     * Purpose : Create connection with the database
     *
     * @return
     * @throws SQLException
     */

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookdb?useSSL=false";
        String userName = "root";
        String password = "root";
        Connection connection;
        System.out.println("Connecting to database: "+jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful! " +connection);
        return connection;
    }

    /**
     * Purpose : Create connection to execute query and read the value from the database
     *           Assign the value in a list variable
     *
     * @param sql
     * @return
     */

    private List<PersonInfo> getEmployeePayrollDataUsingDB(String sql) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("A_ID");
                String fname = result.getString("FirstName");
                String lname = result.getString("LastName");
                String address = result.getString("Address");
                String city = result.getString("City");
                String state = result.getString("State");
                int zip = result.getInt("Zip");
                String phoneNo = result.getString("PhoneNumber");
                String email = result.getString("Email");
                personInfoList.add(new PersonInfo(id, fname, lname, address, city, state, zip, phoneNo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

    /**
     * Purpose : Read the person info from the database
     *
     * @return
     */

    public List<PersonInfo> readData() {
        String sql = "SELECT * FROM address_book_records";
        return getEmployeePayrollDataUsingDB(sql);
    }
}
