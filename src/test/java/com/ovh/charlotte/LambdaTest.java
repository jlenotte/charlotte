package com.ovh.charlotte;

import static org.junit.Assert.assertTrue;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class LambdaTest
{

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
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        List<Client> result = list.stream()
            .filter(client -> client.getDate().getYear() == 2013)
            .filter(client -> client.getNom().equals("Patel"))
            .filter(client -> client.getPrenom().equals("Carlo"))
            .collect(Collectors.toList());

        // Afficher
        System.out.println("\n ///// FILTRAGE DES OPERATIONS DE Patel Carlo EN 2013 /////");
        for (Client cc : result)
        {
            System.out.println(cc);
        }
    }

    @Test
    public void aggregationTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sum = list.stream()
            .filter(client -> client.getDate().getYear() == 2012)
            .filter(client -> client.getNom().equals("Patel"))
            .filter(client -> client.getPrenom().equals("Will"))
            .mapToDouble(Client::getMontant)
            .sum();
        assertTrue(!list.isEmpty());

        // Affichage
        System.out.println("\n ///// AGGREGATION SOMME DES MONTANTS DE PATEL WILL /////");
        System.out.println("Sum of all transactions from 'Patel Will' : " + sum);
    }

    @Test
    public void reduceTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        double sumReduce = list.stream()
            .mapToDouble(Client::getMontant)
            .reduce(0,
                (a, b) -> a + b);

        // Affichage
        System.out.println("\n ///// REDUCE DES MONTANTS TRANSACTIONS /////");
        System.out.println("reduce : " + sumReduce);
    }

    @Test
    public void groupingTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        Map<String, List<Client>> sumAll = list.stream()
            .collect(Collectors.groupingBy(Client::getNom));

        // Affichage
        System.out.println("\n ///// GROUPBY : NOMS DE FAMILLE /////");
        for (Map.Entry<String, List<Client>> ccc : sumAll.entrySet())
        {
            System.out.println(ccc);
        }
    }

    @Test
    public void topNElementsTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        List<Double> toplist = new ArrayList<>();
        double montant;

        for (Client c : list)
        {
            montant = c.getMontant();
            toplist.add(montant);
        }
        Collections.sort(toplist, Collections.reverseOrder());

        // Affichage
        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS /////");
        List<Double> tophundred = new ArrayList<>(toplist.subList(0, 99));
        tophundred.sort(Collections.reverseOrder());
        System.out.println(tophundred);
    }

    @Test
    public void topNElementsLambdaTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        final int[] i = {1};
        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS LAMBDA /////");
        Comparator<Client> byCost = Comparator.comparingDouble(Client::getMontant);
        list.stream()
            .sorted(byCost.reversed())
            .limit(100)
            .forEach(client -> System.out.println("[" + (i[0]++) + "] " + client));
    }

    @Test
    public void multiCriteresComparatorTest() throws IOException
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER

        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

        Comparator<Client> byMontant = Comparator.comparing(Client::getMontant);
        Comparator<Client> byName = Comparator.comparing(Client::getNom);

        list.stream()
            .sorted(byMontant.thenComparing(byName))
            .forEach(System.out::println);
    }

    @Test
    public void yyyyMmGlobalTest() throws IOException
    {

    }

    @Test
    public void yyyyMmNomTest() throws IOException
    {

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
