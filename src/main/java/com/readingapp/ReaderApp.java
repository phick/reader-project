package com.readingapp;

import com.readingapp.database.Datasource;
import com.readingapp.model.Customer;
import com.readingapp.reader.Reader;
import com.readingapp.reader.ReaderFactory;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ReaderApp {

    Datasource datasource;
    private String datasourceUrl;
    private String dataSourceUserName;
    private String dataSourcePassword;

    private String fileType;
    private String filePath;

    Reader reader;

    public ReaderApp() {
        this.datasource = new Datasource();
    }

    private void transferFileToDb() throws XMLStreamException, SQLException {

        datasource.open(datasourceUrl, dataSourceUserName, dataSourcePassword);


        ReaderFactory readerFactory = new ReaderFactory();

        reader = readerFactory.createReader(fileType, filePath);
        doReadAndSave();

        datasource.close();
    }


    private void doReadAndSave() throws XMLStreamException, SQLException {

        Customer customer;
        customer = reader.readPerson();


        while (customer != null) {

            System.out.println("SAVING CUSTOMER:" + customer);
            datasource.insertIntoCustomer(customer);
            customer = reader.readPerson();
        }
    }


    private void loadProperties() throws IOException {

        Properties properties = new Properties();

        FileInputStream ip;

        ip = new FileInputStream("src/main/resources/config.properties");
        properties.load(ip);

        datasourceUrl = properties.getProperty("datasource-url");
        dataSourceUserName = properties.getProperty("datasource-username");
        dataSourcePassword = properties.getProperty("datasource-password");
        fileType = properties.getProperty("file-type");
        filePath = properties.getProperty("file-path");

    }

    public void runApp() {

        try {
            loadProperties();
            transferFileToDb();

        } catch (FileNotFoundException e) {
            System.out.println("File not found exception" + e.getMessage());
        } catch (XMLStreamException e) {
            System.out.println("XML Stream Exception" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception occurred" + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred" + e.getMessage());
        }
    }

}
