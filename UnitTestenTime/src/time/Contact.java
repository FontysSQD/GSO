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

    public Contact(String name) {
        this.name = name;
        agenda = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean addAppointment(Appointment a) {
        if(a != null){
            agenda.add(a);
            return true;
        } else {
            return false;
        }
    }

    public void removeAppointment(Appointment a) {
        if (agenda.contains(a)) {
            agenda.remove(a);
        } else {
            throw new IllegalArgumentException("Appointment is not in the list");
        }
    }

    public Iterator<Appointment> appointments() {
        Iterator<Appointment> iterator = agenda.iterator();
        return iterator;
    }
}
