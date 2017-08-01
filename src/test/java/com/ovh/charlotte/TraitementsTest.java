package com.ovh.charlotte;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.fail;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TraitementsTest
{

    final static Logger LOGGER = LoggerFactory.getLogger(TraitementsTest.class);

    @Test
    public void robustnessTest1()
    {
        try
        {
            List<Invoice> result = new ArrayList<>(null);
            Traitements t = new Traitements();
            t.getBestCustomer(result);
            assertNotNull(result);
        }
        catch (Exception e)
        {
            fail("A thousand kittens have just died because of your lame code.");
        }
    }

    @Test
    public void getBestCustomerTest()
    {
        try
        {
            // Init
            DataSource ds = new DataSource();
            Traitements t = new Traitements();

            // ***********THE RESULT COMES FROM THE PROCESS**********
            String file = "dataBase.csv";
            List<Invoice> actual = new ArrayList<>();

            // date
            ZonedDateTime date = ZonedDateTime.parse("2006-11-15T19:44:33.312Z[UTC]");

            // Initialize an invoice
            Invoice inv = new Invoice("mfdmqsf", "Lenotte", "Jules", 500.00, date);
            actual.add(inv);
            List<Invoice> expected = t.getBestCustomer(actual);

            // Assert that expected != null
            assertNotNull(expected);

            //
        }
        catch (Exception e)
        {
            fail("A thousand kittens have just died because of your lame code.");
            LOGGER.debug(e.getMessage());
        }
    }
}
