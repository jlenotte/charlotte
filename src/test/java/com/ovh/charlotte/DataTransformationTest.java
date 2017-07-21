package com.ovh.charlotte;

import com.opencsv.CSVReader;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataTransformationTest
{

    ArrayList<Client> list = new ArrayList<>();

    DataTransformationTest() throws IOException
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
    }

    @Test
    public void dateSearch()
    {
        List<Client> datesearch = list.stream()
                .filter(client -> client.getDate().getYear() == 2015)
                .collect(Collectors.toList());

        for (Client cc : datesearch)
        {
            System.out.println(cc);
        }
    }
}
