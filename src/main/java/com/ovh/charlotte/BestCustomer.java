package com.ovh.charlotte;

public class BestCustomer
{

    // Attributes
    private String nichandle;
    private String name;
    private String firstName;
    private String ref;
    private Double transaction;

    // Constructor
    BestCustomer(String nichandle, String name, String firstName, String ref, Double transaction)
    {
        this.nichandle = nichandle;
        this.name = name;
        this.firstName = firstName;
        this.ref = ref;
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

    public String getRef() {
        return ref;
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

    public void setRef(String ref) {
        this.ref = ref;
    }

    public void setTransaction(Double transaction)
    {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "BestCustomer{" +
                "nichandle='" + nichandle + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", ref='" + ref + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
