import org.junit.Assert;
import org.junit.Test;
import time.DayInWeek;
import time.Time;

import java.util.GregorianCalendar;

/**
 * @autthor Dane Naebers
 */
public class TimeTest {
    private Time time = new Time(2017, 9, 21, 12, 00);

    @Test(expected = IllegalArgumentException.class)
    public void testTimeMonth() throws Exception {
        time = new Time(2017, 0, 21, 12, 00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeDay() throws Exception {
        time = new Time(2017, 9, 32, 12, 00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeHour() throws Exception {
        time = new Time(2017, 9, 21, 24, 00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTimeMinutes() throws Exception {
        time = new Time(2017, 9, 21, 12, 60);
    }

    @Test
    public void testGetDayInWeekMo() throws Exception {
        time = new Time(2017, 9, 18, 12, 00);
        Assert.assertEquals(DayInWeek.MON, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekTu() throws Exception {
        time = new Time(2017, 9, 19, 12, 00);
        Assert.assertEquals(DayInWeek.TUE, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekWe() throws Exception {
        time = new Time(2017, 9, 20, 12, 00);
        Assert.assertEquals(DayInWeek.WED, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekTh() throws Exception {
        Assert.assertEquals(DayInWeek.THU, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekFr() throws Exception {
        time = new Time(2017, 9, 22, 12, 00);
        Assert.assertEquals(DayInWeek.FRI, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekSa() throws Exception {
        time = new Time(2017, 9, 23, 12, 00);
        Assert.assertEquals(DayInWeek.SAT, time.getDayInWeek());
    }

    @Test
    public void testGetDayInWeekSu() throws Exception {
        time = new Time(2017, 9, 24, 12, 00);
        Assert.assertEquals(DayInWeek.SUN, time.getDayInWeek());
    }

    @Test
    public void testGetYear() throws Exception {
        Assert.assertEquals(2017, time.getYear());
    }

    @Test
    public void testGetMonth() throws Exception {
        Assert.assertEquals(9, time.getMonth());
    }

    @Test
    public void testGetDay() throws Exception {
        Assert.assertEquals(21, time.getDay());
    }

    @Test
    public void testGetHours() throws Exception {
        Assert.assertEquals(12, time.getHours());
    }

    @Test
    public void testGetMinutes() throws Exception {
        Assert.assertEquals(00, time.getMinutes());
    }

    @Test
    public void testPlus() throws Exception {
        time.plus(10);
        Assert.assertEquals(10, time.getMinutes());
    }

    @Test
    public void testCompareToEqual() throws Exception {
        Time time2 = new Time(2017, 9, 21, 12, 00);
        Assert.assertEquals(0, time.compareTo(time2));
    }

    @Test
    public void testCompareToHigher() throws Exception {
        Time time2 = new Time(2017, 10, 21, 12, 00);
        Assert.assertEquals(1, time.compareTo(time2));
    }

    @Test
    public void testCompareToLower() throws Exception {
        Time time2 = new Time(2017, 8, 21, 12, 00);
        Assert.assertEquals(-1, time.compareTo(time2));
    }

    @Test
    public void testDifferenceEqual() throws Exception {
        Time time2 = new Time(2017, 9, 21, 12, 00);
        Assert.assertEquals(0, time.difference(time2));
    }

    @Test
    public void testDifferenceHigher() throws Exception {
        Time time2 = new Time(2017, 9, 21, 12, 10);
        Assert.assertEquals(10, time.difference(time2));
    }

    @Test
    public void testDifferenceLower() throws Exception {
        Time time2 = new Time(2017, 9, 21, 11, 50);
        Assert.assertEquals(-10, time.difference(time2));
    }
}