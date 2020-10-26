package com.readingapp.model;

import java.util.Objects;

public class Contact {

    private int type;
    private String contact;

    public Contact(int type, String contact) {

        this.type = type;
        this.contact = contact;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type=" + type +
                ", contact='" + contact + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact1 = (Contact) o;
        return type == contact1.type &&
                contact.equals(contact1.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, contact);
    }
}
