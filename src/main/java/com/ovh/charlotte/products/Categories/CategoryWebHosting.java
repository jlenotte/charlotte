package com.ovh.charlotte.products.Categories;

import java.util.Random;

public enum CategoryWebHosting {

    DOMAIN,
    WEBHOSTING;

    /*
     * Get a CategoryCloud.
     */
    public static CategoryWebHosting getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
