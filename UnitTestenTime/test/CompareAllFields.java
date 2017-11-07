import org.junit.Assert;
import org.junit.Test;
import time.ITimeSpan;
import time.Time;

/**
 * UnitTestenTime Created by Sven de Vries on 4-10-2017
 */
public class CompareAllFields {
    public void compareAllFieldsInGcBeginTime(Time time, ITimeSpan timeSpan) {
        Assert.assertEquals(time.getYear(), timeSpan.getBeginTime().getYear());
        Assert.assertEquals(time.getMonth(), timeSpan.getBeginTime().getMonth());
        Assert.assertEquals(time.getDay(), timeSpan.getBeginTime().getDay());
        Assert.assertEquals(time.getHours(), timeSpan.getBeginTime().getHours());
        Assert.assertEquals(time.getMinutes(), timeSpan.getBeginTime().getMinutes());
    }

    public void compareAllFieldsInGcEndTime(Time time, ITimeSpan timeSpan) {
        Assert.assertEquals(time.getYear(), timeSpan.getEndTime().getYear());
        Assert.assertEquals(time.getMonth(), timeSpan.getEndTime().getMonth());
        Assert.assertEquals(time.getDay(), timeSpan.getEndTime().getDay());
        Assert.assertEquals(time.getHours(), timeSpan.getEndTime().getHours());
        Assert.assertEquals(time.getMinutes(), timeSpan.getEndTime().getMinutes());
    }
}
