package com.ovh.charlotte.products;

import java.util.Random;

public enum Universe {

    CLOUD,
    WEBHOSTING,
    TELECOM;

    /*
     * Get a random Universe.
     */
    public static Universe getUniverse() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
