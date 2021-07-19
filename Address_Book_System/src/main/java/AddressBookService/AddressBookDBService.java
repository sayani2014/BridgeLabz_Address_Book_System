package AddressBookService;

import AddressBookModel.PersonInfo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;
    private PreparedStatement personInfoDataStatement;

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

    private List<PersonInfo> getPersonInfoDataUsingDB(String sql) {
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
        return getPersonInfoDataUsingDB(sql);
    }

    /**
     * Purpose : Update the state in the DB using Statement Interface
     *
     * @param name
     * @param state
     * @return
     */

    public int updatePersonInfo(String name, String state) {
        return this.updatePersonInfoUsingStatement(name,state);
    }

    /**
     * Purpose : Update the state in the DB using Statement Interface
     *
     * @param name
     * @param state
     * @return
     */

    private int updatePersonInfoUsingStatement(String name, String state) {
        String sql = String.format("UPDATE address_book_records SET State = '%s' WHERE FirstName = '%s';", state, name);
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Purpose : Get the list of PersonInfoData using the assigned name
     *           setString() is used to set the assigned name value in the sql query
     *           Return all the attribute values listed for a particular name
     *
     * @param name
     * @return
     */

    public List<PersonInfo> getPersonInfoData(String name) {
        List<PersonInfo> personInfoList = null;
        if(this.personInfoDataStatement == null)
            this.preparedStatementForPersonInfo();
        try {
            personInfoDataStatement.setString(1, name);
            ResultSet resultSet = personInfoDataStatement.executeQuery();
            personInfoList = this.getPersonInfoData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

    /**
     * Purpose : Assign the value of the attributes in a list and return it
     *
     * @param resultSet
     * @return
     */

    private List<PersonInfo> getPersonInfoData(ResultSet resultSet) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("A_ID");
                String fname = resultSet.getString("FirstName");
                String lname = resultSet.getString("LastName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                int zip = resultSet.getInt("Zip");
                String phoneNo = resultSet.getString("PhoneNumber");
                String email = resultSet.getString("Email");
                personInfoList.add(new PersonInfo(id, fname, lname, address, city, state, zip, phoneNo, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

    /**
     * Purpose : To get the details of a particular person from the DB using PreparedStatement Interface
     */

    private void preparedStatementForPersonInfo() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM address_book_records WHERE FirstName = ?";
            personInfoDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Read the data for a certain date range from the database
     *
     * @param startDate
     * @param endDate
     * @return
     */

    public List<PersonInfo> getPersonInfoData(LocalDate startDate, LocalDate endDate) {
        String sql = String.format("SELECT * FROM address_book_records WHERE Date__Added BETWEEN '%s' AND '%s';",
                Date.valueOf(startDate), Date.valueOf(endDate));
        return getPersonInfoDataUsingDB(sql);
    }

    /**
     * Purpose : Read the data for a particular state from the database
     * @param state
     * @return
     */

    public List<PersonInfo> getPersonInfoDataProvidedState(String state) {
        String sql = String.format("SELECT * FROM address_book_records WHERE State = '%s';", state);
        return getPersonInfoDataUsingDB(sql);
    }

    /**
     * Purpose : Read the data for a particular city from the database
     *
     * @param city
     * @return
     */

    public List<PersonInfo> getPersonInfoDataProvidedCity(String city) {
        String sql = String.format("SELECT * FROM address_book_records WHERE City = '%s';", city);
        return getPersonInfoDataUsingDB(sql);
    }
}
