package com.ovh.charlotte;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;

public class PrintReport
{

    private final static org.slf4j.Logger LOGGER = LoggerFactory
        .getLogger(PrintReport.class.getName());
    private static ArrayList<Invoice> list = new ArrayList<Invoice>();


    ArrayList<Invoice> getReport() throws IOException
    {
        // Init
        LOGGER.debug("Generating report...");
        String filePath = "REPORT.csv";
        File f = new File(filePath);
        FileWriter fw = new FileWriter(f, false);
        BufferedWriter bw = new BufferedWriter(fw);

        // Write

        // Return
        return list;
    }
}
