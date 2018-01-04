package com.ovh.charlotte;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.operators.SortPartitionOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;
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
            .readTextFile("/home/jules/WORKSPACE/Java/charlotte/charlotte/dataBase.csv");


        // Method calls
        SortPartitionOperator<Tuple2<String, Double>> result = jobs.getBestCustomerFlink(text, 10000000);
//        jobs.getTopTenMonthsFlink();
//        jobs.getTotalPerYearFlink();
//        jobs.getTotalPerYearPerCustomerFlink();

        result.writeAsText("/home/jules/WORKSPACE/Java/charlotte/charlotte/result", FileSystem.WriteMode.OVERWRITE);

        env.execute();
    }
}
