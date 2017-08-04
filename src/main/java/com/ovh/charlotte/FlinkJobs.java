package com.ovh.charlotte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FlinkJobs
{

    private final static Logger LOGGER = LoggerFactory.getLogger(FlinkJobs.class);

    /**
     * Return a list of the top N clients
     *
     * @return returnedList
     */
    DataSet<String> getBestCustomerFlink(DataSet<String> data, int amount) throws IOException
    {

        // Sort the list and limit 100
        data
            .sortPartition(1, Order.DESCENDING)
            .first(amount);

        ////LOGGER.info(String.valueOf(returnedList));
        LOGGER.debug("Query OK.");
        // LOGGER.debug("Report generated.");
        return data;
    }

    /**
     * Display the list with flink
     */
    static void displayContent(DataSet<String> text)
    {
        text
            .map((MapFunction<String, Invoice>) s -> {

                String[] splitter = s.split(",");

                // parse string to double
                double value = Double.parseDouble(splitter[3]);

                // parse string to zdate
                ZonedDateTime date = ZonedDateTime.parse(splitter[4]);

                // assign values to object params
                Invoice inv = new Invoice(splitter[0], splitter[1], splitter[2], value, date);

                return inv;
            })
            .map(new MapFunction<Invoice, String>()
            {
                @Override
                public String map(Invoice invoice) throws Exception
                {
                    return "xxxxxx" + invoice.toString();
                }
            }).writeAsText("/tmp/fffuuuuuu"); // once the collect is done, the process stops
    }

    /**
     * Get the total of all transactions per month
     *
     * @return List of doubles
     */
    double getTotalPerMonthFlink(List<Invoice> list) throws IOException
    {
        // Init Variables
        HashMap<String, Double> map = new HashMap<>();

        // Check if the list is empty and log
        if (list.isEmpty())
        {
            LOGGER.error("The list is empty and results may not be processed properly.");
        }
        else
        {
            LOGGER.debug("List values detected, proceeding...");
        }

        // For each client of the list
        for (Invoice client : list)
        {
            // Init variables
            String year = String.valueOf(client.getDate().getYear());
            String month = String.valueOf(client.getDate().getMonthValue());

            // Assign variables to <K, V>
            String key = year + "/" + month;
            double value = client.getTransaction();

            if (map.containsKey(key))
            {
                // if a key already exists
                // the value will be added to the already existing one to avoid overwritting
                value += map.get(key);
                map.put(key, value);
            }
            else
            {
                // otherwise, we simply add a new key/value entry
                map.put(key, value);
            }
        }

        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        // Loop through the sorted map to display results
        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            ////LOGGER.info("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        // Total revenue since 2000
        // Sum up all the values into a variable
        double monthComparison = sortedValues
            .parallelStream()
            .mapToDouble(aDouble -> aDouble)
            .sum();

        if (monthComparison == 0)
        {
            LOGGER.error("No matches found.");
        }
        else
        {
            LOGGER.debug("Query successful for method getTotalPerMonth().");
            ////LOGGER.info("Total des recettes depuis 2000/12 : " + monthComparison);
        }
        return monthComparison;
    }


    /**
     * Get top 10 months ever made
     */
    List<Double> getTopTenMonthsFlink(List<Invoice> list) throws IOException
    {
        LOGGER.debug("Now processing file to get the best 10 months...");

        // Init variables
        HashMap<String, Double> map = new HashMap<>();

        // Check if the list is empty and log
        if (list.isEmpty())
        {
            LOGGER.error("The list is empty and results may not be processed properly.");
        }
        else
        {
            LOGGER.debug("List values detected, proceeding...");
        }

        // For each client of the list
        for (Invoice client : list)
        {
            // Init variables
            String year = String.valueOf(client.getDate().getYear());
            String month = String.valueOf(client.getDate().getMonthValue());

            // Assign variables to <K, V>
            String key = year + "/" + month;
            double value = client.getTransaction();

            if (map.containsKey(key))
            {
                // if a key already exists
                // the value will be added to the already existing one to avoid overwritting
                value += map.get(key);
                map.put(key, value);
            }
            else
            {
                // otherwise, we simply add a new key/value entry
                map.put(key, value);
            }
        }

        List<Double> topTenMonths = new ArrayList<>(map.values());

        try
        {
            topTenMonths.sort(Collections.reverseOrder());
            topTenMonths.subList(0, 10);
        }
        catch (Exception e)
        {
            LOGGER.error(
                "Error, input list may be empty and you may get an IndexOutOfBound exception.");
            LOGGER.error("Exception message : " + e.getMessage());
        }

        if (topTenMonths.isEmpty())
        {
            LOGGER.error("The file may not have been processed correctly.");
        }
        else
        {
            LOGGER.debug("Top ten months successfully processed.");
        }

        // Report
        String filePath = "TOP_TEN_MONTHS_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        //LOGGER.info("Top ten months result : " + topTenMonths);
        LOGGER.debug("Generating report...");
        for (Double reportList : topTenMonths)
        {
            bw.write(String.valueOf(reportList));
            bw.newLine();
            bw.flush();
        }

        LOGGER.debug("Report generated.");
        return topTenMonths;
    }


    /**
     * Get the total of a customer per year
     *
     * @param list Input a List<> as a parameter
     * @return returns a List<> of Invoices
     * @throws IOException IOException
     */
    List<Invoice> getTotalPerYearPerCustomerFlink(List<Invoice> list) throws IOException
    {
        // Stream & filter the list into a sum
        double sum = list.parallelStream()
            .filter(client -> client.getDate().getYear() == 2012)
            .filter(client -> client.getName().equals("Vital"))
            .filter(client -> client.getFirstName().equals("Virginia"))
            .mapToDouble(Invoice::getTransaction)
            .sum();

        // Check
        if (sum == 0)
        {
            LOGGER.error("No matches found.");
        }
        else
        {
            //LOGGER.info("Total transactions for selected client : " + sum);
            LOGGER.debug("Query successfully executed for : getTotalPerYearPerCustomer().");
        }

        // Generate report
        // Init
        String filePath = "CUSTOMER_YEARLY_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        try
        {
            LOGGER.debug("Generating report...");
            bw.write(String.valueOf(sum));
            bw.newLine();
            bw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            LOGGER.error(e.getLocalizedMessage());
        }

        // Return
        LOGGER.debug("Report generated.");
        return list;
    }


    /**
     * Get the total revenue of each year since 2000
     *
     * @param hm HashMap<>
     * @return HashMap<>
     * @throws IOException IOException
     */
    HashMap<String, Double> getTotalPerYearFlink(List<Invoice> list, HashMap<String, Double> hm)
        throws IOException
    {
        // Init variables
        HashMap<String, Double> map = new HashMap<>();

        // Check if the list is empty and log
        if (list.isEmpty())
        {
            LOGGER.error("The list is empty and results will not be processed properly.");
        }
        else
        {
            LOGGER.debug("List values detected, proceeding...");
        }

        // For each client of the list, add keys and values to a Map
        LOGGER.debug("Now reading CSV to map entries into HashMap...");
        for (Invoice client : list)
        {
            // Add year & month as Key
            String year = String.valueOf(client.getDate().getYear());
            double value = client.getTransaction();

            if (map.containsKey(year))
            {
                // if a key already exists
                // the value will be added to the already existing one to avoid overwritting
                value += map.get(year);
                map.put(year, value);
            }
            else
            {
                // otherwise, we simply add a new key/value entry
                map.put(year, value);
            }
        }

        // Affichage
        // Map to TreeMap for sorting
        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        if (sortedMap.values().isEmpty())
        {
            LOGGER.error("Warning : empty values detected.");
        }

        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            //LOGGER.info("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        LOGGER.debug("Query successfully executed for : getTotalPerYear().");

        // Generate report
        // Init writing variables
        String filePath = "YEARLY_TOTAL_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write
        LOGGER.debug("Generating report...");
        try
        {
            for (Double reportList : sortedValues)
            {
                bw.write(String.valueOf(reportList));
                bw.newLine();
                bw.flush();
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
            LOGGER.error("Report was not generated properly.");
        }

        LOGGER.debug("Report generated.");
        return hm;
    }
}
