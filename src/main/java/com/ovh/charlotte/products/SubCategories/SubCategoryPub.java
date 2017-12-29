package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryPub {
    SDDC;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryPub getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
