package com.ovh.charlotte.products.SubCategories;

import com.ovh.charlotte.products.Categories.CategoryWebHosting;

import java.util.Random;

public enum SubCategoryDomain {
    NEWDOMAIN,
    RENEWDOMAIN,
    TMCH;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryDomain getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
