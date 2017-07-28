package com.ovh.charlotte;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class Traitements
{

    private final static java.util.logging.Logger LOGGER = Logger
        .getLogger(Traitements.class.getName());
    org.slf4j.Logger logger = LoggerFactory.getLogger("My logback");

    @Test
    public void getBestCustomer()
    {
        logger.debug("This is a logback entry.");
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        DataSource.readFile();

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        final int[] i = {1};
        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS /////");
        Comparator<Invoice> byCost = Comparator.comparingDouble(Invoice::getTransaction);
        DataSource.list.stream()
            .sorted(byCost.reversed())
            .limit(100)
            .forEach(client -> System.out.println("[" + (i[0]++) + "] " + client));
        LOGGER.setLevel(Level.INFO);
        LOGGER.log(Level.INFO, "Query successfully executed.");
    }

    @Test
    public void getMonthTotals()
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        HashMap<String, Double> map = new HashMap<>();

        // For each client of the list
        for (Invoice client : DataSource.list)
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

        //------------------------------------------------------------------------------------------------//
        // Affichage

        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            System.out
                .println("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        sortedValues.sort(Collections.reverseOrder());
        //System.out.println("///// ********************************* /////");
        //System.out.println("Meilleurs mois -> " + sortedValues);

        //------------------------------------------------------------------------------------------------//
        // TOTAL CA OF ALL TIMES

        List<Double> topTenMonths = new ArrayList<>(map.values());

        double monthComparison = sortedValues.parallelStream()
            .mapToDouble(aDouble -> aDouble)
            .sum();

        if (monthComparison == 0)
        {
            LOGGER.log(Level.WARNING, "No matches found.");
        }
        else
        {
            LOGGER.log(Level.INFO, "Query successfully executed.");
            System.out.println("///// ********************************* /////");
            System.out.println("Total des recettes depuis 2000/12 : " + monthComparison);
            System.out.println("///// ********************************* /////");
        }

        //------------------------------------------------------------------------------------------------//
        // GET 10 BEST MONTHS

        Comparator<Double> byCost = Comparator.comparingDouble(aDouble -> aDouble);

        sortedValues.stream()
            .sorted(byCost.reversed())
            .limit(10)
            .forEach(System.out::println);

        topTenMonths.sort(Collections.reverseOrder());
        topTenMonths = topTenMonths.subList(0, 10);
        System.out.println("topTenMonths : " + topTenMonths);

        LOGGER.setLevel(Level.INFO);
        LOGGER.log(Level.INFO, "Query successfully executed.");
    }

    @Test
    public void getCustomerYearlyTotal()
    {
        DataSource.readFile();

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sum = DataSource.list.parallelStream()
            .filter(client -> client.getDate().getYear() == 2001)
            .filter(client -> client.getName().equals("Vital"))
            .filter(client -> client.getFirstName()
                .equals("Noe")) // replace by Siu for working results.
            .mapToDouble(Invoice::getTransaction)
            .sum();

        if (sum == 0)
        {
            LOGGER.log(Level.WARNING, "No matches found.");
        }
        else
        {
            System.out.println("//*******************************************//");
            System.out.println("Total des transactions pour 'Noe Vital' : " + sum);
            LOGGER.setLevel(Level.INFO);
            LOGGER.log(Level.INFO, "Query successfully executed.");
        }
    }

    @Test
    public void getYearTotal()
    {
        DataSource.readFile();

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        HashMap<String, Double> map = new HashMap<>();

        // For each client of the list
        for (Invoice client : DataSource.list)
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

        //------------------------------------------------------------------------------------------------//
        // Affichage

        Map<String, Double> sortedMap = new TreeMap<>(map);
        List<Double> sortedValues = new ArrayList<>();

        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            System.out
                .println("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }

        LOGGER.setLevel(Level.INFO);
        LOGGER.log(Level.INFO, "Query successfully executed.");
    }
}
