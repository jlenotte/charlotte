package com.ovh.charlotte;

import java.util.concurrent.ThreadLocalRandom;

public class Montant
{
    private double montant = ThreadLocalRandom.current().nextDouble(-500000, 1000000 + 1);

    public Montant()
    {
        this.montant = montant;
    }

    public double getMontant()
    {
        return montant;
    }

    public String toString()
    {
        return String.valueOf(montant);
    }
}
