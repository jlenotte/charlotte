package com.ovh.charlotte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;

public class DataBaseWriteTest
{

    private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
    private static String NEWFILE = "dataBase.csv";
    public static ArrayList<Client> dataList = new ArrayList<Client>();

    @Test
    public void dataBaseGeneration()
    {
        try
        {
            File f = new File(NEWFILE);
            FileWriter fw = new FileWriter(f, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < 1000000; i++)
            {
                // Attributs clients
                String nom = String.valueOf(Noms.getRandomName());
                String prenom = String.valueOf(Prenoms.getRandomName());
                Random rand = new Random();
                long now = System.currentTimeMillis();
                long min = 31l * 365l * 24l * 3600l * 1000l;
                long max = 47l * 365l * 24l * 3600l * 1000l;
                long l = min + (long) (Math.random() * (max - min));
                Instant instant = Instant.ofEpochMilli(l);
                ZonedDateTime date = ZonedDateTime.ofInstant(instant, UTC_ZONE_ID);
                double montant = ThreadLocalRandom.current()
                    .nextDouble(-5000, 5000 + 1);

                // Generation client & ajout dans ArrayList
                Client c = new Client(nom, prenom, montant, date);
                dataList.add(c);

                // Affichage en console et écriture dans fichier
                System.out.println(
                    c.getNom() + ", " + c.getPrenom() + ", " + c.getMontant() + ", "
                        + c.getDate());
                bw.write(
                    c.getNom() + "," + c.getPrenom() + "," + c.getMontant() + ","
                        + c.getDate());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
