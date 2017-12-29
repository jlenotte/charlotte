package com.ovh.charlotte;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import org.apache.flink.api.java.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource
{

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSource.class.getName());


    public ArrayList<Invoice> readFile(DataSet<String> fileName)
    {
        ArrayList<Invoice> list = new ArrayList<>();
        try
        {
            LOGGER.debug("Reading CSV file...");

            CSVReader reader = new CSVReader(new FileReader(String.valueOf(fileName)), ',');
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null)
            {
//                System.out.println(
//                    nextLine[0] + " " + nextLine[1] + " " + nextLine[2] + " " + nextLine[3] + " "
//                        + nextLine[4]);
                String nic = nextLine[0];
                String name = nextLine[1];
                String firstName = nextLine[2];
                String ref = nextLine[3];
                double transaction = Double.parseDouble(nextLine[4]);
                ZonedDateTime date = ZonedDateTime.parse(nextLine[5]);

                Invoice c = new Invoice(nic, name, firstName, ref, transaction, date);
                list.add(c);
            }
            LOGGER.debug("File was read with success.");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            LOGGER.debug(
                "An error occured while trying to read the CSV file. Please check your file's name and path.");
        }
        return list;
    }

}
