package com.ovh.charlotte;

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
        FlinkJobs jobs = new FlinkJobs();
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> text = env
            .readTextFile("/home/jlenotte/WORKSPACE/CodenameCharlotte/dataBase.csv");


        // Method calls
        jobs.getBestCustomerFlink(text, 100);
//        jobs.getTopTenMonthsFlink();
//        jobs.getTotalPerYearFlink();
//        jobs.getTotalPerYearPerCustomerFlink();

        env.execute();
    }
}
