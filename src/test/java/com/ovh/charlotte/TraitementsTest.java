package com.ovh.charlotte;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
            Invoice inv = new Invoice("lj123123-ovh", "Lenotte", "Jules", 320.00, date);

            // Test that the text format is right

//            actual.add(inv);
            List<Invoice> expected = t.getTotalPerYearPerCustomer(actual);

            assertNotNull(actual);
            assertTrue(actual != null);
            assertFalse(expected.contains(null));
        }
        catch (Exception e)
        {
            fail("A thousand kittens have just died because of your lame code !");
            LOGGER.debug(e.getMessage());
        }
    }
}
