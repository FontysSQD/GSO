package time;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * UnitTestenTime Created by Sven de Vries on 27-9-2017
 */
public class Contact {
    private String name;
    private ArrayList<Appointment> agenda;

    /**
     * The creation of an contact with a list of appointments
     * @param name is the name of the contact
     */
    public Contact(String name) {
        this.name = name;
        agenda = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds an appointment to an contact and checks if there is no existing appointment at the same time
     * @param a is the appointment
     * @return true if the appointment was successfully added (not overlapped with other appointments)
     */
    public boolean addAppointment(Appointment a) {
        if(a != null){
            for(Appointment ap : agenda) {
                if (ap.getTimeSpan().isPartOf(a.getTimeSpan())) {
                    return false;
                }
            }
            agenda.add(a);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes the appointment of an contact
     * @param a is the appointment
     */
    public void removeAppointment(Appointment a) {
        if (agenda.contains(a)) {
            agenda.remove(a);
        } else {
            throw new IllegalArgumentException("Appointment is not in the list");
        }
    }

    public Iterator<Appointment> appointments() {
        return agenda.iterator();
    }
}
