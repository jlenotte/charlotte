package com.ovh.charlotte;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource
{

    private final static Logger LOGGER = Logger.getLogger(DataSource.class.getName());
    static ArrayList<Invoice> list = new ArrayList<>();

    static void readFile()
    {
        try
        {
            LOGGER.setLevel(Level.ALL);
            LOGGER.log(INFO, "Reading CSV File...");

            CSVReader reader = new CSVReader(new FileReader("dataBase.csv"), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(
                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3] + " "
                        + nextLine[4]);
                String nic = nextLine[0];
                String name = nextLine[1];
                String firstName = nextLine[2];
                double transaction = Double.parseDouble(nextLine[3]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[4]);

                Invoice c = new Invoice(nic, name, firstName, transaction, date);
                list.add(c);
            }
            LOGGER.log(INFO, "File was read successfully.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            LOGGER.setLevel(Level.WARNING);
            LOGGER.log(WARNING,
                "An error occured while trying to read CSV file. Please check your file's location.");
        }
    }
}
