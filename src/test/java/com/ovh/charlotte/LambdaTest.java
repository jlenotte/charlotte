package com.ovh.charlotte;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaTest
{

    Logger LOGGER = LoggerFactory.getLogger("My Log");

    @Test
    public void splitTest()
    {
        String s = "Nazzaro,Eli,Montant : 487659.5646186366,Date : 2008-1-5 17:28:42:943 TIMEZONE : UTC";
        String[] xxx = s.split(",");

        for (String aXxx : xxx)
        {
            System.out.println(aXxx);
        }
    }

    @Test
    public void filterTest() throws IOException
    {
        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        List<Invoice> result = DataSource.list.stream()
            .filter(client -> client.getDate().getYear() == 2013)
            .filter(client -> client.getName().equals("Patel"))
            .filter(client -> client.getFirstName().equals("Carlo"))
            .collect(Collectors.toList());

        // Afficher
        System.out.println("\n ///// FILTRAGE DES OPERATIONS DE Patel Carlo EN 2013 /////");
        for (Invoice cc : result)
        {
            System.out.println(cc);
        }
    }

    @Test
    public void aggregationTest() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sum = DataSource.list.stream()
            .filter(client -> client.getDate().getYear() == 2013)
            .filter(client -> client.getName().equals("Patel"))
            .filter(client -> client.getFirstName().equals("Will"))
            .mapToDouble(Invoice::getTransaction)
            .sum();
        assertTrue(!DataSource.list.isEmpty());

        // Affichage
        if (sum == 0)
        {
            //LOGGER.
        }
        else
        {

        }
        System.out.println("\n ///// AGGREGATION SOMME DES MONTANTS DE PATEL WILL /////");
        System.out.println("Sum of all transactions from 'Patel Will' : " + sum);
    }

    @Test
    public void reduceTest() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sumReduce = DataSource.list.stream()
            .mapToDouble(Invoice::getTransaction)
            .reduce(0,
                (a, b) -> a + b);

        // Affichage
        System.out.println("\n ///// REDUCE DES MONTANTS TRANSACTIONS /////");
        System.out.println("reduce : " + sumReduce);
    }

    @Test
    public void groupingTest() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        Map<ZonedDateTime, List<Invoice>> sumAll = DataSource.list.stream()
            .filter(client -> client.getDate().getYear() == 2016)
            .filter(client -> client.getDate().getMonthValue() == 7)
            .filter(client -> client.getName().equals("Natali"))
            .filter(client -> client.getFirstName().equals("Virginia"))
            .distinct()
            .collect(Collectors.groupingBy(Invoice::getDate));

        // Affichage
        System.out.println("\n ///// GROUPBY /////");
        for (Entry<ZonedDateTime, List<Invoice>> ccc : sumAll.entrySet())
        {
            System.out.println(ccc);
        }
        System.out.println("\n ///// GROUPBY /////");
        System.out.println(sumAll);
    }

    @Test
    public void getMonthlySum() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sum = DataSource.list.parallelStream()
            .filter(client -> client.getDate().getYear() == 2010)
            .filter(client -> client.getDate().getMonth() == Month.OCTOBER)
            .filter(client -> client.getName().equals("Natali"))
            .filter(client -> client.getFirstName().equals("Virginia"))
            .mapToDouble(Invoice::getTransaction)
            .sum();

        System.out.println("\n" + "TOTAL TRANSACTIONS DE 'VITAL ODA' POUR JUIN 2013 : " + sum);
    }

    @Test
    public void topNElementsTest() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        List<Double> toplist = new ArrayList<>();
        double transaction;

        for (Invoice c : DataSource.list)
        {
            transaction = c.getTransaction();
            toplist.add(transaction);
        }

        toplist.sort(Collections.reverseOrder());

        // Affichage
        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS /////");
        List<Double> tophundred = new ArrayList<>(toplist.subList(0, 99));
        tophundred.sort(Collections.reverseOrder());
        System.out.println(tophundred);
    }

    @Test
    public void getTopCustomers() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        final int[] i = {1};
        System.out.println("\n ///// TOP 1000 CLIENTS ///// \n");
        Comparator<Invoice> byCost = Comparator.comparingDouble(Invoice::getTransaction);
        DataSource.list.parallelStream()
            .sorted(byCost.reversed())
            .limit(1000)
            .forEach(client -> System.out.println("[" + (i[0]++) + "] " + client));
    }

    @Test
    public void multiCriteriaComparator() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        Comparator<Invoice> byMontant = Comparator.comparing(Invoice::getTransaction);
        Comparator<Invoice> byName = Comparator.comparing(Invoice::getName);

        DataSource.list.parallelStream()
            .sorted(byMontant.thenComparing(byName))
//            .map(Client::getDate)
            .forEach(System.out::println);
    }

    @Test
    public void flatMapTest() throws IOException
    {

        DataSource.readFile();

        //------------------------------------------------------------------------------------------//
        // TRAITEMENT

        List<String> flatList = DataSource.list.parallelStream()
            .map(c -> c.getName())
            .distinct()
            .collect(Collectors.toList());

        flatList.forEach(c -> System.out.println(c));
    }

    @Test
    public void flinkStreamingJobs()
    {
        //String outputPath = "output.csv";
        //ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //DataSet<Client> result = env.readCsvFile("dataBase.cs");
        //DataSet<Client> counts = text.filter()
        //counts.writeAsCsv(outputPath, "\n", " ");
    }
}
