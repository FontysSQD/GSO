import org.junit.Assert;
import org.junit.Test;
import time.ITimeSpan;
import time.Time;
import time.TimeSpan;

/**
 * UnitTesten-Time Created by Sven de Vries on 20-9-2017
 */
public class TimeSpanTest {
    ITimeSpan timeSpan = new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 2, 22, 22));
    CompareAllFields compareAllFields = new CompareAllFields();

    @Test(expected = IllegalArgumentException.class)
    public void testTimeSpan() throws Exception {
        timeSpan = new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2002, 2, 2, 22, 22));
    }

    @Test
    public void testLength() throws Exception {
        timeSpan.setEndTime(new Time(2011, 1, 1, 11, 21));
        Assert.assertEquals(10, timeSpan.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBeginTime() throws Exception {
        timeSpan.setBeginTime(new Time(2023, 9, 21, 12, 00));
    }

    @Test
    public void testSetBeginTime2() throws Exception {
        timeSpan.setBeginTime(new Time(2017, 9, 21, 12, 00));
        Time time = new Time(2017, 9, 21, 12, 00);
        compareAllFields.compareAllFieldsInGcBeginTime(time, timeSpan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEndTime() throws Exception {
        timeSpan.setEndTime(new Time(2010, 11, 25, 12, 00));
    }

    @Test
    public void testSetEndTime2() throws Exception {
        timeSpan.setEndTime(new Time(2033, 3, 3, 3, 33));
        Time time = new Time(2033, 3, 3, 3, 33);
        compareAllFields.compareAllFieldsInGcEndTime(time, timeSpan);
    }

    @Test
    public void testMove() throws Exception {
        timeSpan.move(30);
        Assert.assertEquals(41, timeSpan.getBeginTime().getMinutes());
        Assert.assertEquals(52, timeSpan.getEndTime().getMinutes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeLengthWith() throws Exception {
        timeSpan.changeLengthWith(-10);
    }

    @Test
    public void testChangeLengthWith2() throws Exception {
        timeSpan.changeLengthWith(10);
        Assert.assertEquals(32, timeSpan.getEndTime().getMinutes());
    }

    @Test
    public void testIsPartOfTrue() throws Exception {
        Boolean isPartOf = timeSpan.isPartOf(new TimeSpan(new Time(2015, 1, 1, 11, 11), new Time(2020, 2, 22, 22, 22)));
        Assert.assertEquals(true, isPartOf);
    }

    @Test
    public void testIsPartOfFalse() throws Exception {
        Boolean isPartOf = timeSpan.isPartOf(new TimeSpan(new Time(2010, 1, 1, 11, 11), new Time(2020, 2, 22, 22, 22)));
        Assert.assertEquals(false, isPartOf);
    }

    @Test
    public void testUnionWithGood() throws Exception {
        TimeSpan timeSpanUnion = (TimeSpan) timeSpan.unionWith(new TimeSpan(new Time(2015, 1, 1, 11, 11), new Time(2030, 2, 22, 22, 22)));
        Assert.assertEquals(2011, timeSpanUnion.getBeginTime().getYear());
        Assert.assertEquals(2030, timeSpanUnion.getEndTime().getYear());
    }

    @Test
    public void testUnionWithGood2() throws Exception {
        TimeSpan timeSpanUnion = (TimeSpan) timeSpan.unionWith(new TimeSpan(new Time(2010, 1, 1, 11, 11), new Time(2020, 2, 22, 22, 22)));
        Assert.assertEquals(2010, timeSpanUnion.getBeginTime().getYear());
        Assert.assertEquals(2022, timeSpanUnion.getEndTime().getYear());
    }

    @Test
    public void testUnionWithNull() throws Exception {
        TimeSpan timeSpanUnion = (TimeSpan) timeSpan.unionWith(new TimeSpan(new Time(2005, 1, 1, 11, 11), new Time(2010, 2, 22, 22, 22)));
        Assert.assertEquals(null, timeSpanUnion);
    }

    @Test
    public void testIntersectionWithGood() throws Exception {
        TimeSpan timeSpanIntersection = (TimeSpan) timeSpan.intersectionWith(new TimeSpan(new Time(2015, 1, 1, 11, 11), new Time(2030, 2, 22, 22, 22)));
        Assert.assertEquals(2015, timeSpanIntersection.getBeginTime().getYear());
        Assert.assertEquals(2022, timeSpanIntersection.getEndTime().getYear());
    }

    @Test
    public void testIntersectionWithGood2() throws Exception {
        TimeSpan timeSpanIntersection = (TimeSpan) timeSpan.intersectionWith(new TimeSpan(new Time(2010, 1, 1, 11, 11), new Time(2020, 2, 22, 22, 22)));
        Assert.assertEquals(2011, timeSpanIntersection.getBeginTime().getYear());
        Assert.assertEquals(2020, timeSpanIntersection.getEndTime().getYear());
    }

    @Test
    public void testIntersectionWithNull() throws Exception {
        TimeSpan timeSpanIntersection = (TimeSpan) timeSpan.intersectionWith(new TimeSpan(new Time(2005, 1, 1, 11, 11), new Time(2010, 2, 22, 22, 22)));
        Assert.assertEquals(null, timeSpanIntersection);
    }
}