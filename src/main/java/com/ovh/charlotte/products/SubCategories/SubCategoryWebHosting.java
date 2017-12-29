package com.ovh.charlotte.products.SubCategories;

import java.util.Random;

public enum SubCategoryWebHosting {

    HOSTINGPRO,
    HOSTINGPERSO,
    WORDPRESS,
    JOOMLA,
    DRUPAL,
    PRESTASHOP;

    /*
     * Get a CategoryCloud.
     */
    public static SubCategoryWebHosting getCategory() {
        Random rand = new Random();
        return values()[rand.nextInt(values().length)];
    }
}
