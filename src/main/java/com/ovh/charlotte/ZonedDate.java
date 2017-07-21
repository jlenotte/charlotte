package com.ovh.charlotte;

import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ZonedDate
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

    public ZonedDate()
    {
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
        this.hh = hh;
        this.min = min;
        this.ss = ss;
        this.milli = milli;
    }

    public ZonedDate(int yyyy, int mm, int dd, int hh, int min, int ss, int milli)
    {
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
        this.hh = hh;
        this.min = min;
        this.ss = ss;
        this.milli = milli;
    }

    public int getYyyy()
    {
        return yyyy;
    }

    public int getMm()
    {
        return mm;
    }

    public int getDd()
    {
        return dd;
    }

    public int getHh()
    {
        return hh;
    }

    public int getMin()
    {
        return min;
    }

    public int getSs()
    {
        return ss;
    }

    public int getMilli()
    {
        return milli;
    }

    public void setYyyy(int yyyy)
    {
        this.yyyy = yyyy;
    }

    public void setMm(int mm)
    {
        this.mm = mm;
    }

    public void setDd(int dd)
    {
        this.dd = dd;
    }

    public void setHh(int hh)
    {
        this.hh = hh;
    }

    public void setMin(int min)
    {
        this.min = min;
    }

    public void setSs(int ss)
    {
        this.ss = ss;
    }

    public void setMilli(int milli)
    {
        this.milli = milli;
    }

    public void toDateFormat()
    {
        String month;
        String day;
        String hour;
        String min;
        String sec;
        String millisec;
        // Transformer String en format ZonedDate

    }

    public String toString()
    {
        return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min + ":" + ss + ":" + milli + "[" + UTC_ZONE_ID + "]";
    }
}
