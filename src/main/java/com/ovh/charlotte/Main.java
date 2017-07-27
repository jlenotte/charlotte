package com.ovh.charlotte;

import java.io.IOException;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class Main
{

    public static void main(String[] args, String outputPath) throws IOException
    {
        System.out
            .println("Welcome to projet Charlotte - Learning Apache Flink & Maven deployments");
//        TransformationTest.flinklessStreamingJobs();

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> text = env.readTextFile("/path/to/file");

        DataSet<Tuple2<String, Integer>> counts =
            // split up the lines in pairs (2-tuples) containing: (word,1)
            text.flatMap(new Tokenizer())
                // group by the tuple field "0" and sum up tuple field "1"
                .groupBy(0)
                .sum(1);

        counts.writeAsCsv(outputPath, "\n", " ");

    }

    // User-defined functions
    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>>
    {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out)
        {
            // normalize and split the line
            String[] tokens = value.toLowerCase().split("\\W+");

            // emit the pairs
            for (String token : tokens)
            {
                if (token.length() > 0)
                {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}
