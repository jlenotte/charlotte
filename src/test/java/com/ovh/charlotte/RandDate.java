package com.ovh.charlotte;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandDate
{
    @Test
    public void generateTime()
    {
        ZoneId UTC_TIME_ZONE = ZoneId.of("UTC");
        Random rand = new Random();
        System.out.println("mm" + Math.random());
        long now = System.currentTimeMillis();
        long min = 30l * 365l * 24l * 3600l * 1000l;
        long max = 47l * 365l * 24l * 3600l * 1000l;
        long x = min + (long) (Math.random() * (max - min));
        System.out.println("x " + x);

        Instant instant = Instant.ofEpochMilli(x);
        ZonedDateTime date = ZonedDateTime.ofInstant(instant, UTC_TIME_ZONE);
        System.out.println(DateTimeFormatter.ofPattern("yyyy/MM/dd - hh:mm").format(date));
    }
}

