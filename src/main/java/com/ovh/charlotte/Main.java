package com.ovh.charlotte;

import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{

    //Logger
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting... TEST");
        LOGGER.error("Initializing environment...");
        // Init
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<String> text = env
            .readTextFile("/home/jlenotte/WORKSPACE/CodenameCharlotte/dataBase.csv");
        FlinkJobs jobs = new FlinkJobs();

        // TO FIX
        text
            .sortPartition(1, Order.DESCENDING)
            .first(100);

        env.execute();

        // Method calls
        jobs.displayContent(text);
//         jobs.getBestCustomerFlink(text, 100);

        // Flink Jobs writeAsCsv
        // text.writeAsCsv("FLINK_REPORT.CSV");
    }
}
