package com.ovh.charlotte;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{

    //Logger
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException
    {
        // Init
        String file = "dataBase.csv";
        DataSource m = new DataSource();
        Traitements t = new Traitements();
        List<Invoice> invoiceData = m.readFile(file);

        // Lists
        HashMap<String, Double> yearTotalMap = new HashMap<>();

        // Method calls
        t.getBestCustomer(invoiceData);
        t.getTotalPerMonth(invoiceData);
        t.getTotalPerYearPerCustomer(invoiceData);
        t.getTotalPerYear(invoiceData, yearTotalMap);
        t.getTopTenMonths(invoiceData);
    }
}
