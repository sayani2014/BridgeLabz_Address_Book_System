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
     * Purpose : Read the person info from the new database
     *
     * @return
     */

    public List<PersonInfo> readDataFromNewDB() {
        String sql = "SELECT person_details.P_ID, first_name, last_name, street_address, city, zip, state, Phone_No, " +
                                "Email_ID FROM person_details JOIN person_phone JOIN person_email ON " +
                                "person_phone.P_ID = person_details.P_ID AND person_email.P_ID = person_details.P_ID;";
        return getPersonInfoDataUsingNewDB(sql);
    }

    /**
     * Purpose : Create connection to execute query and read the value from the new database
     *           Assign the value in a list variable
     *
     * @param sql
     * @return
     */

    private List<PersonInfo> getPersonInfoDataUsingNewDB(String sql) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("P_ID");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String address = resultSet.getString("street_address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                String phone = resultSet.getString("Phone_No");
                String email = resultSet.getString("Email_ID");
                personInfoList.add(new PersonInfo(id, fname, lname, address, city, state, zip, phone,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
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

    /**
     * Purpose : Add new Contact to the Address Book Database
     *           If multiple tables are impacted then ensure the DB Transaction is implemented
     *
     * @param id
     * @param fname
     * @param lname
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param type
     * @param phoneNo
     * @param email
     * @return
     */

    public PersonInfo addPersonInDB(int id, String fname, String lname, String street, String city, String state,
                                    int zip, String type, String phoneNo, String email) {
        int personID = -1;
        PersonInfo personInfoData = null;
        Connection connection = null;

        try {
            connection = this.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO person_details (P_ID, first_name, last_name, street_address, city, "
                                            + "zip, state) VALUES ('%d', '%s', '%s', '%s', '%s', '%d', '%s')",
                                                     id, fname, lname, street, city, zip, state);
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if(rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if(resultSet.next())
                    personID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO person_email (P_ID, Email_ID) VALUES (" +
                    "(SELECT P_ID FROM person_details WHERE first_name = '%s'), '%s')", fname, email);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO person_phone (P_ID, Phone_No) VALUES (" +
                    "(SELECT P_ID FROM person_details WHERE first_name = '%s'), '%s')", fname, phoneNo);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO type (Type) VALUES ('%s')", type);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO person_type (P_ID, T_ID) VALUES " +
                    "((SELECT P_ID FROM person_details WHERE first_name = '%s'), " +
                    "(SELECT T_ID FROM type WHERE Type = '%s'))", fname, type);

            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected == 1)
                personInfoData = new PersonInfo(id, fname, lname, street, city, state, zip, phoneNo, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoData;
    }

    /**
     * Purpose : Get the list of PersonInfoData
     *           setString() is used to set the assigned name value in the sql query
     *           Return all the attribute values listed for a particular name
     *
     * @return
     */

    public List<PersonInfo> getPersonInfoDataFromNewDB(String fname) {
        List<PersonInfo> personInfoList = null;
        if(this.personInfoDataStatement == null)
            this.preparedStatementForPersonInfoFromNewDB(fname);
        try {
            ResultSet resultSet = personInfoDataStatement.executeQuery();
            personInfoList = this.getPersonInfoDataFromNewDB(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

    /**
     * Purpose : To get the details of a particular person from the DB using PreparedStatement Interface
     */

    private void preparedStatementForPersonInfoFromNewDB(String fname) {
        try {
            Connection connection = this.getConnection();
            String sql = String.format("SELECT person_details.P_ID, first_name, last_name, street_address, city, zip, state, " +
                    "Phone_No, Email_ID FROM person_details JOIN person_phone JOIN person_email ON " +
                    "person_phone.P_ID = person_details.P_ID AND person_email.P_ID = person_details.P_ID" +
                    " WHERE first_name = '%s';", fname);
            personInfoDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Assign the value of the attributes in a list and return it
     *
     * @param resultSet
     * @return
     */

    private List<PersonInfo> getPersonInfoDataFromNewDB(ResultSet resultSet) {
        List<PersonInfo> personInfoList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("P_ID");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String address = resultSet.getString("street_address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zip = resultSet.getInt("zip");
                String phone = resultSet.getString("Phone_No");
                String email = resultSet.getString("Email_ID");
                personInfoList.add(new PersonInfo(id, fname, lname, address, city, state, zip, phone,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personInfoList;
    }

}
