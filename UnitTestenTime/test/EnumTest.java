import org.junit.Test;
import time.DayInWeek;

/**
 * UnitTestenTime
 * @author Dane Naebers
 * Created by Dane Naebers on 4-11-2017.
 */
public class EnumTest {

    /**
     * This unit test can actually be ignored. This Unit test gives me 100% coverage of the methods in the enum.
     * This unit test calls the constructor of the enum. Something JaCoCo wants for 100% coverage.
     */
    @Test
    public void testEnum() throws Exception {
        DayInWeek.valueOf(DayInWeek.MON.toString());
    }
}
