package com.ovh.charlotte;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataBaseReadTest
{
    @Test
    public void dataBaseRead() throws Exception
    {
        // Lecture du fichier CSV
        try (BufferedReader br = new BufferedReader(new FileReader("dataBase.csv")))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
            br.close();
        }
    }
}
