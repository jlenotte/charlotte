package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryHybrid {
    VRACK,
    VRACKCONNECT;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryHybrid getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
