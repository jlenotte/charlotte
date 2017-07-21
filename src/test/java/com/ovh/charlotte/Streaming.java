package com.ovh.charlotte;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Streaming
{
    private static String file = "dataBase.csv";

    @Test
    public void streaming() throws Exception
    {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<String> text = env.readTextFile("dataBase.csv");

//        text.filter(client -> client.startsWith("Bittner"));
//        System.out.println(text.toString());
//        env.execute();

        List<Client> dates = DataBaseWriteTest.dataList.stream()
                .filter(d -> d.getDate().getYear() == 2013)
                .collect(Collectors.toList());
    }

    @Test
    public void testMinimal()
    {
        List<String> lll = new ArrayList<>();
        lll.add("1");
        lll.add("2");
        lll.add("3");
        List<String> result = lll.stream().filter(integer -> integer.equals("2")).collect(Collectors.toList());
        for (String i : result)
        {
            System.out.println("Result " + i);

        }
    }

    @Test
    public void testYear() throws IOException
    {

    }
}
