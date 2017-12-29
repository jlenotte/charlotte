package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryVPS {
    SSD,
    SSDRAM,
    SOFTRAID;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryVPS getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
