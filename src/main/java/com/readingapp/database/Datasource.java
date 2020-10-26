package com.readingapp.database;

import com.readingapp.model.Contact;
import com.readingapp.model.Customer;

import java.sql.*;
import java.util.List;

public class Datasource {

    private Connection connection;

    public static final String INSERT_CUSTOMER = "INSERT INTO customers ( name, surname, age) VALUES (?,?,?)";
    public static final String INSERT_CONTACT = "INSERT INTO contacts (type, contact, customers_id) VALUES (?,?,?)";


    private PreparedStatement insertIntoCustomer;
    private PreparedStatement insertIntoContact;


    public void open(String url, String username, String password) {

        try {

            connection = DriverManager.getConnection(url, username, password);
            insertIntoCustomer = connection.prepareStatement(INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            insertIntoContact = connection.prepareStatement(INSERT_CONTACT, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            System.out.println("Couldn't connect to db: " + e.getMessage());
        }
    }

    public void insertIntoCustomer(Customer customer) throws SQLException {


        insertIntoCustomer.setString(1, customer.getName());
        insertIntoCustomer.setString(2, customer.getSurname());

        if (customer.getAge() != 0)
            insertIntoCustomer.setInt(3, customer.getAge());
        else
            insertIntoCustomer.setNull(3, java.sql.Types.INTEGER);


        int affectedRow = insertIntoCustomer.executeUpdate();

        if (affectedRow != 1) {
            throw new SQLException("Couldn't insert data into customer");
        }

        ResultSet generatedKeys = insertIntoCustomer.getGeneratedKeys();
        if (generatedKeys.next()) {
            insertIntoContacts(generatedKeys.getInt(1), customer.getContacts());
        }


    }

    private void insertIntoContacts(int id, List<Contact> contacts) throws SQLException {

        for (Contact contact : contacts) {

            insertIntoContact.setInt(1, contact.getType());
            insertIntoContact.setString(2, contact.getContact());
            insertIntoContact.setInt(3, id);

            int affectedRow = insertIntoContact.executeUpdate();
            if (affectedRow != 1) {
                throw new SQLException("Couldn't insert data into contacts");
            }
        }
    }


    public void close() {

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
            if (insertIntoContact != null) {
                insertIntoContact.close();
            }
            if (insertIntoCustomer != null) {
                insertIntoCustomer.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close connection" + e.getMessage());

        }

    }


}
