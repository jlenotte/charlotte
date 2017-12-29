package com.ovh.charlotte;

import com.ovh.charlotte.products.*;
import com.ovh.charlotte.products.Categories.CategoryCloud;
import com.ovh.charlotte.products.Categories.CategoryTelecom;
import com.ovh.charlotte.products.Categories.CategoryWebHosting;
import com.ovh.charlotte.products.SubCategories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryMixer {

    private static Logger LOG = LoggerFactory.getLogger(CategoryMixer.class);

    /**
     * Scramble the Product Universes and assign the matching CategoryCloud and SubCategory
     * @return String
     */
    public static String scrambleCategories() {

        Universe uni;
        CategoryCloud cloudCat = null;
        CategoryWebHosting webCat = null;
        CategoryTelecom telCat = null;
        SubCategorySD subSd = null;
        SubCategoryVPS subVps = null;
        SubCategoryPub subPub = null;
        SubCategoryPriv subPriv = null;
        SubCategoryHybrid subHybrid = null;
        SubCategoryDomain subDomain = null;
        SubCategoryWebHosting subWebHost = null;
        SubCategoryInternet subInternet = null;
        SubCategoryTelecom subPhonePlan = null;

        StringBuilder sb = new StringBuilder();

        // Get a Product universe
        uni = Universe.getUniverse();

        String productRef;

        // Assign the correct CategoryCloud and SubCategory
        // IF CLOUD
        if (uni == Universe.CLOUD) {

            sb.append(uni);
            sb.append(".");
            cloudCat = CategoryCloud.getCategory();


            if (cloudCat == CategoryCloud.SD) {
                sb.append(cloudCat);
                sb.append(".");
                subSd = SubCategorySD.getCategory();
                sb.append(subSd);
            }
            else if (cloudCat == CategoryCloud.VPS) {
                sb.append(cloudCat);
                sb.append(".");
                subVps = SubCategoryVPS.getCategory();
                sb.append(subVps);
            }
            else if (cloudCat == CategoryCloud.HYBRIDCLOUD) {
                sb.append(cloudCat);
                sb.append(".");
                subHybrid = SubCategoryHybrid.getCategory();
                sb.append(subHybrid);
            }
            else if (cloudCat == CategoryCloud.PRIVATECLOUD) {
                sb.append(cloudCat);
                sb.append(".");
                subPriv = SubCategoryPriv.getCategory();
                sb.append(subPriv);
            }
            else if (cloudCat == CategoryCloud.PUBLICCLOUD) {
                sb.append(cloudCat);
                sb.append(".");
                subPub = SubCategoryPub.getCategory();
                sb.append(subPub);
            }
        }
        else if (uni == Universe.WEBHOSTING) {

            sb.append(uni);
            sb.append(".");
            webCat = CategoryWebHosting.getCategory();

            if (webCat == CategoryWebHosting.DOMAIN) {
                sb.append(webCat);
                sb.append(".");
                subDomain = SubCategoryDomain.getCategory();
                sb.append(subDomain);
            }
            else if (webCat == CategoryWebHosting.WEBHOSTING) {
                sb.append(webCat);
                sb.append(".");
                subWebHost = SubCategoryWebHosting.getCategory();
                sb.append(subWebHost);
            }
        }
        else if (uni == Universe.TELECOM) {

            sb.append(uni);
            sb.append(".");
            telCat = CategoryTelecom.getCategory();

            if (telCat == CategoryTelecom.INTERNET) {
                sb.append(telCat);
                sb.append(".");
                subInternet = SubCategoryInternet.getCategory();
                sb.append(subInternet);
            }
            else if (telCat == CategoryTelecom.PHONEPLAN) {
                sb.append(telCat);
                sb.append(".");
                subPhonePlan = SubCategoryTelecom.getCategory();
                sb.append(subPhonePlan);
            }
        }

        return sb.toString();
    }
}
