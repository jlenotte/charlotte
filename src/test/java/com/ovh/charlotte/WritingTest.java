package com.ovh.charlotte;

import org.junit.Test;

public class WritingTest {

    @Test
    public void dataGenerationTest() {
        DataBaseWriter dbw = new DataBaseWriter();
        dbw.dataBaseGeneration();
    }
}
