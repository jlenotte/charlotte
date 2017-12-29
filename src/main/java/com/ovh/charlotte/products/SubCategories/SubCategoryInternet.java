package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryInternet {
    ADSLVDSL,
    SDSL;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryInternet getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
