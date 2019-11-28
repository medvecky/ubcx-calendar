package model;

public class Event extends Entry {

    private Reminder reminder;

    public Event(MyDate date, MyDate time, String label) {
        super(date, time, label);
        this.reminder = null;
    }

    public Event(MyDate date, MyDate time, String label, Reminder reminder) {
        super(date, time, label);
        this.reminder = reminder;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }
}
