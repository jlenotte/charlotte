package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryTelecom {
    VOIP,
    REGULAR,
    SIPIPBX;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryTelecom getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
