package com.ovh.charlotte;

import java.time.ZonedDateTime;

public class Invoice {

    // Attributes
    private String nichandle;
    private String name;
    private String firstName;
    private String productRef;
    private ZonedDateTime date;
    private Double transaction;

    // Constructor
    Invoice() {
        this.name = null;
        this.firstName = null;
        this.nichandle = null;
        this.productRef = null;
        this.transaction = null;
        this.date = null;
    }

    // Constructor
    public Invoice(String nichandle, String name, String firstName, String productRef, Double transaction, ZonedDateTime date) {
        this.nichandle = nichandle;
        this.name = name;
        this.firstName = firstName;
        this.productRef = productRef;
        this.transaction = transaction;
        this.date = date;
    }


    // Getters & Setters

    String getNichandle() {
        return nichandle;
    }

    String getName() {
        return name;
    }

    Double getTransaction() {
        return transaction;
    }

    ZonedDateTime getDate() {
        return date;
    }

    String getFirstName() {
        return firstName;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setNichandle(String nichandle) {
        this.nichandle = nichandle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setTransaction(Double transaction) {
        this.transaction = transaction;
    }

    public String toString() {
        return "[Client]" + "NicHandle: " + nichandle + " Nom: " + name + ", Prenom: " + firstName
                + ", Reference: " + productRef
                + ", Montant: " + transaction
                + ", Date: " + date + "\n";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
