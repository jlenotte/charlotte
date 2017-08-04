package com.ovh.charlotte;

public class BestCustomer
{

    // Attributes
    private String nichandle;
    private String name;
    private String firstName;
    private Double transaction;

    // Constructor
    BestCustomer()
    {
        this.nichandle = null;
        this.name = null;
        this.firstName = null;
        this.transaction = null;
    }

    BestCustomer(String nichandle, String name, String firstName, Double transaction)
    {
        this.nichandle = nichandle;
        this.name = name;
        this.firstName = firstName;
        this.transaction = transaction;
    }

    // Getters / Setters
    public String getNichandle()
    {
        return nichandle;
    }

    public String getName()
    {
        return name;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public Double getTransaction()
    {
        return transaction;
    }

    public void setNichandle(String nichandle)
    {
        this.nichandle = nichandle;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setTransaction(Double transaction)
    {
        this.transaction = transaction;
    }
}
