package com.ovh.charlotte;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomClientGenerator
{
    public static ArrayList<Client> list = new ArrayList<Client>();
    private static ZoneId UTC_ZONE_ID = ZoneId.of("UTC");

    public static void generateClient()
    {
        for (Client a : list)
        {
            String nom = String.valueOf(Noms.getRandomName());
            String prenom = String.valueOf(Prenoms.getRandomName());
            double montant = ThreadLocalRandom.current().nextDouble(-500000, 1000000 + 1);
            Random rand = new Random();
            long l = rand.nextLong();
            Instant instant = Instant.ofEpochMilli(l);
            ZonedDateTime date = ZonedDateTime.ofInstant(instant, UTC_ZONE_ID);
            Client c = new Client(nom, prenom, montant, date);
            list.add(c);
        }
    }

    public static void displayClients()
    {
        for (Client c : list)
        {
            System.out.println(c);
        }
    }
}
