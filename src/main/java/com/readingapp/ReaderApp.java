package com.readingapp;

import com.readingapp.database.Datasource;
import com.readingapp.model.Customer;
import com.readingapp.reader.Reader;
import com.readingapp.reader.ReaderFactory;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ReaderApp {

    Datasource datasource;
    ReaderFactory readerFactory;
    private String datasourceUrl;
    private String dataSourceUserName;
    private String dataSourcePassword;

    private String fileType;
    private String filePath;

    public ReaderApp(Datasource datasource, ReaderFactory readerFactory) {
        this.readerFactory = readerFactory;
        this.datasource = datasource;
    }

    private void transferFileToDb() throws XMLStreamException, SQLException, FileNotFoundException {


        datasource.open(datasourceUrl, dataSourceUserName, dataSourcePassword);

        Reader reader = readerFactory.createReader(fileType, filePath);

        doReadAndSave(reader);

        datasource.close();
    }


    private void doReadAndSave(Reader reader) throws XMLStreamException, SQLException {

        int readCounter = 0;
        int insertCounter = 0;

        Optional<Customer> optionalCustomer = reader.readPerson();

        while (optionalCustomer.isPresent()) {

            datasource.insertIntoCustomer(optionalCustomer.get());
            readCounter++;

            optionalCustomer = reader.readPerson();
            insertCounter++;

        }

        System.out.println("READED: " + readCounter + " INSERTED: " + insertCounter);

    }


    private void loadProperties() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/config.properties"));


        datasourceUrl = properties.getProperty("datasource-url");
        dataSourceUserName = properties.getProperty("datasource-username");
        dataSourcePassword = properties.getProperty("datasource-password");

        fileType = properties.getProperty("file-path");
        fileType = fileType.substring(fileType.length() - 3);

        filePath = properties.getProperty("file-path");

    }

    public void runApp() {

        try {
            loadProperties();
            transferFileToDb();

        } catch (FileNotFoundException e) {
            System.out.println("File not found exception: " + e.getMessage());
        } catch (XMLStreamException e) {
            System.out.println("XML Stream Exception: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Exception occurred: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
        }
    }

}
