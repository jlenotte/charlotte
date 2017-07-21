//package com.ovh.charlotte;
//
//import com.ovh.charlotte.Client;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//public class FileAccess
//{
//    private static String FILE = "datav3.csv";
//    public static ArrayList<Client> dataList = new ArrayList<Client>();
//
//    public FileAccess()
//    {
//
//        try
//        {
//            FileReader fr = new FileReader(FILE);
//            BufferedReader br = new BufferedReader(fr);
//            String line;
//
//            ZonedDateTime startDate = ZonedDateTime.now();
//            System.out.println(DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(startDate));
//
//            String date = startDate.toString();
//
//            while((line = br.readLine()) != null)
//            {
//                String[] splitArray = null;
//                splitArray = line.split(",");
//                String nom = splitArray[0];
//                String prenom = splitArray[1];
//                String montant = splitArray[2];
//                date = splitArray[3];
//                Client c = new Client(nom, prenom, montant, date);
//                dataList.add(c);
//            }
//            System.out.print(dataList);
//            br.close();
//        }
//        catch(Exception e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public static void displayAmountsByDate()
//    {
//        dataList.stream()
//                .filter(client -> client.getDate().getYear() == 2013);
//    }
//}
