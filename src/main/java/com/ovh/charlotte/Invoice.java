package com.ovh.charlotte;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Invoice extends ArrayList<Invoice>
{

    // Attributes
    private String nichandle;
    private String name;
    private String firstName;
    private ZonedDateTime date;
    private Double transaction;

    // Constructor

    Invoice()
    {
        this.name = null;
        this.firstName = null;
        this.nichandle = null;
        this.transaction = null;
        this.date = null;
    }

    Invoice(String nichandle, String name, String prename, Double transaction, ZonedDateTime date)
    {
        this.name = name;
        this.firstName = prename;
        this.nichandle = nichandle;
        this.transaction = transaction;
        this.date = date;
    }

    /**
     * Getters & Setters
     */

    String getNichandle()
    {
        return nichandle;
    }

    String getName()
    {
        return name;
    }

    Double getTransaction()
    {
        return transaction;
    }

    ZonedDateTime getDate()
    {
        return date;
    }

    String getFirstName()
    {
        return firstName;
    }

    public String toString()
    {
        return "[Client]" + "NicHandle: " + nichandle + " Nom: " + name + ", Prenom: " + firstName
            + ", Montant: " + transaction
            + ", Date: " + date + "\n";
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        return super.equals(o);
    }
}
