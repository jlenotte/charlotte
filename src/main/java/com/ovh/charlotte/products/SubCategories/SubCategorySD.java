package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategorySD {

    HOSTL,
    HOSTH,
    POLY,
    HOSTING,
    GPU,
    GAMING;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategorySD getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
