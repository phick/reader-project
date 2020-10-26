package com.readingapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String name;
    private String surname;
    private int age;

    public Customer() {
    }

    public Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

    private List<Contact> contacts;

    public void addContact(Contact contact) {

        if (contacts == null)
            this.contacts = new ArrayList<>();

        contacts.add(contact);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", contacts=" + contacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age &&
                name.equals(customer.name) &&
                surname.equals(customer.surname) &&
                Objects.equals(contacts, customer.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, contacts);
    }
}
