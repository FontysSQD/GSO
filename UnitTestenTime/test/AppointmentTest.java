import org.junit.Assert;
import org.junit.Test;
import time.Appointment;
import time.Contact;
import time.Time;
import time.TimeSpan;

import java.util.Iterator;

/**
 * UnitTestenTime Created by Sven de Vries on 27-9-2017
 */
public class AppointmentTest {
    Appointment appointment = new Appointment("test", new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22)));

    @Test
    public void testGetSubject() throws Exception {
        Assert.assertEquals("test", appointment.getSubject());
    }

    @Test
    public void testGetTimeSpan() throws Exception {
        TimeSpan timeSpan2 = new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22));
        Assert.assertEquals(timeSpan2.getBeginTime().getYear(), appointment.getTimeSpan().getBeginTime().getYear());
        Assert.assertEquals(timeSpan2.getBeginTime().getMonth(), appointment.getTimeSpan().getBeginTime().getMonth());
        Assert.assertEquals(timeSpan2.getBeginTime().getDay(), appointment.getTimeSpan().getBeginTime().getDay());
        Assert.assertEquals(timeSpan2.getBeginTime().getHours(), appointment.getTimeSpan().getBeginTime().getHours());
        Assert.assertEquals(timeSpan2.getBeginTime().getMinutes(), appointment.getTimeSpan().getBeginTime().getMinutes());
        Assert.assertEquals(timeSpan2.getEndTime().getYear(), appointment.getTimeSpan().getEndTime().getYear());
        Assert.assertEquals(timeSpan2.getEndTime().getMonth(), appointment.getTimeSpan().getEndTime().getMonth());
        Assert.assertEquals(timeSpan2.getEndTime().getDay(), appointment.getTimeSpan().getEndTime().getDay());
        Assert.assertEquals(timeSpan2.getEndTime().getHours(), appointment.getTimeSpan().getEndTime().getHours());
        Assert.assertEquals(timeSpan2.getEndTime().getMinutes(), appointment.getTimeSpan().getEndTime().getMinutes());
    }

    @Test
    public void testInvitees() throws Exception {
        Contact c = new Contact("Ruben Dieleman");
        Contact c2 = new Contact("Dane Naebers");
        appointment.addContact(c);
        appointment.addContact(c2);
        Iterator<Contact> iterator = appointment.invitees();
        int count = 0;
        while (iterator.hasNext()) {
            if (count == 0) {
                Assert.assertEquals(c, iterator.next());
                count++;
            } else {
                Assert.assertEquals(c2, iterator.next());
            }
        }
    }

    @Test
    public void testAddContactTrue() throws Exception {
        Contact c = new Contact("Sven de Vries");
        Assert.assertEquals(true, appointment.addContact(c));
    }

    @Test
    public void testAddContactFalse() throws Exception {
        Contact c = null;
        Assert.assertEquals(false, appointment.addContact(c));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveContact() throws Exception {
        Contact c = new Contact("Quint Aartsen");
        appointment.removeContact(c);
    }

    @Test
    public void testRemoveContactSucceed() throws Exception {
        Contact c = new Contact("Quint Aartsen");
        appointment.addContact(c);
        appointment.removeContact(c);
        Assert.assertEquals(false, appointment.invitees().hasNext());
    }
}