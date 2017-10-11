package time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * UnitTestenTime Created by Sven de Vries on 27-9-2017
 */
public class Appointment {
    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> contacts;

    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        contacts = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    public Iterator<Contact> invitees() {
        Iterator<Contact> iterator = contacts.iterator();
        return iterator;
    }

    public boolean addContact(Contact c) {
        try {
            contacts.add(c);
            c.addAppointment(this);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public void removeContact(Contact c) {
        if (contacts.contains(c)) {
            c.removeAppointment(this);
            contacts.remove(c);
        }
        else {
            throw new IllegalArgumentException("Contact is not in the list");
        }
    }
}
