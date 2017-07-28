package com.ovh.charlotte;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import org.junit.Test;

public class DataBaseWriteTest
{

    private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
    private static ArrayList<Invoice> dataList = new ArrayList<Invoice>();
    private final static Logger LOGGER = Logger.getLogger(DataBaseWriteTest.class.getName());

    @Test
    public void dataBaseGeneration()
    {
        try
        {
            LOGGER.setLevel(INFO);
            LOGGER.log(INFO, "Generating randing file entries...");
            String NEWFILE = "dataBase.csv";
            File f = new File(NEWFILE);
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter bw = new BufferedWriter(fw);
            Random rand = new Random();

            for (int i = 0; i < 1000000; i++)
            {
                // Initialize customer attributes
                String name = String.valueOf(Names.getRandomName());
                String firstName = String.valueOf(FirstNames.getRandomName());
                String nic = String.valueOf(name.charAt(0)).toLowerCase() + String
                    .valueOf(firstName.charAt(0)).toLowerCase() + String
                    .valueOf(1000000 + rand.nextInt(999999)) + "-ovh";

                // Generating random dates within a range (2000-2017)
                long min = 31L * 365L * 24L * 3600L * 1000L;
                long max = 47L * 365L * 24L * 3600L * 1000L;
                long l = min + (long) (Math.random() * (max - min));
                Instant instant = Instant.ofEpochMilli(l);
                ZonedDateTime date = ZonedDateTime.ofInstant(instant, UTC_ZONE_ID);

                // Generating random amounts of transactions
                double transaction = ThreadLocalRandom.current().nextDouble(-5000, 5000 + 1);

                // Generation client & ajout dans ArrayList
                Invoice c = new Invoice(nic, name, firstName, transaction, date);
                dataList.add(c);

                // Affichage en console et écriture dans fichier
                System.out.println(
                    c.getNichandle() + ", " + c.getName() + ", " + c.getFirstName() + ", " + c
                        .getTransaction() + ", " + c.getDate());
                bw.write(c.getNichandle() + "," + c.getName() + "," + c.getFirstName() + "," + c
                    .getTransaction() + "," + c.getDate());
                bw.newLine();
                bw.flush();
            }
            bw.close();
            LOGGER.log(INFO, "File was successfully written.");
        }
        catch (Exception e)
        {
            LOGGER.setLevel(WARNING);
            LOGGER.log(WARNING, "Error while writing entries to the CSV file.");
            LOGGER.log(WARNING, e.getMessage());
        }
    }

}
