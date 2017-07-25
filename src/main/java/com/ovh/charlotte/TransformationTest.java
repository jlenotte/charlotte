//package com.ovh.charlotte;
//
//import static org.junit.Assert.assertTrue;
//
//import com.opencsv.CSVReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.apache.flink.api.java.ExecutionEnvironment;
//import org.junit.Test;
//
//public class TransformationTest
//{
//
//    public static ArrayList<Client> dataList = new ArrayList<Client>();
//
//    @Test
//    public void splitTest()
//    {
//        String s = "Nazzaro,Eli,Montant : 487659.5646186366,Date : 2008-1-5 17:28:42:943 TIMEZONE : UTC";
//        String[] xxx = s.split(",");
//        for (int i = 0; i < xxx.length; i++)
//        {
//            System.out.println(xxx[i]);
//        }
//    }
//
//    @Test
//    public void dateSearch() throws IOException
//    {
//        ArrayList<Client> list = new ArrayList<>();
//        CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
//        String[] nextLine;
//
//        while ((nextLine = reader.readNext()) != null)
//        {
//            System.out
//                .println(nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
//            String nom = nextLine[0];
//            String prenom = nextLine[1];
//            double montant = Double.parseDouble(nextLine[2]);
//            ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);
//
//            Client c = new Client(nom, prenom, montant, date);
//            list.add(c);
//        }
//
//        // filter by YYYY MM and Name
//        List<Client> result = list.stream()
//            .filter(client -> client.getDate().getYear() == 2009
//                && client.getDate().getMonthValue() == 07 && client.getNom().equals("Gudger"))
//            .collect(Collectors.toList());
//
//        for (Client cc : result)
//        {
//            System.out.println(cc);
//        }
//    }
//
//    @Test
//    public void flinklessStreamingJobs() throws IOException
//    {
//        // Initialisation liste
//        // ----------------------------------------------------------------------------------------------/
//        ArrayList<Client> list = new ArrayList<>();
//
//        try
//        {
//            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
//            String[] nextLine;
//
//            while ((nextLine = reader.readNext()) != null)
//            {
//                System.out.println(
//                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3]);
//                String nom = nextLine[0];
//                String prenom = nextLine[1];
//                double montant = Double.parseDouble(nextLine[2]);
//                ZonedDateTime date = ZonedDateTime.parse(nextLine[3]);
//
//                Client c = new Client(nom, prenom, montant, date);
//                list.add(c);
//            }
//        } catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//        // Opérations sur les données du fichier CSV
//        // ----------------------------------------------------------------------------------------------/
//
//        // Filter des opérations de Luann Gudger en 2016 -------------------------------------------------------
//        // A1
//        List<Client> result = list.stream()
//            .filter(client -> client.getDate().getYear() == 2013 && client.getNom().equals("Patel")
//                && client.getPrenom().equals("Carlo"))
//            .collect(Collectors.toList());
//
//        // Aggregation de la somme de tous les montant de 'Nom' 'Prenom' par 'Année' ---------------------------
//        // A2
//        double sum = list.stream()
////                .filter(client -> client.getDate().getYear() == 2012)
//            .filter(client -> client.getNom().equals("Patel"))
//            .filter(client -> client.getPrenom().equals("Will"))
//            .mapToDouble(Client::getMontant)
//            .sum();
//        assertTrue(!list.isEmpty());
//
//        // Reduce du montant des transactions des clients ------------------------------------------------------
//        // A3
//        double sumReduce = list.stream()
//            .mapToDouble(Client::getMontant)
//            .reduce(0,
//                (a, b) -> a + b);
//
//        // Groupage par Noms de famille des clients ------------------------------------------------------------
//        // A4
//        Map<String, List<Client>> sumAll = list.stream()
//            .collect(Collectors.groupingBy(Client::getNom));
//
//        // Top N elements from list ----------------------------------------------------------------------------
//        // A5
//        List<Double> toplist = new ArrayList<>();
//        double montant;
//
//        for (Client c : list)
//        {
//            montant = c.getMontant();
//            toplist.add(montant);
//        }
//        Collections.sort(toplist, Collections.reverseOrder());
//
//        // Top N elements from list ----------------------------------------------------------------------------
//        // A6
//
//        // Aggregation par YYYY + MM ---------------------------------------------------------------------------
//        // A7
//
//        // Multicritère sorting --------------------------------------------------------------------------------
//        // A8
//
//        //------------------------------------------------------------------------------------------------------
//        // A1
////                System.out.println("\n ///// FILTRAGE DES OPERATIONS DE Patel Carlo EN 2013 /////");
////                for (Client cc : result)
////                {
////                        System.out.println(cc);
////                }
////
////                // A2
//        System.out.println("\n ///// AGGREGATION SOMME DES MONTANTS DE PATEL WILL /////");
//        System.out.println("Sum of all transactions from 'Patel Will' : " + sum);
////
////                // A3
//        System.out.println("\n ///// REDUCE DES MONTANTS TRANSACTIONS /////");
//        System.out.println("reduce : " + sumReduce);
////
//        // A4
//        System.out.println("\n ///// GROUPBY : NOMS DE FAMILLE /////");
//        for (Map.Entry<String, List<Client>> ccc : sumAll.entrySet())
//        {
//            System.out.println(ccc);
//        }
//
//        // A5
//        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS /////");
//        List<Double> tophundred = new ArrayList<>(toplist.subList(0, 99));
//        Collections.sort(tophundred, Collections.reverseOrder());
//        System.out.println(tophundred);
//
//        // A6
//        final int[] i = {1};
//        System.out.println("\n ///// TOP 100 DES MONTANTS DE TRANSACTIONS LAMBDA /////");
//        Comparator<Client> byCost = (c1, c2) -> Double.compare(
//            c1.getMontant(), c2.getMontant());
//        list.stream()
//            .sorted(byCost.reversed())
//            .limit(100)
//            .forEach(client -> System.out.println("[" + (i[0]++) + "] " + client));
//
//        // A8
//        Comparator<Client> byMontant = (c1, c2) -> c1.getMontant().compareTo(c2.getMontant());
//
//        Comparator<Client> byName = (c1, c2) -> c1.getNom().compareTo(c2.getNom());
//
//        list.stream()
//            .sorted(byMontant.thenComparing(byName))
//            .forEach(client -> System.out.println(client));
//    }
//
//    @Test
//    public void flinkStreamingJobs()
//    {
//        String outputPath = "output.csv";
//        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        //DataSet<Client> result = env.readCsvFile("dataBase.cs");
//
//        //DataSet<Client> counts = text.filter()
//        //counts.writeAsCsv(outputPath, "\n", " ");
//    }
//}
