package com.readingapp;

import com.readingapp.database.Datasource;
import com.readingapp.reader.ReaderFactory;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = new Datasource();
        ReaderFactory readerFactory = new ReaderFactory();

        ReaderApp readerApp = new ReaderApp(datasource, readerFactory);

        readerApp.runApp();


    }

}
