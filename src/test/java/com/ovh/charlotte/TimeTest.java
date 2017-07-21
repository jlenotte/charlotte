package com.ovh.charlotte;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TimeTest
{
    Random rand = new Random();
    private static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
    private int yyyy = ThreadLocalRandom.current().nextInt(2000, 2017 + 1);
    private int mm = ThreadLocalRandom.current().nextInt(1, 12 + 1);
    private int dd = ThreadLocalRandom.current().nextInt(1, 30 + 1);
    private int hh = ThreadLocalRandom.current().nextInt(0, 23 + 1);
    private int min = ThreadLocalRandom.current().nextInt(0, 59 + 1);
    private int ss = ThreadLocalRandom.current().nextInt(0, 59 + 1);
    private int milli = ThreadLocalRandom.current().nextInt(0, 999 + 1);

    @Test
    public void displayValues()
    {
        ZonedDateTime date = ZonedDateTime.of(yyyy, mm, dd, hh, min, ss, milli, UTC_ZONE_ID);
        System.out.println(date);
    }
}
