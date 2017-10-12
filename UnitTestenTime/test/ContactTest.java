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
public class ContactTest {
    Contact contact = new Contact("Sven de Vries");

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Sven de Vries", contact.getName());
    }

    @Test
    public void testAddAppointment() throws Exception {
        Appointment a = new Appointment("test", new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22)));
        Assert.assertTrue(contact.addAppointment(a));
    }

    @Test
    public void testAddAppointmentOverlaps() throws Exception {
        Appointment a = new Appointment("test", new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22)));
        contact.addAppointment(a);
        Appointment a2 = new Appointment("test2", new TimeSpan(new Time(2012, 1, 1, 11, 11), new Time(2021, 2, 22, 22, 22)));
        Assert.assertFalse(contact.addAppointment(a2));
    }

    @Test
    public void testAddAppointmentNull() throws Exception {
        Assert.assertFalse(contact.addAppointment(null));
    }

    @Test
    public void testRemoveAppointment() throws Exception {
        boolean didThrow = false;
        try{
            Appointment a = new Appointment("test", new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22)));
            contact.removeAppointment(a);
        }
        catch (IllegalArgumentException e){
            didThrow = true;
        }
        finally {
            Assert.assertTrue(didThrow);
        }
    }

    @Test
    public void testAppointments() throws Exception {
        Appointment a = new Appointment("test", new TimeSpan(new Time(2011, 1, 1, 11, 11), new Time(2022, 2, 22, 22, 22)));
        Appointment a2 = new Appointment("test2", new TimeSpan(new Time(2012, 2, 2, 12, 12), new Time(2023, 3, 23, 23, 23)));
        contact.addAppointment(a);
        contact.addAppointment(a2);
        Iterator<Appointment> iterator = contact.appointments();
        int count = 0;
        while(iterator.hasNext()){
            if(count == 0){
                Assert.assertEquals(a, iterator.next());
                count++;
            }
            else{
                Assert.assertEquals(a2, iterator.next());
            }
        }
    }
}