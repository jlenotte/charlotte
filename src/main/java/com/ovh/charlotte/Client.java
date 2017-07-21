package com.ovh.charlotte;

import java.time.ZonedDateTime;

public class Client
{
    // Attributes
    private String nom = String.valueOf(Noms.getRandomName());
    private String prenom = String.valueOf(Prenoms.getRandomName());
    private ZonedDateTime date;
    private Double montant;


    // Constructor
    public Client()
    {
        this.nom = null;
        this.prenom = null;
        this.montant = null;
        this.date = null;
    }

    public Client(String nom, String prenom, Double montant, ZonedDateTime date)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.montant = montant;
//
//        ZoneId UTC_TIME_ZONE = ZoneId.of("UTC");
//        Random rand = new Random();
//        long now = System.currentTimeMillis();
//        long min = 30l * 365l * 24l * 3600l * 1000l ;
//        long max = 47l * 365l * 24l * 3600l * 1000l ;
//        long x = min + (long)(Math.random() * (max - min));
//
//        Instant instant = Instant.ofEpochMilli(x);
//        date = ZonedDateTime.ofInstant(instant, UTC_TIME_ZONE);
//        System.out.println(DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(date));
        this.date = date;
    }

    // Methods
    public String getNom()
    {
        return nom;
    }

    public Double getMontant()
    {
        return montant;
    }

    public ZonedDateTime getDate()
    {
        return date;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public String toString()
    {
        return "[Client]" + "Nom: " + nom + ", Prenom: " + prenom + ", Montant: " + montant + ", Date: " + date + "\n";
    }
}
