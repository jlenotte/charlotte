package com.ovh.charlotte;

import com.opencsv.CSVReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransformationTest
{
    public static ArrayList<Client> dataList = new ArrayList<Client>();

    @Test
    public void TransformationTest() throws IOException
    {
        try
        {
            // lecture fichier
            FileReader fr = new FileReader("dataBase.csv");
            BufferedReader br = new BufferedReader(fr);


            // fichier -> arraylist
            ArrayList<String> list = new ArrayList<>();
            String line;
            String result;
            while ((line = br.readLine()) != null)
            {
                list.add(line);
            }


            // affichage list
            System.out.println(line);


            // test recherche par date pour 2006
            for (String s : list)
            {
                if (s.contains("Date : 2003"))
                {
                    System.out.println("\n MATCH FOUND : " + s);
                }
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

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

    @Test
    public void streamingJobs() throws IOException
    {
        // Initialisation liste
        // ----------------------------------------------------------------------------------------------/
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

        // Opérations sur les données
        // ----------------------------------------------------------------------------------------------/

        // Filter des opérations de Luann Gudger en 2016
        List<Client> result = list.stream()
                .filter(client -> client.getDate().getYear() == 2016 && client.getNom().equals("Gudger") && client.getPrenom().equals("Luann"))
                .collect(Collectors.toList());

        // Aggregation de la somme de tous les montant de Luann Gudger en 2016
        double sum = list.stream()
                .filter(client -> client.getDate().getYear() == 2016)
                .filter(client -> client.getNom().equals("Gudger"))
                .filter(client -> client.getPrenom().equals("Luann"))
                .mapToDouble(Client::getMontant)
                .sum();

        // Reduce du montant des transactions des clients
        double sumReduce = list.stream()
                .mapToDouble(Client::getMontant)
                .reduce(0,
                        (a, b) -> a + b);

        System.out.println("hi");

        // Groupage par Noms de famille des clients
        Map<String, List<Client>> sumAll = list.stream()
                .collect(Collectors.groupingBy(Client::getNom));

        // Affichage
        for (Client cc : result)
        {
            System.out.println(cc);
        }
        System.out.println("sum : " + sum);
        System.out.println("reduce : " + sumReduce);

        for (Map.Entry<String, List<Client>> ccc : sumAll.entrySet())
        {
            System.out.println(ccc);
        }
    }
}
