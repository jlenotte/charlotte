package com.ovh.charlotte.products.Categories;

import java.util.Random;

public enum CategoryTelecom {

    INTERNET,
    PHONEPLAN,
    HUBIC;

    /*
     * Get a CategoryCloud.
     */
    public static CategoryTelecom getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
