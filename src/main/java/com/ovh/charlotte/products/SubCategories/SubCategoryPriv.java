package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryPriv {
    SDDC,
    DEDICATED;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryPriv getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
