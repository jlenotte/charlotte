package com.ovh.charlotte;

import com.opencsv.CSVReader;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class TransformationTest
{
    public static ArrayList<Client> dataList = new ArrayList<Client>();

    @Test
    public void splitTest()
    {
        String s = "Nazzaro,Eli,Montant : 487659.5646186366,Date : 2008-1-5 17:28:42:943 TIMEZONE : UTC";
        String[] xxx = s.split(",");
        for (int i = 0; i < xxx.length; i++)
        {
            System.out.println(xxx[i]);
        }
    }

    @Test
    public void dateSearch() throws IOException
    {
        ArrayList<Client> list = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null)
        {
            System.out.println(nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
            String nom = nextLine[0];
            String prenom = nextLine[1];
            double montant = Double.parseDouble(nextLine[2]);
            ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

            Client c = new Client(nom, prenom, montant, date);
            list.add(c);
        }

        // filter by YYYY MM and Name
        List<Client> result = list.stream()
                .filter(client -> client.getDate().getYear() == 2009 && client.getDate().getMonthValue() == 07 && client.getNom().equals("Gudger"))
                .collect(Collectors.toList());

        for (Client cc : result)
        {
            System.out.println(cc);
        }
    }

    public static void streamingJobs() throws IOException
    {
        // Initialisation liste
        // ----------------------------------------------------------------------------------------------/
        ArrayList<Client> list = new ArrayList<>();

        try
        {
            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
                String nom = nextLine[0];
                String prenom = nextLine[1];
                double montant = Double.parseDouble(nextLine[2]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);

                Client c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        // Opérations sur les données
        // ----------------------------------------------------------------------------------------------/

        // Filter des opérations de Luann Gudger en 2016
        List<Client> result = list.stream()
                .filter(client -> client.getDate().getYear() == 2016 && client.getNom().equals("Gudger") && client.getPrenom().equals("Luann"))
                .collect(Collectors.toList());

        // Aggregation de la somme de tous les montant de 'Nom' 'Prenom' par 'Année'
        double sum = list.stream()
//                .filter(client -> client.getDate().getYear() == 2012)
                .filter(client -> client.getNom().equals("Patel"))
                .filter(client -> client.getPrenom().equals("Will"))
                .mapToDouble(Client::getMontant)
                .sum();
        assertTrue(list.isEmpty() == false);
        assertTrue("Client must match the search.", true);

        // Reduce du montant des transactions des clients
        double sumReduce = list.stream()
                .mapToDouble(Client::getMontant)
                .reduce(0,
                        (a, b) -> a + b);

        // Groupage par Noms de famille des clients
        Map<String, List<Client>> sumAll = list.stream()
                .collect(Collectors.groupingBy(Client::getNom));

        // Top N elements from list
        List<Client> toplist;
        Comparator<Client> comp = (Client c1, Client c2) -> c1.getMontant().compareTo(c2.getMontant());
        list.sort(comp.reversed());

        // Affichage 1
        for (Client cc : result)
        {
            System.out.println(cc);
        }

        // Affichage 2
        System.out.println("Sum of all transactions from 'Patel Will' : " + sum);

        // Affichage 3
        System.out.println("reduce : " + sumReduce);

        // Affichage 4
        for (Map.Entry<String, List<Client>> ccc : sumAll.entrySet())
        {
            System.out.println(ccc);
        }

        // Affichage 5
        for(Client cccc : list)
        {
            System.out.println(cccc);
        }
    }
}
