package com.ovh.charlotte;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.junit.Test;

public class Traitements
{

    @Test
    public void getBestClients()
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER & CONVERSION VERS ARRAYLIST D'OBJETS

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
    public void getMonthTotal()
    {
        //------------------------------------------------------------------------------------------------//
        // OUVERTURE FICHIER & CONVERSION VERS ARRAYLIST D'OBJETS

        ArrayList<Client> list = new ArrayList<>();

        Client c = null;
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

                c = new Client(nom, prenom, montant, date);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //------------------------------------------------------------------------------------------------//
        // TRAITEMENT

//        List<Double> allMonthsTotal = new ArrayList<>();
//        int o;
//        for (o = 0; o <= 11; o++)
//        {
//
//            final int[] yyyy = {1};
//            int MM = 1;
//            double monthlySum = list.parallelStream()
//                .filter(client -> client.getDate().getYear() == 2010)
//                .filter(client -> client.getDate().getMonthValue() == (yyyy[0] + 1))
//                .mapToDouble(Client::getMontant)
//                .sum();
//
//            System.out.println("\n" + "TOTAL TRANSACTIONS FOR 2010/" + MM + " : " + String
//                .format("%1$,.2f", monthlySum));
//            MM++;
//            allMonthsTotal.add(monthlySum);
//        }
//        System.out.println("\n" + "TOTAL TRANSACTIONS : " + String.format("%1$,.2f", monthlySum));

        HashMap<String, Double> map = new HashMap<>();
        // Pour chaque client
        for (Client client : list)
        {
            String year = "";
            String month = "";
            // Ajouter année à K
            year = String.valueOf(client.getDate().getYear());
            month = String.valueOf(client.getDate().getMonthValue());
            String key = year + "/" + month;
            double value = client.getMontant();

            if (map.containsKey(key))
            {
                // if key exists
                // we add the value to the already existing one to avoid overwritting
                value += map.get(key);
                map.put(key, value);
            } else
            {
                // otherwise, we simply add a new key/value entry
                map.put(key, value);
            }
        }

        Map<String, Double> sortedMap = new TreeMap<>(map);
//        System.out.println(sortedMap);
        List<Double> sortedValues = new ArrayList<>();
        for (Entry<String, Double> result : sortedMap.entrySet())
        {
            System.out
                .println("Total des recettes pour " + result.getKey() + " : " + result.getValue());
            sortedValues.add(result.getValue());
        }
        System.out.println(sortedValues);

        HashMap<String, HashMap<String, HashMap<String, HashMap>>> mapMess = new HashMap<>();
    }
}
