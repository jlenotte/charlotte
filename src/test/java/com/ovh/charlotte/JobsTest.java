package com.ovh.charlotte;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobsTest
{

    final static Logger LOGGER = LoggerFactory.getLogger(JobsTest.class);

    @Test
    public void getBestCustomerTopOneTest()
    {
        try
        {
            // Init
            Jobs t = new Jobs();

            // ***********THE RESULT COMES FROM THE PROCESS**********
            List<Invoice> actual = new ArrayList<>();

            // date
            ZonedDateTime date = ZonedDateTime.parse("2006-11-15T19:44:33.312Z[UTC]");

            // Initialize an invoice
            Invoice inv = new Invoice("lj123123-ovh", "Lenotte", "Jules", 320.00, date);

            // Test that the text format is right

            // Test that the list is correctly initialized

            actual.add(inv);
            List<Invoice> expected = t.getBestCustomer(actual, 1);

            // Test that the list is not null
            assertNotNull(expected);

            // Test that if input is empty, then output must be empty as well
            assertEquals(actual.isEmpty(), expected.isEmpty());

            // Test that list size is the same
            assertEquals(actual.size(), expected.size());

        }
        catch (Exception e)
        {
            fail("A thousand kittens have just died because of your lame code !");
            LOGGER.debug(e.getMessage());
        }
    }

    @Test
    public void getBestCustomerTopThreeTest()
    {
        try
        {
            // Init
            Jobs t = new Jobs();
            int amount = 3;

            // ***********THE RESULT COMES FROM THE PROCESS**********
            List<Invoice> actual = new ArrayList<>();

            // date
            ZonedDateTime date = ZonedDateTime.parse("2006-11-15T19:44:33.312Z[UTC]");

            // Initialize an invoice
            Invoice inv = new Invoice("lj123123-ovh", "Lenotte", "Jules", 320.00, date);
            Invoice inv2 = new Invoice("aj124124-ovh", "Archibald", "Jean-Celestin", 200.00, date);

            actual.add(inv);
            actual.add(inv);
            actual.add(inv);
            actual.add(inv2);

            List<Invoice> expected = t.getBestCustomer(actual, amount);

            // Test that the output "N" is equal to the amount of top "N"
            assertEquals(expected.size(), amount);

            // Test that the list is not null
            assertNotNull(expected);

            // Test that if input is empty, then output must be empty as well
            assertEquals(actual.isEmpty(), expected.isEmpty());
        }
        catch (Exception e)
        {
            fail("A thousand kittens have just died because of your lame code !");
            LOGGER.error(e.getMessage());
        }
    }
}
