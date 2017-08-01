package com.ovh.charlotte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class Traitements
{

    final static Logger LOGGER = LoggerFactory.getLogger(Traitements.class);

    /**
     * Return a list of the top N clients
     *
     * @return returnedList
     */

    List<Invoice> getBestCustomer(List<Invoice> list) throws IOException
    {
        // Init
        String reportPath = "TOP_CUSTOMER_REPORT.csv";
        File f = new File(reportPath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Init comparator
        Comparator<Invoice> byCost = Comparator.comparingDouble(Invoice::getTransaction);

        // Stream
        List<Invoice> returnedList = list
            .parallelStream()
            .sorted(byCost.reversed())
            .limit(100)
            .collect(Collectors.toList());

        // Check & report
        LOGGER.debug("Generating report...");
        bw.write(returnedList.toString());
        bw.newLine();
        bw.flush();

        LOGGER.debug(String.valueOf(returnedList));
        LOGGER.debug("Query OK.");
        LOGGER.debug("Report generated.");
        return returnedList;
    }

    /**
     * Get the total of all transactions per month
     *
     * @return List of doubles
     */
    List<Double> getMonthTotals(List<Invoice> list) throws IOException
    {

        //----------------------------------------------------------------------------------------//
        // TRAITEMENT

        DataSource ds = new DataSource();
        String file = "dataBase.csv";

        HashMap<String, Double> map = new HashMap<>();
        // For each client of the list
        for (Invoice client : ds.readFile(file))
        {
            // Add year & month as Key
            String year = String.valueOf(client.getDate().getYear());
            String month = String.valueOf(client.getDate().getMonthValue());
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

        //----------------------------------------------------------------------------------------//
        // Affichage

        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            LOGGER.debug("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        //----------------------------------------------------------------------------------------//
        // TOTAL CA OF ALL TIMES

        List<Double> topTenMonths = new ArrayList<>(map.values());

        double monthComparison = sortedValues
            .parallelStream()
            .mapToDouble(aDouble -> aDouble)
            .sum();

        if (monthComparison == 0)
        {
            LOGGER.debug("No matches found.");
        }
        else
        {
            LOGGER.debug("Total des recettes depuis 2000/12 : " + monthComparison);
        }

        //----------------------------------------------------------------------------------------//
        // GET 10 BEST MONTHS

        Comparator<Double> byCost = Comparator.comparingDouble(aDouble -> aDouble);

        sortedValues
            .parallelStream()
            .sorted(byCost.reversed())
            .limit(10);

        topTenMonths.sort(Collections.reverseOrder());
        topTenMonths = topTenMonths.subList(0, 10);
        LOGGER.debug("topTenMonths : " + topTenMonths);
        LOGGER.debug("Query successfully executed for : getMonthTotal().");

        //----------------------------------------------------------------------------------------//
        // Generate report

        // Init
        LOGGER.debug("Generating report...");
        String filePath = "TOP_MONTHS_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write
        for (Double topTenMonth : topTenMonths)
        {
            bw.write(String.valueOf(topTenMonth));
            bw.newLine();
            bw.flush();
        }
        LOGGER.debug("Report generated.");

        // Return
        return topTenMonths;
    }

    List<Invoice> getCustomerYearlyTotal(List<Invoice> l) throws IOException
    {

        //----------------------------------------------------------------------------------------//
        // TRAITEMENT

        // Init
        DataSource ds = new DataSource();
        ds.readFile("dataBase.csv");
        ArrayList<Invoice> list = new ArrayList<Invoice>();

        // Stream
        double sum = l.parallelStream()
            .filter(client -> client.getDate().getYear() == 2012)
            .filter(client -> client.getName().equals("Vital"))
            .filter(client -> client.getFirstName().equals("Virginia"))
            .mapToDouble(Invoice::getTransaction)
            .sum();

        // Check
        if (sum == 0)
        {
            LOGGER.debug("No matches found.");
        }
        else
        {
            LOGGER.debug("Total transactions for selected client : " + sum);
            LOGGER.debug("Query successfully executed for : getCustomerYearlyTotal().");
        }

        //----------------------------------------------------------------------------------------//
        // Generate report

        // Init
        String filePath = "CUSTOMER_YEARLY_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Try to Write
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
        return l;
    }

    HashMap<String, Double> getYearTotal(HashMap<String, Double> hm) throws IOException
    {

        //----------------------------------------------------------------------------------------//
        // TRAITEMENT

        String file = "dataBase.csv";
        DataSource ds = new DataSource();
        ds.readFile(file);
        HashMap<String, Double> map = new HashMap<>();

        // For each client of the list
        for (Invoice client : ds.readFile(file))
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

        //----------------------------------------------------------------------------------------//
        // Affichage

        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            LOGGER.debug("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        LOGGER.debug("Query successfully executed for : getYearTotal().");

        //----------------------------------------------------------------------------------------//
        // Generate report

        // Init
        String filePath = "YEARLY_TOTAL_REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write
        LOGGER.debug("Generating report...");
        for (Double reportList : sortedValues)
        {
            bw.write(String.valueOf(reportList));
            bw.newLine();
            bw.flush();
        }

        LOGGER.debug("Report generated.");
        return hm;
    }
}
