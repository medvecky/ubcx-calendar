package model;

import java.util.ArrayList;
import java.util.List;

public class Meeting extends Event {

    private List<String> attendees;

    public Meeting(MyDate date, MyTime time, String label) {
        super(date, time, label);
        attendees = new ArrayList<>();
    }

    public Meeting(MyDate date, MyTime time, String label, Reminder reminder) {
        super(date, time, label, reminder);
        attendees = new ArrayList<>();
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void addAttendee(String name) {
        if (!attendees.contains(name)) {
            attendees.add(name);
        }
    }

    public void removeAttendee(String name) {
        if (attendees.contains(name)) {
            attendees.remove(name);
        }
    }

    public void sendInvites() {
        if (attendees.size() > 0) {
            attendees.forEach(attendee -> System.out.println("Inviting: " + attendee + "@gmail.com"));
        } else {
            System.out.println("No attendees found");
        }
    }
}
