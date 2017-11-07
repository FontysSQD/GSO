package time;

import java.util.ArrayList;
import java.util.Iterator;

public class Appointment {
    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> contacts;

    /**
     * The creation of an appointment with a list of contacts
     * @param subject is the subject of the appointment
     * @param timeSpan is the time span of the appointment
     */
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
        return contacts.iterator();
    }

    /**
     * Adds a contact to an appointment, and also adds the appointment to that contact
     * @param c is the contact
     * @return if the adding was successful
     */
    public boolean addContact(Contact c) {
        try {
            contacts.add(c);
            return c.addAppointment(this);
        }
        catch (Exception ex){
            return false;
        }
    }

    /**
     * Removes a contact on an appointment, and also removes the appointment of that contact
     * @param c is the contact
     * @exception IllegalArgumentException if the contact does not exists.
     */
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
