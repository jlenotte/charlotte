package com.ovh.charlotte;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class MontantTest
{
    private double montant = ThreadLocalRandom.current().nextDouble(-500000, 1000000 + 1);

    public MontantTest()
    {
        this.montant = montant;
    }

    @Test
    public void displayMontant()
    {
        System.out.println(montant);
    }
}
