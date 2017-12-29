package com.ovh.charlotte.products.Categories;

import java.util.Random;

public enum CategoryCloud {

    SD,
    VPS,
    PUBLICCLOUD,
    PRIVATECLOUD,
    HYBRIDCLOUD;

    /*
     * Get a CategoryCloud.
     */
    public static CategoryCloud getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
